package algorithms.binarysearch;

/**
 * turning point < m <--> input[m] < input[r] so [m...r] is sorted [5,0,1,2,3,4]
 * l m r
 * 
 * turning point > m <--> input[m] > input[r] so [l...m] is sorted [2,3,4,5,0,1]
 * target = 2 l m r
 * 
 * case 1. input[m] == target, return case 2. input[m] < input[r]: turning point
 * < m --> [m...r] is sorted 2.1 if input[m] < target <= input[r], look in the
 * right part 2.2 else, look in the left case 3. input[m] > input[r]: turning
 * point > m --> [l...m] is sorted 3.1 if input[l] <= target < input[m], look in
 * the left 3.2 else, look in the right
 */

public class TargetInRotatedSortedArray {

	public static int targetInRotatedSortedArray(int[] input, int target) {
		if (input == null || input.length == 0) {
			return -1;
		}

		int l = 0;
		int r = input.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (input[m] == target) {
				return m;
			} else if (input[m] < input[r]) {
				if (input[m] < target && target <= input[r]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			} else { // if (input[m] > input[r]) {
				if (input[l] <= target && target < input[m]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println('I' - 33);
		System.out.println(targetInRotatedSortedArray(new int[] { 6, 7, 8, 9, 1, 2 }, 1));
		System.out.println(targetInRotatedSortedArray(new int[] { 6, 7, 8, 9, 1, 2 }, 6));
		System.out.println(targetInRotatedSortedArray(new int[] { 9, 1, 2, 6, 7, 8 }, 1));
		System.out.println(targetInRotatedSortedArray(new int[] { 9, 1, 2, 6, 7, 8 }, 6));
	}

}
