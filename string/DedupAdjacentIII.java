package string;

import java.util.Arrays;

public class DedupAdjacentIII {

	/**
	 * Given a sorted integer array, remove duplicate elements. For each group of
	 * elements with the same value do not keep any of them. Do this in-place, using
	 * the left side of the original array and and maintain the relative order of
	 * the elements of the array. Return the array after deduplication. 
	 * {1, 2, 2, 3, 3, 3} → {1}
	 * 
	 * we cannot use s and f only to tell if a char should be kept or not
	 * we need to count identical chars after f
	 * so we introduce a new variable next_f: 1st letter after f && different from array[f]
	 * 
	 * for each step
	 * 1. next_f = f; keep doing next_f++ until array[next_f] != array[f]
	 * 2. case 1: next_f - f == 1, keep, a[s] = a[f], s++, f = next_f or f++
	 * 	  case 2: next_f - f > 1, f = next_f
	 * 
	 * comparison condition is between next_f and f; has nothing to do with s
	 */
	public int[] dedup(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		
		int s = 0;
		int f = 0;
		int next_f = 0;
		while (f < array.length) {
			while (next_f < array.length && array[next_f] == array[f]) {
				next_f++;
			}
			
			if (next_f - f == 1) {
				array[s++] = array[f];
			}			
			f = next_f;			
		}
		
		return Arrays.copyOf(array, s);
	}
}
