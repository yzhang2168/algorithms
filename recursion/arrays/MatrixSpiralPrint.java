package recursion.arrays;

import java.util.Arrays;

public class MatrixSpiralPrint {
	public int[][] matrixSpiralPrint(int size) {
		int counter = 1;
		int offset = 0;
		int[][] m = new int[size][size];
		for (; offset < size / 2; offset++) {
			for (int j = offset; j < size - 1 - offset; j++) {
				m[offset][j] = counter++;
			}

			for (int j = offset; j < size - 1 - offset; j++) {
				m[j][size - 1 - offset] = counter++;
			}
			
			for (int j = size - 1 - offset; j > offset; j--) {
				m[size - 1 - offset][j] = counter++;
			}
			
			for (int j = size - 1 - offset; j > offset; j--) {
				m[j][offset] = counter++;
			}			
		}
		
		if (size % 2 == 1) {
			m[offset][offset] = counter;
		}
		
		return m;
	}
	
	public int[][] matrixSpiralPrint(int length, int width) {
		int counter = 1;
		int[][] m = new int[length][width];
		int top = 0;
		int bottom = length - 1;
		int left = 0;
		int right = width - 1;
		
		while (top < bottom && left < right) {
			for (int i = left; i < right; i++) {
				m[top][i] = counter++;
			}
			
			for (int i = top; i < bottom; i++) {
				m[i][right] = counter++;
			}
			
			for (int i = right; i > left; i--) {
				m[bottom][i] = counter++;
			}
			
			for (int i = bottom; i > top; i--) {
				m[i][left] = counter++;
			}
			
			top++;
			bottom--;
			left++;
			right--;
		}
		
		if (top == bottom) {
			for (int i = left; i <= right; i++) {
				m[top][i] = counter++;
			}
			
		} else if (left == right) {
			for (int i = top; i <= bottom; i++) {
				m[i][left] = counter++;
			}
		}
		
		return m;
	}
	
	public int[][] matrixSpiralPrintII(int length, int width) {
		int counter = 1;
		int[][] m = new int[length][width];
		int offset = 0;
		while (offset < length / 2 && offset < width / 2) {
			for (int i = offset; i < width - 1 - offset; i++) {
				m[offset][i] = counter++;
			}
			
			for (int i = offset; i < length - 1 - offset; i++) {
				m[i][width - 1 - offset] = counter++;
			}
			
			for (int i = width - 1 - offset; i > offset; i--) {
				m[length - 1 - offset][i] = counter++;
			}
			
			for (int i = length - 1 - offset; i > offset; i--) {
				m[i][offset] = counter++;
			}
			
			offset++;
		}
		
		if (length > width) {
			for (int i = offset; i <= length - 1 - offset; i++) {
				m[i][offset] = counter++;
			}
			
		} else if (length < width) {
			for (int i = offset; i <= width - 1 - offset; i++) {
				m[offset][i] = counter++;
			}
		}
		
		return m;
	}
	
	public static void main(String[] args) {
		MatrixSpiralPrint test = new MatrixSpiralPrint();
		int[][] m1 = test.matrixSpiralPrint(4);
		System.out.println(Arrays.deepToString(m1));

		int[][] m2 = test.matrixSpiralPrint(5);
		System.out.println(Arrays.deepToString(m2));

	
		int[][] m3 = test.matrixSpiralPrint(5, 3);
		System.out.println(Arrays.deepToString(m3));

		int[][] m4 = test.matrixSpiralPrintII(5, 3);
		System.out.println(Arrays.deepToString(m4));

		int[][] m5 = test.matrixSpiralPrint(3, 5);
		System.out.println(Arrays.deepToString(m5));

		int[][] m6 = test.matrixSpiralPrintII(3, 5);
		System.out.println(Arrays.deepToString(m6));
	}

}
