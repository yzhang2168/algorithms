package algorithms.sorting;
import util.Util;

public class SelectionSort {
	/** use i to mark boundaries
	 *		left...i) sorted
	 * 		[i...right] unsorted
	 * 		i: 0... n - 2, 2nd to last
	 *  use j to traverse the unsorted region to find minIndex
	 * 		j: i + 1... n - 1
	 *  time:  O(n^2): 1+2+3+...+n = n(n+1) / 2 
	 *  space: O(1): heap 0, stack 2-3 levels
	 */
	public static void selectionSort(int[] array) {
		// corner cases
		if (array == null || array.length == 0) {
			return;
		}
		
		int n = array.length;

		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			swap(array, i, minIndex);
		}
	}
	
	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
}
