package algorithms.DP;

public class LongestAscendingSubarray {

	/**
	 * given an unsorted array, find the length of the longest ascending subarray. 
	 * Example: {7, 2, 3, 1, 5, 8, 9, 6}
	 * answer: 4 - {1, 5, 8, 9}
	 * 
	 * base case: m[0] = 1
	 * induction rule: 
	 * 		semantics: m[i] is the length of longest ascending subarray in [0...i] including input[i]
	 * 		bigger problem <- smaller problem requires that m[i] including input[i] in order to form a subarray
	 * 		math: if m[i] > m[i - 1], m[i] = m[i - 1] + 1
	 * 			  else m[i] = 1
	 * 		keep a global max
	 * time : O(n)
	 * space: O(n)
	 * */
	public static int longestAscendingSubarrayI(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}
		
		int n = input.length;
		int[] m = new int[n];
		m[0] = 1;
		int globalMax = 1;
		
		for (int i = 1; i < n; i++) {
			if (input[i] > input[i - 1]) {
				m[i] = m[i - 1] + 1;
				globalMax = Math.max(globalMax, m[i]);
			} else {
				m[i] = 1;
			}
		}
		
		return globalMax;
	}
	
	/**
	 * space: optimized to O(1)
	 * */ 
	public static int longestAscendingSubarrayII(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}
		
		int currMi = 1;
		int globalMax = 1;
		
		for (int i = 1; i < input.length; i++) {
			if (input[i] > input[i - 1]) {
				currMi++;
				globalMax = Math.max(globalMax, currMi);
			} else {
				currMi = 1;
			}
		}
		
		return globalMax;
	}	

	public static void main(String[] args) {
		int[] input = {7, 1, 5, 8, 9, 6, 2, 3};
		System.out.println(longestAscendingSubarrayI(input));
		System.out.println(longestAscendingSubarrayII(input));
	}
}
