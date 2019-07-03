package recursion;

import java.util.Arrays;

public class MatrixRotateByOne {

	public void rotateOneClockwise(int[][] input) {
		if (input == null || input.length == 0 || input.length != input[0].length) {
			return;
		}
		
		int n = input.length;
		for (int offset = 0; offset < n / 2; offset++) {
			int tmp = input[offset][n - 1 - offset];

			// top row
			for (int i = offset; i < n - 1 - offset; i++) {
				input[offset][n - 1 - i] = input[offset][n - 2 - i];
			}
			
			// left col
			for (int i = offset; i < n - 1 - offset; i++) {
				input[i][offset] = input[i + 1][offset];
			}
			
			// bottom row
			for (int i = offset; i < n - 1 - offset; i++) {
				input[n - 1 - offset][i] = input[n - 1 - offset][i + 1];				
			}
			
			// right col
			for (int i = offset; i < n - 1 - offset; i++) {
				input[n - 1 - i][n - 1 - offset] = input[n - 2 - i][n - 1 - offset];				
			}
			
			input[offset + 1][n - 1 - offset] = tmp;
		}
	}
	
	public static void main(String[] args) {
		MatrixRotateByOne test = new MatrixRotateByOne();

		int[][] test1 = null;
		test.rotateOneClockwise(test1);
		System.out.println(Arrays.deepToString(test1));

		int[][] test2 = new int[0][0];
		test.rotateOneClockwise(test2);
		System.out.println(Arrays.deepToString(test2));

		int[][] test3 = new int[][] { { 1 } };
		test.rotateOneClockwise(test3);
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
		test.rotateOneClockwise(test6);
		System.out.println(Arrays.deepToString(test6));
	}

}
