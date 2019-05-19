package algorithms.sorting;

public class MergeSort {
	/**
	 * 1. divide into 2 subarrays recursively
	 * 		[left...mid]
	 * 		[mid+1...right]		
	 * 		mid should be included into the left subarray because Java takes floor of (right - left)/2
	 * 		otherwise, if mid is odd, array is not divided into 2 smaller subarrays - infinite loop 
	 * 2. merge
	 * 		copy input array to a temp array and then modify original
	 * 		use i to track left subarray [left...mid]
	 * 		use j to track right subarray [mid+1...right]		
	 * 		compare i and j to put smaller into array[k]
	 * 		use k to mark sorted region: [left...k) sorted
	 * 		if there are remaining elements in left subarray, copy them
	 * 		if there are remaining elements in right subarray, no need to copy them 
	 * 			b/c they are already in their right position.
	 * time:  O(nlogn)
	 * space: O(n) helper array
	 * */

	public static int[] mergeSort(int[] array) {
		// corner
		if (array == null || array.length <= 1) {
			return array;
		}
		// create a temp array for merge()
		// this ensures space complexity is O(n)
		// do not create a new temp array in each stack frame
		int[] temp = new int[array.length];
		mergeSort(array, temp, 0, array.length - 1);
		return array;
	}

	private static void mergeSort(int[] array, int[] temp, int left, int right) {
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

	private static void merge(int[] array, int[] temp, int left, int mid, int right) {
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
