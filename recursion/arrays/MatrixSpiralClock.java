package recursion.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * given a 2d array n x n, fill out the array in spiral order
 *  1  2  3  4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * O(n)
 * */
public class MatrixSpiralClock {

	public static void spiralRecursion(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}

		spiralRecursion(input, 0, input[0].length, 1);
	}

	private static void spiralRecursion(int[][] input, int offset, int size, int counter) {
		// base 1: input.length is even, 0 element left
		if (size == 0) {
			return;
		}

		// base 2: input.length is odd, 1 element left
		if (size == 1) {
			input[offset][offset] = counter;
			return;
		}

		// top
		// each round, print size - 1 elements: 5 x 5 matrix, outer loop prints 4 elements 1 2 3 4 | 5
		// need to finish top row before doing right col, so 4 for loops
		for (int i = 0; i < size - 1; i++) {
			input[0 + offset][i + offset] = counter++;
		}
		
		// right
		for (int i = 0; i < size - 1; i++) {
			input[i + offset][offset + size - 1] = counter++;
		}

		// bottom
		for (int i = size - 1; i >= 1; i--) {
			input[offset + size - 1][i + offset] = counter++;
		}

		// left
		for (int i = size - 1; i >= 1; i--) {
			input[i + offset][offset] = counter++;
		}

		// tail recursion: can be converted to iterative
		spiralRecursion(input, offset + 1, size - 2, counter);		
	}


	public static List<Integer> spiralPrintRecursion(int[][] input) {
		List<Integer> result = new ArrayList<Integer>();

		if (input == null || input.length == 0 || input.length != input[0].length) {
			return result;
		}

		spiralPrintRecursion(input, 0, input[0].length, result);
		return result;
	}


	private static void spiralPrintRecursion(int[][] input, int offset, int size, List<Integer> result) {
		// base 1: input.length is even, 0 element left
		if (size == 0) {
			return;
		}

		// base 2: input.length is odd, 1 element left
		if (size == 1) {
			result.add(input[offset][offset]);
			return;
		}

		// top
		// each round, print size - 1 elements: 5 x 5 matrix, outer loop prints 4 elements 1 2 3 4 | 5
		for (int i = 0; i < size - 1; i++) {
			result.add(input[0 + offset][i + offset]);
		}
		// right
		for (int i = 0; i < size - 1; i++) {
			result.add(input[i + offset][offset + size - 1]);
		}

		// bottom
		for (int i = size - 1; i >= 1; i--) {
			result.add(input[offset + size - 1][i + offset]);
		}

		// left
		for (int i = size - 1; i >= 1; i--) {
			result.add(input[i + offset][offset]);
		}

		spiralPrintRecursion(input, offset + 1, size - 2, result);		
	}


	public List<Integer> spiralTraversal (int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length) {
			return result;
		}

		//onion peeling, one circle at a time    
		spiralRecursive(matrix, result, 0, matrix.length - 1);
		return result;
	}

	private void spiralRecursive(int[][] matrix, List<Integer> result, int start, int end) {
		if (start > end) {
			return;
		}

		if (start == end) {
			result.add(matrix[start][start]);
			return;
		}

		// each time, we copy size - 1 elements
		// top
		for (int i = start; i < end; i++) {
			result.add(matrix[start][i]);
		}

		// right
		for (int i = start; i < end; i++) {
			result.add(matrix[i][end]);
		}

		// bottom
		for (int i = end; i > start; i--) {
			result.add(matrix[end][i]);
		}

		// left
		for (int i = end; i > start; i--) {
			result.add(matrix[i][start]);
		}

		spiralRecursive(matrix, result, start + 1, end - 1);
	}   
	

	public static void main(String[] args) {
		int[][] test1 = null; 
		spiralRecursion(test1);
		System.out.println(Arrays.deepToString(test1));
		System.out.println();

		int[][] test2 = new int[0][0]; 
		spiralRecursion(test2);
		System.out.println(Arrays.deepToString(test2));
		System.out.println();

		int[][] test3 = new int[1][1]; 
		spiralRecursion(test3);
		System.out.println(Arrays.deepToString(test3));
		System.out.println();

		int[][] test4 = new int[4][4]; 
		spiralRecursion(test4);
		System.out.println(Arrays.deepToString(test4));
		System.out.println();

		int[][] test5 = new int[5][5]; 
		spiralRecursion(test5);
		System.out.println(Arrays.deepToString(test5));
		System.out.println(spiralPrintRecursion(test5));
	}
}
