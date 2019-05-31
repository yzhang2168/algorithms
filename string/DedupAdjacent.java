package string;

import java.util.Arrays;

public class DedupAdjacent {

	/**
	 * abbbaaazw -> abazw
	 * */
	public static String dedupAdjacentLetters(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		char[] array = input.toCharArray();
		// [0...slow-1]: result
		int slow = 1;
		for (int fast = 1; fast < array.length; fast++) {
			if (array[fast] != array[slow - 1]) {
				array[slow++] = array[fast];
			}
		}
		return new String(array, 0, slow); // [0, slow -1]
	}
	
	/**
	 * abbbazw -> zw
	 * */
	public static String dedupAdjacentLettersRepeatedly(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		char[] array = input.toCharArray();
		// [0...end]: stack		
		int end = 0; // now stack has 1 element
		for (int fast = 1; fast < array.length; fast++) {
			// end == -1: stack is empty
			if (end == -1 || array[fast] != array[end]) {
				array[++end] = array[fast];
			} else { // array[fast] == array[slow] / stack top
				while (fast < array.length - 1 && array[fast + 1] == array[fast]) {
					fast++;
				}
				// fast == array.length - 1 || array[fast + 1] != array[fast]
				// array[fast] is the last == array[slow]
				end--;
			}
		}
		return new String(array, 0, end + 1); // [0, slow -1]
	}
	
	/**
	 * Given an integer array(not guaranteed to be sorted), remove adjacent repeated elements. For each group of elements with the same value keep at most two of them. Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. Return the final array.

		Assumptions
		
		The given array is not null
		Examples
		
		{1, 2, 2, 3, 3, 3} --> {1, 2, 2, 3, 3}
		{2, 1, 2, 2, 2, 3} --> {2, 1, 2, 2, 3}  
	 * */
	public int[] dedup(int[] array) {
	      //[0,s]: stack
	      // empty: -1
	      // {2, 1, 2, 2, 3, 3} 
	      //              s
	      //                   f
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

	public static void main(String[] args) {
		System.out.println(5 ^ 6); // 3
		System.out.println(dedupAdjacentLetters("aaabbazwww"));
		System.out.println(dedupAdjacentLettersRepeatedly("abbbazw"));
	}
}
