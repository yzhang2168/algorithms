package DP;

public class LongestConsecutiveOnes {

	/**
	 * Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.
	 * Assumptions
	 * The given array is not null
	 * Examples
	 * {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
	 * */
	public static int longest(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}
		
		// m[i]: longest consecutive 1s in subarray [0...i] including input[i]
		int n = input.length;
		int[] m = new int[n];
		
		// linear scan and look back to m[i]
		// base case
		m[0] = input[0] == 1 ? 1: 0;
		int max = m[0];
		
		// induction rule:
		// if input[i] == 1, m[i] = m[i - 1] + 1
		//			   == 0, m[i] = 0
		for (int i = 1; i < n; i++) {
			if (input[i] == 1) {
				m[i] = m[i - 1] + 1;
				max = Math.max(max, m[i]);
			} else {
				m[i] = 0;
			}
		}
		
		return max;
	}
	
	
	public static void main(String[] args) {
		int[] input = {1,0,1,0,0,1,1,1,1,0};
		System.out.println(longest(input));
		System.out.println(longest(new int[] {1}));
	}
}
