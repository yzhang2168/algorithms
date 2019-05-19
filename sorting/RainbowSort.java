package algorithms.sorting;

public class RainbowSort {
	public static int[] rainbowSort(int[] array) {
		if (array == null || array.length <= 1) {
			return array;
		}
		
		/** 
		 * similar to the partition step in quicksort 
		 * use 3 pointers i, j, k to divide the array into 4 subarrays
		 * 	[0...i): a/-1
		 *  [i...j): b/0
		 *  [j...k]: unsorted region
		 *  (k...n-1]: c/1
		 * j is the current index 
		 * time: 
		 * space: 
		 * */
		int i = 0;
		int j = 0;
		int k = array.length - 1;
		
		// when j meets k, current element is not examined yet
		while (j <= k) {
			if (array[j] == -1) {
				swap(array, i, j);
				i++;
				j++;				
			} else if (array[j] == 0) {
				j++;				
			} else if (array[j] == 1) {
				swap(array, j, k);
				k--;
			} else {
				throw new java.lang.Error("array contains unexpected value: " + array[j]);
			}
		}
		return array;
	}
	
	private static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
