package comparison;

import java.util.Arrays;


public class LargestSmallest {

	public int[] largestAndSmallest(int[] array) {
		int n = array.length;
		if (n <= 1) {
			return new int[] { array[0], array[0] };
		}
		// 0 1 2 n = 3
		// i
		// w
		// l
		int[] winners = new int[n / 2];
		int[] losers = new int[n / 2];
		// odd number of elements, leave out the last one a[n-1]
		for (int i = 0, j = 0; i < n - 1; i += 2, j++) {
			if (array[i] < array[i + 1]) {
				losers[j] = array[i];
				winners[j] = array[i + 1];
			} else {
				losers[j] = array[i + 1];
				winners[j] = array[i];
			}
		}
		
		int max = winners[0];
		for (int i = 1; i < n / 2; i++) {
			max = Math.max(max, winners[i]);
		}

		int min = losers[0];
		for (int i = 1; i < n / 2; i++) {
			min = Math.min(min, losers[i]);
		}

		if (n % 2 == 1) {
			max = Math.max(max, array[n - 1]);
			min = Math.min(min, array[n - 1]);
		}

		return new int[] {max, min};
	}
	
	/**
	 * 0 1 2 3 4
	 * 1 2 3 3 3
	 * i
	 *         j
	 * 
	 * 0 1 2 3 4
	 * 3 3 3 2 1
	 *       i
	 *   j
	 * 
	 * 0 1 2 3 4 5
	 * 3 3 3 2 1 1
	 *       i
	 *     j
	 * 
	 * */
	public int[] largestAndSmallestII(int[] array) {
		int i = 0;
		int j = array.length - 1;
		while (i <= j) {
			if (array[i] < array[j]) {
				swap(array, i, j);
			}
			i++;
			j--;
		}
		
		// winners: [0,i-1]
		// losers: [j+1,n-1]
		int max = getMax(array, 0, i - 1);
		int min = getMin(array, j + 1, array.length - 1);
		return new int[] {max, min};
	}
	
	private int getMax(int[] array, int start, int end) {
		int max = array[start];
		for (int i = start + 1; i <= end; i++) {
			max = Math.max(max, array[i]);
		}
		return max;
	}
	
	private int getMin(int[] array, int start, int end) {
		int min = array[start];
		for (int i = start + 1; i <= end; i++) {
			min = Math.min(min, array[i]);
		}
		return min;
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public int[] largestAndSmallestIII(int[] array) {
		int n = array.length;
		// if n is odd, the mid element is not compared
		for (int i = 0; i < n / 2; i++) {
			if (array[i] < array[n - 1 - i]) {
				swap(array, i, n - 1 - i);
			}
		}

		int max = getMax(array, 0, (n - 1) / 2); // this works for both even and odd numbers
		int min = getMax(array, n / 2, n - 1);
		return new int[] {max, min};
	}

	public static void main(String[] args) {
		LargestSmallest test = new LargestSmallest();
		int[] input = new int[]{1,2,3,3,3};
		int[] output = test.largestAndSmallestII(input);
		System.out.println(Arrays.toString(output));
	}
}
