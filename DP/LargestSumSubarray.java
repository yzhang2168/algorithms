package algorithms.DP;

public class LargestSumSubarray {

	/**
	 * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.
	 * Assumptions
	 * The given array is not null and has length of at least 1.
	 * Examples
	 * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
	 * {-2, -1, -3}, the largest subarray sum is -1
	 * 
	 * linear scan, look back to the last one
	 * base case
	 * m[0] = input[0];
	 * induction rule
	 * 		m[i]: largestSumSubarray in 0...i including input[i]
	 * 		m[i] = m[i - 1] + input[i] if m[i - 1] >= 0
	 * 		m[i] = input[i] otherwise 
	 * 
	 * time: O(n)
	 * space: O(n) - can be optimized
	 * */

	public static int largestSumSubarrayI(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}

		int n = input.length;
		int[] m = new int[n];

		// base case
		m[0] = input[0];
		int globalMax = input[0];

		// induction rule
		// m[i]: largestSumSubarray in 0...i including input[i]
		// m[i] = m[i - 1] + input[i] if m[i - 1] >= 0
		// m[i] = input[i] otherwise 
		for (int i = 1; i < n; i++) {
			if (m[i - 1] >= 0) {
				m[i] = m[i - 1] + input[i];
				globalMax = Math.max(globalMax, m[i]);
			} else {
				m[i] = input[i];
				globalMax = Math.max(globalMax, m[i]);
			}
		}

		return globalMax;
	}

	// space: O(1)
	public static int largestSumSubarrayII(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}

		// base case
		int lastMax = input[0];
		int globalMax = input[0];

		// induction rule
		// m[i]: largestSumSubarray in 0...i including input[i]
		// m[i] = m[i - 1] + input[i] if m[i - 1] >= 0
		// m[i] = input[i] otherwise 
		for (int i = 1; i < input.length; i++) {
			if (lastMax >= 0) {
				lastMax = lastMax + input[i];
				globalMax = Math.max(globalMax, lastMax);
			} else {
				lastMax = input[i];
				globalMax = Math.max(globalMax, lastMax);
			}
		}

		return globalMax;
	}


	/**
	 * return largest sum, left and right border
	 * space: O(1)
	 * use 4 temp vars: currLeft, currRight (i), globalLeft, globalRight
	 * when to update these vars?
	 *  	if lastMax < 0, reset currLeft = i
	 * 		else, currRight++ (i++)
	 * 
	 * 		if globalMax is updated, reset globalLeft = currLeft, globalRight = i
	 * could leave out currRight(i) and use 3 vars: currLeft, globalLeft, globalRight
	 * 
	 * */ 
	public static Result largestSumSubarrayIII(int[] input) {
		if (input == null || input.length == 0) {
			return new Result(0, 0, 0);
		}

		// base case
		int lastMax = input[0];
		int globalMax = input[0];
		
		int currLeft = 0;
		int globalLeft = 0;
		int globalRight = 0;
		// induction rule
		// m[i]: largestSumSubarray in 0...i including input[i]
		// m[i] = m[i - 1] + input[i] if m[i - 1] >= 0
		// m[i] = input[i] otherwise 
		for (int i = 1; i < input.length; i++) {
			if (lastMax >= 0) {
				lastMax = lastMax + input[i];
			} else {
				lastMax = input[i];
				currLeft = i;
			}
			
			if (lastMax > globalMax) {
				globalMax = lastMax;
				globalLeft = currLeft;
				globalRight = i;
			}	
		}

		return new Result(globalMax, globalLeft, globalRight);
	}

	public static void main(String[] args) {
		int[] a = {4,2,-3,-2,3,-1,-2,6};
		int[] b = {1, 2, 4, -1, -2, 10, -1, -100, -1, 10, 20};
		int[] c = {-2, -3, 1};
		System.out.println(largestSumSubarrayI(a));
		System.out.println(largestSumSubarrayI(b));
		System.out.println(largestSumSubarrayI(c));
		System.out.println();
		
		System.out.println(largestSumSubarrayII(a));
		System.out.println(largestSumSubarrayII(b));
		System.out.println(largestSumSubarrayII(c));
		System.out.println();

		System.out.println(largestSumSubarrayIII(a));
		System.out.println();
		System.out.println(largestSumSubarrayIII(b));
		System.out.println();
		System.out.println(largestSumSubarrayIII(c));
	}	
}


class Result {
	final int maxSum;
	final int leftIndex;
	final int rightIndex;

	public Result() {
		maxSum = 0;
		leftIndex = 0;
		rightIndex = 0;
	}
	
	public Result(int maxSum, int leftIndex, int rightIndex) {
		this.maxSum = maxSum;
		this.leftIndex = leftIndex;
		this.rightIndex = rightIndex;
	}
	
	@Override
	public String toString() {
		return "maxSum: " + maxSum + "\nleftIndex: " + leftIndex 
				+ "\nrightIndex: " + rightIndex;
	}
	
}
