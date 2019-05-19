package algorithms.random;

import java.util.Random;

public class RandomShuffling {

	/**
	 * Given an array of integers (without any duplicates), shuffle the array such that 
	 * all permutations are equally likely to be generated.
	 * Assumptions
	 * The given array is not null
	 * 	processed(to be processed)
	 * 							root 123
	 * 					/		  |			\
	 * level 0		1(23)		2(13)		 3(12)
	 * 				/	\		/	\		 /  \
	 * level 1	12(3) 13(2)	  21(3) 23(1)  31(2) 31(1)	
	 * 			 |	   |       |	 |      |     |
	 * level 2	123   132	  213   231    312   311
	 * 
	 * data structure: 
	 * 		[0,i) already shuffled || [i,array.length - 1] not shuffled yet
	 * initialization: index = 0
	 * for each step
	 * 	1. uniformly and randomly pick one card[j] from unshuffled section
	 *  2. swap card[index] and card[j]
	 *  3. index++
	 * */
	public static void randomShuffle(int[] array) {
		if (array == null|| array.length <= 1) {
			return;
		}
		
		// Math.random() returns double [0...1)
		// random in [a...b]: (b - a + 1) 0...b-a + a
		for (int i = 0; i < array.length; i++) {
			Random rand = new Random();
			int randomIndex = i + rand.nextInt(array.length - i); 
			swap(array, i, randomIndex);
		}
	}
	
	/**
	 * k permutations
	 * n = 5, [1,2,3,4,5]
	 * k = 3
	 * [1,2,3], [3,2,1], [2,4,5]...
	 * in array of size n, move k steps, solution in subarray [0...k-1]
	 * in recursion tree, go down k levels
	 * */
	public static int[] randomShuffle(int[] array, int k) {
		if (array == null|| array.length <= 1 || k <= 0 || k > array.length) {
			return new int[0];
		}
		
		int[] result = new int[k];
		for (int i = 0; i < k; i++) {
			Random rand = new Random();
			int randomIndex = i + rand.nextInt(array.length - i); 
			swap(array, i, randomIndex);
			result[i] = array[i];
		}
		return result; 
	}
	
	private static void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6};
		randomShuffle(array);
		util.Util.printArray(array);
		
		util.Util.printArray(randomShuffle(array, 3));
	}
}
