package hash;

import java.util.Arrays;
import java.util.HashSet;

public class MissingNumber {

	public int missingNumberI(int[] array) {
		int n = array.length + 1;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}
		
		for (int i = 1; i < n; i++) {
			if (!set.contains(i)) {
				return i;
			}
		}		
		return n;
	}
	
	public int missingNumberII(int[] array) {
		int n = array.length + 1;
		long targetSum = (n + 0L) * (n + 1) / 2;
		long actualSum = 0;
		for (int num : array) {
			actualSum += num;
		}
		return (int) (targetSum - actualSum);
	}
	
	public int missingNumberIII(int[] array) {
		int n = array.length + 1;
		boolean[] target = new boolean[n + 1];
		for (int num : array) {
			target[num] = true;
		}
		
		for (int i = 1; i <= n - 1; i++) {
			if (!target[i]) {
				return i;
			}
		}
		return n;
	}
	
	/**
	 * given a list of n-1 integers and these integers are in the range of 1 to n. 
	 * There are no duplicates in list. 
	 * One of the integers is missing in the list. 
	 * 
	 * solution: XOR bit operation
	 * O(n)
	 * x ^ x = 0;
	 * x ^ 0 = x;
	 * 1 ^ 2 ^ 3 ^ ... ^ n ^ a0 ^ a1 ^ a2 ^ ... ^ an-1
	 * associate/commutative
	 * 1 ^ 1 ^ 2 ^ 2 ^ ... ^ n ^ n ^ missing = missing
	 * */
	public int missingNumberIV(int[] array) {
		int n = array.length + 1;
		int xor = 0; // 0 ^ 10 = 10
		for (int i = 1; i <= n; i++) {
			xor = xor ^ i;
		}
		
		for (int num : array) {
			xor = xor ^ num;
		}
		return xor;
	}
	
	public static void main(String[] args) {
		System.out.println(10 ^ 0); // 10
		System.out.println(0 ^ 0);
		System.out.println(1 ^ 1);
		System.out.println(0 ^ 1);
	}
}
