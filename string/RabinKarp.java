package string;

public class RabinKarp {
	/**
	 * cannot assume charset are only 26 lowercase chars
	 * need to handle:
	 * 1. can use arbitrary charset - base a small prime
	 * 2. overflow, take mod on a very large prime
	 * 
	 * */
	public static int rabinKarp(String text, String pattern) {
		if (text.length() < pattern.length()) {
			return -1;
		}
		
		if (pattern.length() == 0) {
			return 0;
		}
		
		// need a large prime for mod
		int largePrime = 101;
		// small prime here corresponds to the base 
		// since the charset could be very large, e.g., 1m for UTF, we cannot use the number directly
		int prime = 31;
		int seed = 1;
		
		// hash = (s0 * smallPrime ^ k + s1 * smallPrime ^ (k-1) +...sk * smallPrime ^ 0) % largePrime
		// calculate pattern hash value
		int targetHash = pattern.charAt(0) % largePrime;		
		for (int i = 1; i < pattern.length(); i++) {
			seed = moduleHash(seed, 0, prime, largePrime);
			targetHash = moduleHash(targetHash, pattern.charAt(i), prime, largePrime);
		}
		
		// text: 1st sliding window 
		int hash = 0;
		for (int i = 0; i < pattern.length(); i++) {
			hash = moduleHash(hash, text.charAt(i), prime, largePrime);
		}
		
		if (hash == targetHash && equals(text, 0, pattern)) {
			return 0;
		}
		
		// text: use a sliding window to scan through it
		for (int i = 1; i <= text.length() - pattern.length(); i++) {			
			// 1. remove out of window char at i - 1
			// seed = (prime ^ (pattern.length - 1)) % largePrime, the base factor for the 1st position in window
			// need to make sure the mod is not negative
			hash = nonNegative(hash - seed * text.charAt(i - 1) % largePrime, largePrime);
			
			// 2. add new char at i + windowSize - 1
			hash = moduleHash(hash, text.charAt(i + pattern.length() - 1), prime, largePrime);
			
			// 3. handle collision
			// on average, this will not increase the time complexity since collision is O(1), we still have O(n + m)
			if (hash == targetHash && equals(text, i, pattern)) {
				return i;
			}
		}
		return -1;
	}
	
	private static boolean equals(String large, int start, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (large.charAt(i + start) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// take mod for each calculation to scale down the number
	private static int moduleHash(int hash, int addition, int prime, int largePrime) {
		return (hash * prime % largePrime + addition) % largePrime;
	}
	
	private static int nonNegative(int hash, int largePrime) {
		if (hash < 0) {
			hash += largePrime;
		}
		return hash;
	}
}
