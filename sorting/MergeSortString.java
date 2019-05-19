package algorithms.sorting;

public class MergeSortString {
	/**
	 * Example
	 * A1B2C3D4 --> ABCD1234
	 * */
	public static String mergeSort(String s) {
		char[] unsorted = s.toCharArray();		
		char[] sorted = mergeSort(unsorted);
		return sorted.toString();
	}

	public static char[] mergeSort(char[] array) {
		// corner
		if (array == null || array.length <= 1) {
			return array;
		}

		// create a temp array for merge()
		// this ensures space complexity is O(n)
		// do not create a new temp array in each stack frame
		char[] temp = new char[array.length];
		mergeSort(array, temp, 0, array.length - 1);
		return array;
	}

	private static void mergeSort(char[] array, char[] temp, int left, int right) {
		// base case
		if (left == right) {
			return;
		}

		// divide
		int mid = left + (right - left) / 2;
		mergeSort(array, temp, left, mid);
		mergeSort(array, temp, mid + 1, right);

		// conquer
		merge(array, temp, left, mid, right);	
	}

	private static void merge(char[] array, char[] temp, int left, int mid, int right) {
		// copy array[left...right] to temp array and then merge from temp
		// array[left...mid] and array[mid + 1...right] are already sorted
		for (int x = left; x <= right; x++) {
			temp[x] = array[x];
		}

		int i = left;
		int j = mid + 1;
		int k = left;
		// copy the smaller between left and right subarrays to the input array
		while (i <= mid && j <= right) {
			if (temp[i] <= temp[j]) {
				array[k++] = temp[i++];
			} else {
				array[k++] = temp[j++];
			}
		}

		// if left subarray has elements left
		while (i <= mid) {
			array[k++] = temp[i++];
		}

		// if right has remaining elements, they are in right place, copying is not needed
	}	
}
