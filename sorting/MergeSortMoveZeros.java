package algorithms.sorting;

public class MergeSortMoveZeros {

	// move zeros to the right and retain the order of non-Os 
	public static int[] moveZeros(int[] array) {
		// corner
		if (array == null || array.length <= 1) {
			return array;
		}

		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != 0) {
				array[index++] = array[i];
			}
		}
		
		// reset to 0 
		for (int i = index; i < array.length; i++) {
			array[i] = 0;
		}
		
		return array;
	}


	public static int[] mergeSortMoveZeros(int[] array) {
		// corner
		if (array == null || array.length <= 1) {
			return array;
		}
		// create a temp array for merge()
		// this ensures space complexity is O(n)
		// do not create a new temp array in each stack frame
		int[] temp = new int[array.length];
		mergeSortMoveZeros(array, temp, 0, array.length - 1);
		return array;
	}

	private static void mergeSortMoveZeros(int[] array, int[] temp, int left, int right) {
		// base case
		if (left == right) {
			return;
		}

		// divide
		int mid = left + (right - left) / 2;
		mergeSortMoveZeros(array, temp, left, mid);
		mergeSortMoveZeros(array, temp, mid + 1, right);

		// conquer
		mergeMoveZeros(array, temp, left, mid, right);	
	}

	private static void mergeMoveZeros(int[] array, int[] temp, int left, int mid, int right) {
		// copy array[left...right] to temp array and then merge from temp
		// array[left...mid] and array[mid + 1...right] are already sorted
		for (int x = left; x <= right; x++) {
			temp[x] = array[x];
		}

		int i = left;
		int j = mid + 1;
		int k = left;

		while (i <= mid) {
			if (temp[i] != 0) {
				array[k] = temp[i];
				k++;
			}
			i++;
		}

		while (j <= right) {
			if (temp[j] != 0) {
				array[k] = temp[j];
				k++;
			} 	
			j++;
		}

		// reset remaining values to 0s
		while (k <= right) {
			array[k++] = 0;
		}	
	}	

	public static void main(String[] args) {
		int[] arr0 = {8, 0, 2, 0, 1};
		util.Util.printArray(moveZeros(arr0));
		//util.Util.printArray(mergeSortMoveZeros(arr0));

		int[] arr2 = {1, 0, -1, 1, 0, -1, 0, 0, -1};
		util.Util.printArray(moveZeros(arr2));
		//util.Util.printArray(mergeSortMoveZeros(arr2));
	}
}
