package algorithms.sorting;

public class QuickSort {

	public static void quickSort(int[] array) {
		// corner case
		if (array == null || array.length == 0) {
			return;
		}
		
		quickSort(array, 0, array.length - 1);
	}
	
	public static int[] quickSort2(int[] array) {
		// corner case
		if (array == null || array.length == 0) {
			return array;
		}
		
		quickSort(array, 0, array.length - 1);
		return array;
	}
	
	private static void quickSort(int[] array, int left, int right) {
		/**base case
		 * 0 element: pivotIndex == left or right, left > right 
		 * 1 element: left == right
		 */
		if (left >= right) {
			return;
		}
		// divide
		int pivotIndex = partition(array, left, right);
		// conquer
		quickSort(array, left, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, right);
	}
	
	private static int partition(int[] array, int left, int right) {
		// pick a random pivot and swap it with the last array element
		int randomIndex = left + (int) (Math.random() * (right - left + 1));
		swap(array, randomIndex, right);
		
		/** use leftBound and rightBound 
		 * Use two pointers 挡板 leftBound and rightBound to sandwich unsorted region
		 * [leftBound...rightBound], sandwich the items to be sorted
		 * [left...leftBound) < pivot: Move leftBound from left to right as long as array[leftBound] < pivot
		 * (rightBound...right] >= pivot: Move rightBound from right to left as long as array[rightBound] >= pivot
		 * After partitioning, pivot is in its final position.
		 * 
		 * time:  average O(nlogn), worst O(n^2)
		 * space: average O(logn), worst O(n)
     	 */
		int pivot = array[right];
		int leftBound = left;
		int rightBound = right - 1;
		
		// when leftBound meets rightBound, current element not examined yet
		while (leftBound <= rightBound) {
			if (array[leftBound] < pivot) {
				leftBound++;
			} else if (array[rightBound] > pivot) {
				rightBound--;
			} else { // array[leftBound] >= pivot && array[rightBound] < pivot
				swap(array, leftBound++, rightBound--);
			}
		}
		// after partition, leftBound and rightBound crossed, leftBound == rightBound + 1
		// put pivot into its final position
		swap(array, leftBound, right);		
		return leftBound; 
	}
	
	private static void swap(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	public static void main(String[] args) {
		int[] arr1 = {2, 1, 1, 2, 3};
		int[] arr2 = {1, 2, 1, 3, 2};
		quickSort(arr1);
		util.Util.printArray(arr1);
		quickSort(arr2);
		util.Util.printArray(arr2);
		
	}
}
