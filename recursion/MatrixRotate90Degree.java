package algorithms.recursion;

import java.util.Arrays;

/**
 * given a 2d array n x n, in place rotate 90 degree
 *  1  2  3  4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * 
 * [0][0] -> [0][n-1] -> [n-1][n-1] -> [n-1][0]
 * [0][1] -> [1][n-1] -> [n-1][n-2]
 * each time: one circle
 * */
public class MatrixRotate90Degree {

	public static void rotateIteration(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}
		
		int start = 0;
		int end = input.length - 1;
		
		while (start < end) {
			for (int i = start; i < end; i++) {
				int temp = input[start][i];
				
				input[start][i] = input[end - i][start];
				input[end - i][start] = input[end][end - i];
				input[end][end - i] = input[start + i][end];
				input[start + i][end] = temp;
			}
			start++;
			end--;
			//offset++;
		}
		
		// 0 or 1 element left, do nothing
		if (start == end) {
			// do nothing
			return;
		}
	}
	
	
	public static void rotateRecursion(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}
		
		int start = 0;
		int end = input.length - 1;
	
		rotateRecursion(input, start, end);
	}	
	
	private static void rotateRecursion(int[][] input, int start, int end) {
		if (start >= end) {
			return;
		}
		
		for (int i = start; i < end; i++) {
			int temp = input[start][i];
			input[start][i] = input[end - i][start];
			input[end - i][start] = input[end][end - i];
			input[end][end - i] = input[start + i][end];
			input[start + i][end] = temp;
		}
		
		rotateRecursion(input, start + 1, end - 1);
	}	
	
	public static void main(String[] args) {
		int[][] test1 = null;
		rotateIteration(test1);
		System.out.println(Arrays.deepToString(test1));
		
		int[][] test2 = new int[0][0]; 
		rotateIteration(test2);
		System.out.println(Arrays.deepToString(test2));
		
		int[][] test3 = new int[][]{{1}}; 
		rotateIteration(test3);
		System.out.println(Arrays.deepToString(test3));
		
		int[][] test4 = new int[][] {{1,2,3,4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}; 
		System.out.println(Arrays.deepToString(test4));
		rotateIteration(test4);
		System.out.println(Arrays.deepToString(test4));
		/*
		 *  1  2  3  4 
		 * 12 13 14  5 
		 * 11 16 15  6
		 * 10  9  8  7

		 * 10 11 12  1 
		 *  9 16 13  2 
		 *  8 15 14  3
		 *  7  6  5  4
		 * */
		
		int[][] test5 = new int[][] {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}}; 
		System.out.println(Arrays.deepToString(test5));
		rotateIteration(test5);
		System.out.println(Arrays.deepToString(test5));
		
		int[][] test6 = new int[][] {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}}; 
		//System.out.println(Arrays.deepToString(test6));
		rotateRecursion(test6);
		System.out.println(Arrays.deepToString(test6));

	}

	
}
