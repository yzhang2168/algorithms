package recursion.arrays;

import java.util.Arrays;

public class MatrixRotateByOne {
	
	public void rotateByOneClockwise(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}
		
		int n = input.length;
		for (int offset = 0; offset < n / 2; offset++) {
			int tmp = input[offset][offset];
			
			// left col: shift from top to bottom, offset + 1...n - 1 - offset
			for (int i = offset; i < n - 1 - offset; i++) {
				input[i][offset] = input[i + 1][offset];
			}
			
			// bottom row: shift from left to right
			for (int i = offset; i < n - 1 - offset; i++) {
				input[n - 1 - offset][i] = input[n - 1 - offset][i + 1];
			}
			
			// right col: shift from bottom to top
			for (int i = n - 1 - offset; i > offset; i--) {
				input[i][n - 1 - offset] = input[i - 1][n - 1 - offset];
			}
			
			// top row: shift from right to left
			for (int i = n - 1 - offset; i > offset; i--) {
				input[offset][i] = input[offset][i - 1];
			}
			
			input[offset][offset + 1] = tmp;
		}
	}

	public static void main(String[] args) {
		MatrixRotateByOne test = new MatrixRotateByOne();

		int[][] test1 = null;
		test.rotateByOneClockwise(test1);
		System.out.println(Arrays.deepToString(test1));

		int[][] test2 = new int[0][0];
		test.rotateByOneClockwise(test2);
		System.out.println(Arrays.deepToString(test2));

		int[][] test3 = new int[][] { { 1 } };
		test.rotateByOneClockwise(test3);
		System.out.println(Arrays.deepToString(test3));

		/*
		 * 1 2 3 4 5 
		 * 16 17 18 19 6 
		 * 15 24 25 20 7 
		 * 14 23 22 21 8 
		 * 13 12 11 10 9
		 */

		int[][] test6 = new int[][] { { 1, 2, 3, 4, 5 }, { 16, 17, 18, 19, 6 }, { 15, 24, 25, 20, 7 },
				{ 14, 23, 22, 21, 8 }, { 13, 12, 11, 10, 9 } };
		test.rotateByOneClockwise(test6);
		System.out.println(Arrays.deepToString(test6));

		test.rotateByOneClockwise(test6);
		System.out.println(Arrays.deepToString(test6));

		test.rotateByOneClockwise(test6);
		System.out.println(Arrays.deepToString(test6));
	
		test.rotateByOneClockwise(test6);
		System.out.println(Arrays.deepToString(test6));
	}

}
