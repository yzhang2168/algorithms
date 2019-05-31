package algorithms.binarysearch;

public class LastOccurrence {

	public static int lastOccurrence(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}

		int left = 0;
		int right = array.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				left = mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		// left >= right - 1, 1-2 left
		if (array[right] == target) {
			return right;
		} else if (array[left] == target) {
			return left;
		} else {
			return -1;
		}

	}

}
