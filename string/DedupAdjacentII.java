package string;

import java.util.Arrays;

public class DedupAdjacentII {

	/**
	 * Given an integer array(not guaranteed to be sorted), remove adjacent repeated
	 * elements. For each group of elements with the same value keep at most two of
	 * them. Do this in-place, using the left side of the original array and
	 * maintain the relative order of the elements of the array. Return the final
	 * array.
	 * 
	 * Assumptions
	 * 
	 * The given array is not null Examples 
	 * {1, 2, 2, 3, 3, 3} --> {1, 2, 2, 3, 3}
	 * {2, 1, 2, 2, 2, 3} --> {2, 1, 2, 2, 3}
	 */
	public int[] dedup(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		// [0,s]: result
		// {2, 1, 2, 2, 3, 3}
		// s
		// f
		int s = 0;
		int f = 1;
		while (f < array.length) {
			if (array[f] != array[s]) {
				array[++s] = array[f++];
			} else { // ==
				while (f < array.length && array[f] == array[s]) {
					f++;
				}
				array[++s] = array[s];
			}
		}
		return Arrays.copyOf(array, s + 1);
	}
	
	public int[] dedupII(int[] array) {
		// corner case needs to go with s & f semantics, so that we cover everything
		if (array == null || array.length <= 2) {
			return array;
		}
		// [0, s - 1]: result
		// {2, 1, 2, 2, 3, 3}
		// s
		// f
		int s = 2;
		int f = 2;
		while (f < array.length) {
			if (array[f] != array[s - 2]) {
				array[s++] = array[f++];
			} else { // a[f] == a[s - 2], and a[f] == a[s - 1], not copy
				f++;
			}
		}
		return Arrays.copyOf(array, s);
	}
}
