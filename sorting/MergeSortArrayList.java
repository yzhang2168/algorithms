package algorithms.sorting;

import java.util.ArrayList;

public class MergeSortArrayList {
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> array) {
		// corner
		if (array == null || array.size() <= 1) {
			return array;
		}
		// create a temp array for merge()
		// this ensures space complexity is O(n)
		// do not create a new temp array in each stack frame
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < array.size(); i++) {
			temp.add(null);
		}
		mergeSort(array, temp, 0, array.size() - 1);
		return array;
	}

	private static void mergeSort(ArrayList<Integer> array, ArrayList<Integer> temp, int left, int right) {
		// base case
		if (left >= right) {
			return;
		}

		// divide
		int mid = left + (right - left) / 2;
		mergeSort(array, temp, left, mid);
		mergeSort(array, temp, mid + 1, right);

		// conquer
		merge(array, temp, left, mid, right);	
	}

	private static void merge(ArrayList<Integer> array, ArrayList<Integer> temp, int left, int mid, int right) {
		// copy array[left...right] to temp array and then merge from temp
		// array[left...mid] and array[mid + 1...right] are already sorted
		for (int x = left; x <= right; x++) {
			temp.set(x, array.get(x));
		}
		
		int i = left;
		int j = mid + 1;
		int k = left;
		// copy the smaller between left and right subarrays to the input array
		while (i <= mid && j <= right) {
			if (temp.get(i) <= temp.get(j)) {
				array.set(k++, temp.get(i++));
			} else {
				array.set(k++, temp.get(j++));
			}
		}

		// if left subarray has elements left
		while (i <= mid) {
			array.set(k++, temp.get(i++));
		}

		// if right has remaining elements, they are in right place, copying is not needed
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(4);
		array.add(2);
		array.add(3);
		array.add(1);
		array.add(0);
		System.out.println(array);
		mergeSort(array);
		System.out.println(array);		
	}
}
