package binarysearch;

import java.util.Arrays;

public class ClosestKToTarget {
	/**
	 * closest k elements to target binary search + 谁小移谁 O(log n + k), return k
	 * elements in order of distance to target binary search + binary search array
	 * of distance from target O(log n + log k), return {left bound, right bound} 1
	 * 2 3 8 9 l r target = 4 k = 4 {3,2,1,8}
	 */
	public static int[] closestKElements(int[] array, int target, int k) {
		if (array == null || array.length == 0 || k <= 0 || k > array.length) {
			return new int[0]; // empty
		}

		int left = largestSmallerEqual(array, target);
		int right = left + 1;
		// if the entire array > target, left = -1, right = 0
		int[] result = new int[k];

		for (int i = 0; i < k; i++) {
			// 谁小移谁
			// group logic for cases where we move left:
			// 1. right is out of bound
			// 2. both left and right are in bound, but array[left] is closer to target
			if (right >= array.length || left >= 0 && (target - array[left] < array[right] - target)) {
				result[i] = array[left];
				left--;
				// move right when right is in bound
				// case 1: left is out of bound
				// case 2: array[right] is closer to target
			} else {
				result[i] = array[right];
				right++;
			}
		}
		return result;
	}

	private static int largestSmallerEqual(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int left = 0;
		int right = array.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (array[mid] <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}

		if (array[right] <= target) {
			return right;
		} else if (array[left] <= target) {
			return left;
		} else {
			return -1;
		}
	}

	public static int[] kClosest(int[] array, int target, int k) {
		if (array == null || array.length == 0 || k <= 0) {
			return new int[0];
		}

		if (k > array.length) {
			k = array.length;
		}

		int closestIndex = findClosest(array, target); // >= 0
		int[] result = new int[k];
		result[0] = array[closestIndex];
		int l = closestIndex - 1;
		int r = closestIndex + 1;

		for (int i = 1; i < k; i++) {
			if (r >= array.length || (l >= 0 && Math.abs(target - array[l]) < Math.abs(target - array[r]))) {
				result[i] = array[l--];
			} else {
				result[i] = array[r++];
			}
		}

		return result;
	}

	private static int findClosest(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int l = 0;
		int r = array.length - 1;
		while (l < r - 1) {
			int m = l + (r - l) / 2;
			if (array[m] == target) {
				return m;
			} else if (array[m] < target) {
				l = m;
			} else {
				r = m;
			}
		}

		// 1 or 2 elements
		if (Math.abs(target - array[l]) < Math.abs(target - array[r])) {
			return l;
		} else {
			return r;
		}
	}

	/**
	 * closest k elements to target binary search + 谁小移谁 O(log n + k) binary search
	 * + binary search array of distance from target O(log n + log k), return {left
	 * bound, right bound} 1 2 3 8 9 l r target = 4 k = 4 {3,2,1,8}
	 */
	public static int[] closestKElementsBinary(int[] array, int target, int k) {
		if (array == null || array.length == 0 || k <= 0 || k > array.length) {
			return new int[0];
		}

		int left = largestSmallerEqual(array, target);
		int right = left + 1;
		// if the entire array > target, left = -1, right = 0
		int kth = kthClosest(array, target, k, left, right);
		if (kth <= left) {
			return new int[] { kth, kth + k - 1 };
		} else {
			return new int[] { kth - k + 1, kth };
		}
	}

	private static int kthClosest(int[] array, int target, int k, int left, int right) {
		// base case
		if (left < 0) {
			return right + k - 1;
		} else if (right >= array.length) {
			return left - k + 1;
		} else if (k == 1) {
			return target - array[left] < array[right] - target ? left : right;
		}

		int leftMid = left - k / 2 + 1;
		int rightMid = right + k / 2 - 1;
		int leftVal = leftMid >= 0 ? target - array[leftMid] : Integer.MAX_VALUE;
		int rightVal = rightMid < array.length ? array[rightMid] - target : Integer.MAX_VALUE;

		if (leftVal < rightVal) {
			return kthClosest(array, target, k - k / 2, leftMid - 1, right);
		} else {
			return kthClosest(array, target, k - k / 2, left, rightMid + 1);
		}
	}

	public static void main(String[] args) {
		int[] input = { 1, 1, 2, 6, 7 };
		int[] result = closestKElements(input, 5, 4);
		int[] result1 = kClosest(input, 5, 4);
		int[] result2 = closestKElementsBinary(input, 5, 2);
		Arrays.toString(result);
		Arrays.toString(result1);
		System.out.print("closest k elements left index, right index: ");
		Arrays.toString(result2);
	}
}
