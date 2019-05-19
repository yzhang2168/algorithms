package algorithms;

import java.util.HashSet;

public class MissingNumber {

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
	public static int findMissingNumberI(int[] array) {
		int n = array.length + 1;
		int xor = 0;
		for (int i = 1; i <= n; i++) {
			xor = xor ^ i;
		}

		for (int num : array) {
			xor = xor ^ num;
		}

		return xor;
	}

	/**
	 * use a hash set
	 * */
	public static int findMissingNumberII(int[] array) {
		int n = array.length + 1;
		int missing = -1;
		HashSet<Integer> set = new HashSet<Integer>();

		for (int num : array) {
			set.add(num);
		}

		for (int i = 1; i <= n; i++) {
			if (!set.contains(i)) {
				missing = i;
				break;
			}
		}
		return missing;
	}

	public static int findMissingNumberIII(int[] array) {
		boolean[] occurred = new boolean[array.length + 2];
		int result = -1;
		// linear scan the input array
		for (int num : array) {
			occurred[num] = true;
		}

		for (int i = 1; i <= array.length + 1; i++) {
			if (occurred[i] == false) {
				result = i;
				break;
			}
		}
		return result;
	}

	public static void main(String args[]) 
	{ 
		int a[] = {1,2,4,5,6}; 
		System.out.println(findMissingNumberI(a)); 
		System.out.println(findMissingNumberII(a)); 
		System.out.println(findMissingNumberIII(a)); 
		System.out.println(findMissingNumberI(a)); 
	} 
}
