package recursion.arrays;

import java.util.ArrayList;
import java.util.List;

public class MatrixSpiralCounterClock {
	
	public List<Integer> spiralCounterClock(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		
		if (matrix == null || matrix.length == 0) {
			return result;
		}
		
		int top = 0; 
		int left = 0; 
		int bottom = matrix.length - 1; 
		int right = matrix[0].length - 1;
		
		for (; top < bottom && left < right; top++, left++, bottom--, right--) {
			// left col
			for (int i = top; i < bottom; i++) {
				result.add(matrix[i][left]);
			}
			
			// bottom row
			for (int i = left; i < right; i++) {
				result.add(matrix[bottom][i]);
			}
			
			// right col
			for (int i = bottom; i > top; i--) {
				result.add(matrix[i][right]);
			}
			
			// top row
			for (int i = right; i > left; i--) {
				result.add(matrix[top][i]);
			}
		}
		
		// even number of rows/cols has nothing left: top > bottom || left > right
		if (top == bottom) {
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
		} else if (left == right) {
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][left]);
			}
		}
		
		return result;
	}
	
	public List<Integer> spiralCounterClockRecursive(int[][] matrix) {
		List<Integer> result = new ArrayList<Integer>();
		
		if (matrix == null || matrix.length == 0) {
			return result;
		}
		
		helper(matrix, result, 0, matrix.length - 1, 0, matrix[0].length - 1);
		return result;
	}
	
	private void helper(int[][] matrix, List<Integer> result, int top, int bottom, int left, int right) {
		// case 1. even number of rows for wide rectangles, or even number of cols for tall rectangles
		if (left > right || top > bottom) {
			return;
		} else if (top == bottom) {
			// case 2. odd number of rows for wide rectangles: one row left
			for (int i = left; i <= right; i++) {
				result.add(matrix[top][i]);
			}
			return;
		} else if (left == right) {
			// case 3. odd number of cols for tall rectangles: one col left
			for (int i = top; i <= bottom; i++) {
				result.add(matrix[i][left]);
			}
			return;
		} else {
			// left col
			for (int i = top; i < bottom; i++) {
				result.add(matrix[i][left]);
			}

			// bottom row
			for (int i = left; i < right; i++) {
				result.add(matrix[bottom][i]);
			}

			// right col
			for (int i = bottom; i > top; i--) {
				result.add(matrix[i][right]);
			}

			// top row
			for (int i = right; i > left; i--) {
				result.add(matrix[top][i]);
			}
			
			helper(matrix, result, top + 1, bottom - 1, left + 1, right - 1);
		}
	}
	
	public static void main(String[] args) {
		MatrixSpiralCounterClock test = new MatrixSpiralCounterClock();
		int[][] rectangle1 = {{1,2,3,4,5},{6,7,8,9,10}};
		int[][] rectangle2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		int[][] rectangle3 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
		System.out.println(test.spiralCounterClock(rectangle1));
		System.out.println(test.spiralCounterClockRecursive(rectangle1));
		System.out.println(test.spiralCounterClock(rectangle2));
		System.out.println(test.spiralCounterClockRecursive(rectangle2));
		System.out.println(test.spiralCounterClock(rectangle3));
		System.out.println(test.spiralCounterClockRecursive(rectangle3));
	}
}
