package algorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/**
Traverse an M * N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.
Assumptions
The 2D array is not null and has size of M * N where M, N >= 0
Examples
{ {1,  2,  3,  4},
  {5,  6,  7,  8},
  {9, 10, 11, 12} }
the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7] 
 * */
public class RectangleSpiralTraversal {

	public static List<Integer> spiralTraversal(int[][] input) {
		List<Integer> result = new ArrayList<Integer>();
		// check input.length == 0 to avoid NPE on input[0].length
		if (input == null || input.length == 0) {
			return result;
		}
		
		int rows = input.length;
		int cols = input[0].length;
		int rowStart = 0;
		int rowEnd = rows - 1;
		int colStart = 0;
		int colEnd = cols - 1;
		
		while (rowStart < rowEnd && colStart < colEnd) {
			// top
			for (int i = colStart; i < colEnd; i++) {
				result.add(input[rowStart][i]);
			}
			
			// right
			for (int i = rowStart; i < rowEnd; i++) {
				result.add(input[i][colEnd]);
			}
			
			// bottom
			for (int i = colEnd; i > colStart; i--) {
				result.add(input[rowEnd][i]);
			}
			
			// left
			for (int i = rowEnd; i > rowStart; i--) {
				result.add(input[i][colStart]);
			}
			rowStart++;
			rowEnd--;
			colStart++;
			colEnd--;
		}
		
		// case 1: nothing left: colStart > colEnd || rowStart > rowEnd
		// case 2: 1 row left
		if (rowStart == rowEnd) {
			for (int i = colStart; i <= colEnd; i++) {
				result.add(input[rowStart][i]);
			}
		// case 3: 1 col left	
		} else if (colStart == colEnd) {
			for (int i = rowStart; i <= rowEnd; i++) {
				result.add(input[i][colStart]);
			}
		}
			
		return result;
	}
	
	
	public List<Integer> spiralRecursive(int[][] input) {
	    List<Integer> result = new ArrayList<Integer>();
	    
	    if (input == null || input.length == 0) {
	      return result;
	    }
	    
	    spiralRecursive(input, result, 0, input[0].length - 1, input.length - 1, 0);
	    return result;
	  }
	  
	  private void spiralRecursive(int[][] input, List<Integer> result, int top, int right, int bottom, int left) {
	    if (left >= right || top >= bottom) {
	      // case 1: nothing left, do nothing
	    	// 1.1: wide rectangle: even number of rows, top > bottom
	    	// 1.2: tall rectangle: even number of cols, left > right
	      
	    	// case 2: row left
	    	// wide rectangle: odd number of rows, top == bottom
	      if (top == bottom) {
	        for (int i = left; i <= right; i++) {
	          result.add(input[top][i]);
	        }  
	      // case 3: col left  
	      // tall rectangle: odd number of cols, left == right  
	      } else if (left == right) {
	        for (int i = top; i <= bottom; i++) {
	          result.add(input[i][left]);
	        }  
	      }
	      return;
	    }
	    
	    // top
	    for (int i = left; i < right; i++) {
	      result.add(input[top][i]);
	    }

	    // right
	    for (int i = top; i < bottom; i++) {
	      result.add(input[i][right]);
	    }

	    // bottom
	    for (int i = right; i > left; i--) {
	      result.add(input[bottom][i]);
	    }

	    // left
	    for (int i = bottom; i > top; i--) {
	      result.add(input[i][left]);
	    }

	    spiralRecursive(input, result, top + 1, right - 1, bottom - 1, left + 1);
	  }
	  
	public static void main(String[] args) {
		int[][] rectangle1 = {{1,2,3,4,5},{6,7,8,9,10}};
		int[][] rectangle2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		int[][] rectangle3 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
		System.out.println(spiralTraversal(rectangle1));
		System.out.println(spiralTraversal(rectangle2)); // [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
		System.out.println(spiralTraversal(rectangle3));
	}
}
