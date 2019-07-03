package recursion;

import java.util.Arrays;

/**
 * given a 2d array n x n, in place rotate 90 degree 1 2 3 4 5 16 17 18 19 6 15
 * 24 25 20 7 14 23 22 21 8 13 12 11 10 9
 * 
 * [0][0] -> [0][n-1] -> [n-1][n-1] -> [n-1][0] [0][1] -> [1][n-1] -> [n-1][n-2]
 * each time: one circle
 */
public class MatrixRotate90Degree {

	public void rotate90Clockwise(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}

		int n = input.length;
		int offset = 0;
		while (offset < n / 2) {
			for (int i = offset; i < n - 1 - offset; i++) {
				// [offset][i] > [i][n - 1 - offset] > [n - 1 - offset][n - 1 - i] > [n - 1 - i][offset] > [offset][i]
				// save top row
				int tmp = input[offset][i];

				// top row <- left col
				input[offset][i] = input[n - 1 - i][offset];

				// left col <- bottom row
				input[n - 1 - i][offset] = input[n - 1 - offset][n - 1 - i];

				// bottom row <- right col
				input[n - 1 - offset][n - 1 - i] = input[i][n - 1 - offset];

				// right col <- top row
				input[i][n - 1 - offset] = tmp;
			}
			offset++;
		}
	}

	
	public void rotate(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}

		int start = 0;
		int end = input.length - 1;
		while (start < end) {
			for (int i = start; i < end; i++) {
				int temp = input[start][start + i];
				input[start][start + i] = input[end - i][start];
				input[end - i][start] = input[end][end - i];
				input[end][end - i] = input[start + i][end];
				input[start + i][end] = temp;
			}
			start++;
			end--;
		}
	}
	
	public void rotate90CounterClock(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}
		
		int n = input.length;
		for (int offset = 0; offset < n / 2; offset++) {
			for (int i = offset; i < n - 1 - offset; i++) {
				int tmp = input[offset][i];
				input[offset][i] = input[i][n - 1 - offset];
				input[i][n - 1 - offset] = input[n - 1 - offset][n - 1 - i];
				input[n - 1 - offset][n - 1 - i] = input[n - 1 - i][offset];
				input[n - 1 - i][offset] = tmp;
			}
		}
	}
	


	public static void main(String[] args) {
		MatrixRotate90Degree test = new MatrixRotate90Degree();

		int[][] test1 = null;
		test.rotate90Clockwise(test1);
		System.out.println(Arrays.deepToString(test1));

		int[][] test2 = new int[0][0];
		test.rotate90Clockwise(test2);
		System.out.println(Arrays.deepToString(test2));

		int[][] test3 = new int[][] { { 1 } };
		test.rotate90Clockwise(test3);
		System.out.println(Arrays.deepToString(test3));

		/*
		 * 1 2 3 4 5 
		 * 16 17 18 19 6 
		 * 15 24 25 20 7 
		 * 14 23 22 21 8 
		 * 13 12 11 10 9
		 * 
		 * 
		 * 13 14 15 16 1 
		 * 12 23 24 17 2 
		 * 11 22 25 18 3 
		 * 10 21 20 19 4 
		 * 9 8 7 6 5
		 */

		int[][] test6 = new int[][] { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 },
				{ 14, 23, 22, 21, 8 }, { 13, 12, 11, 10, 9 } };
		test.rotate(test6);
		System.out.println(Arrays.deepToString(test6));

		int[][] test7 = new int[][] { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 },
				{ 14, 23, 22, 21, 8 }, { 13, 12, 11, 10, 9 } };
		test.rotate90Clockwise(test7);
		System.out.println(Arrays.deepToString(test7));
		
		int[][] test8 = new int[][] { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 },
			{ 14, 23, 22, 21, 8 }, { 13, 12, 11, 10, 9 } };
		test.rotate90CounterClock(test8);
		System.out.println(Arrays.deepToString(test8));
	}

}
