package binarysearch;

public class LargestSmallerThanTarget {

	/**
	 * sorted array 3 5 target = 3 or 4 try (l < r): 2 elements --> update l = m -->
	 * not moving, infinite loop
	 * 
	 * try (l < r - 1): 3 elements l x r --> update --> becomes 1 or 2 elements
	 */
	public static int largestSmallerThanTarget(int[] input, int target) {
		if (input == null || input.length == 0) {
			return -1;
		}

		int l = 0;
		int r = input.length - 1;
		while (l < r - 1) { // lxr
			int m = l + (r - l) / 2;
			if (input[m] >= target) {
				r = m - 1;
			} else {
				l = m; // while (l < r), l == m so l does not move, infinite loop, r = m is okay
			}
		}

		// 1 or 2 elements left to explore
		if (input[r] < target) {
			return r;
		} else if (input[l] < target) {
			return l;
		} else {
			return -1;
		}
	}
}
