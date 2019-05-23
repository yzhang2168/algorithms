package string;

import java.util.Arrays;

public class RollingHash {
	public long naiveHash(char[] array, int base, int modulus) {
		long hash = 0;
		for (int i = 0; i < array.length; i++) {
			hash = (hash * base % modulus + array[i]) % modulus;
		}
		return hash;
	}
	
	public int[] rollingHash(char[] string, int windowSize, int base, int modulus) {
		int[] hashValues = new int[string.length - windowSize + 1];
		
		// seedHash: window at index 0
		int seedHash = seedHash(string, 0, windowSize, base, modulus);
		hashValues[0] = seedHash;

		// seed: the base factor for the 1st position in window
		// (base ^ (windowSize - 1)) % modulus
		int seed = 1;
		for (int i = 0; i < windowSize; i++) {
			seed = seed * base % modulus;
		}

		// use a sliding window to do rolling hash
		int hash = 0;
		for (int i = 1; i < string.length - windowSize + 1; i++) {
			// remove out of window char at i - 1
			hash = removeChar(hash, string[i - 1], seed, modulus);
			// make it non-negative
			hash = nonNegative(hash, modulus);
			// add new char at i + windowSize - 1
			hash = addChar(hash, string[i + windowSize - 1], base, modulus);
			hashValues[i] = hash;
		}
		return hashValues;
	}
	
	private int nonNegative(int value, int modulus) {
		if (value < 0) {
			value += modulus;
		}
		return value;
	}
	
	private int removeChar(int hash, int deletion, int seed, int modulus) {
		return hash - seed * deletion % modulus;
	}
	
	private int addChar(int hash, int addition, int base, int modulus) {
		return (hash * base % modulus + addition) % modulus;
	}
	
	public int seedHash(char[] string, int start, int windowSize, int base, int modulus) {
		int seedHash = 0;
		for (int i = start; i < start + windowSize; i++) {
			seedHash = addChar(seedHash, string[i], base, modulus);
		}
		return seedHash;
	}
	
	public static void main(String[] args) {
		RollingHash test = new RollingHash();
		int base = 31;
		int modulus = 171;
		System.out.println(test.naiveHash(new char[] {'a'}, 26, 101));
		System.out.println((97 * 26 % 101 + 98) % 101);
		System.out.println(test.naiveHash(new char[] {'a', 'b'}, 26, 101));
		System.out.println(Arrays.toString(test.rollingHash("abcabcdcba".toCharArray(), 3, base, modulus)));
	}
}
