package algorithms.binarysearch;

public class Closest {

	// return -1 if array is null or empty
	// if there are identical closest elements, return any index
	// 1 2 3 4 target = 5
	// l m r
	// m < target: l = m; otherwise, r = m
	// l < r won't work - infinit loop for rule l - m
	public static int closest(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int left = 0;
		int right = array.length - 1;
		while (left < right - 1) { // when left is next to right
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid; // cannot eliminate mid - it could be the solution
			} else {
				right = mid;
			}
		}

		if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
			return left;
		} else {
			return right;
		}
	}
}
