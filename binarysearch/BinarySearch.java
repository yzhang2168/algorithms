package algorithms.binarysearch;

public class BinarySearch {

	public static int binarySearch(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
		}
		return binarySearch(array, target, 0, array.length - 1);
	}

	private static int binarySearch(int[] array, int target, int left, int right) {
		if (left > right) {
			return -1;
		}

		int mid = left + (right - left) / 2;
		if (array[mid] == target) {
			return mid;
		} else if (array[mid] < target) {
			return binarySearch(array, target, mid + 1, right);
		} else {
			return binarySearch(array, target, left, mid - 1);
		}
	}
}
