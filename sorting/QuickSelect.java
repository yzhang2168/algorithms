package sorting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuickSelect {
	/**
	 * find the smallest k items in an unsorted array
	 * they do not to be in ascending/descending order
	 * assumption: k in range [0...n-1]
	 * partition the array using a random pivot
	 * recursively partition one subarray as needed
	 * time : average O(n), worst O(n^2)
	 * space: average O(log n), worst O(n)
	 * */ 
	public static List<Integer> quickSelect(int[] array, int k) {
		List<Integer> result = new ArrayList<Integer>();
		// corner case
		if (array == null || array.length == 0 || k <= 0) {
		    return result;	
		}
				
		quickSelect(array, k - 1, 0, array.length - 1);

		for (int i = 0; i < k; i++) {
			result.add(array[i]);
		}
		
		// to output a sorted list 
		Collections.sort(result);
		return result;
	}

	private static void quickSelect(int[] array, int k, int left, int right) {
		if (left >= right) {
			return;
		}

		int pivotFinal = partition(array, left, right);
		
		if (k == pivotFinal) {
			return;
		} else if (k < pivotFinal) {
			quickSelect(array, k, left, pivotFinal - 1);
		} else { // pivotFinal > k
			quickSelect(array, k, pivotFinal + 1, right);
		}
	}

	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	private static int partition(int[] array, int left, int right) {
		// select a pivot
		Random random = new Random();
		int pivotIndex = left + random.nextInt(right - left + 1);
		int pivotValue = array[pivotIndex];

		// put pivot to the last
		swap(array, pivotIndex, right);
		
		// partition
		int i = left, j = right - 1;
		while (i <= j) {
			if (array[i] < pivotValue) {
				i++;			
			} else if (array[j] >= pivotValue) {
				j--;
			} else { // array[i] >= pivotValue && array[j] < pivotValue
				swap(array, i, j);
				i++;
				j--;
			}
		}
		
		// put pivot into final position
		swap(array, i, right);
		return i; // pivot index
	}
}
