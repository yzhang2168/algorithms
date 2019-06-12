package DP;

import java.util.Arrays;

public class LargestSquareOfOnes {
	/**
	 * input matrix is a 0/1 binary square matrix
	 * # squares in a matrix of n by n: 
	 * 	n ^ 2 points in the matrix
	 * 	for each point, use it as the top left point of all possible squares, there are n squares
	 * 	total: n ^ 2 * n = n ^ 3 
	 * 
	 * brute-force solution
	 * 	check each of the n ^ 3 squares includes 1s only
	 * 	n ^ 2 * n ^ 3 = n ^ 5 
	 * 
	 * DP solution
	 * 	base case: 1st row, 1st col = 0/1 in input matrix
	 *  induction rule
	 * 		m[i][j]: max size of square of 1s using i,j as the bottom right point
	 *  	m[i][j] = 0 if input[i][j] == 0
	 *  			else input[i][j] == 1
	 *  			look back at m[i - 1][j - 1], m[i - 1][j], m[i][j - 1] for the max size of squares ending at these positions
	 *  			min(3 smaller problems) + 1 
	 * */
	public static int largestSquareOfOnes(int[][] input) {
		if (input == null) {
			return 0;
		}

		int n =  input.length;
		int max = 0;
		int[][] result = new int[n][n];
		// m[i][j]: largest square of 1s with i, j as the right bottom position
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					result[i][j] = input[i][j] == 0 ? 0 : 1;
				} else if (input[i][j] == 0) {
					result[i][j] = 0;
				} else { // input[i][j] == 1
					result[i][j] = 1 + Math.min(result[i - 1][j - 1], Math.min(result[i - 1][j], result[i][j - 1]));
				}
				
				max = Math.max(max, result[i][j]);
			}
		}

		return max;
	}
	
	public int largestSquareOfOnesOptimal(int[][] matrix) {
	      if (matrix == null) {
	          return 0;
	      }
	      
	      int max = 0;
	      int n = matrix.length;
	      int[] prevRow = new int[n];
	      int[] currRow = new int[n];
	      // result[i][j]: largest square of 1s ending at matrix[i][j]
	      for (int i = 0; i < n; i++) {
	          for (int j = 0; j < n; j++) {
	              if (i == 0) {
	                  prevRow[j] = matrix[i][j];
	                  max = Math.max(max, prevRow[j]);
	              } else if (j == 0) {
	                  currRow[j] = matrix[i][j];
	              } else if (matrix[i][j] == 0) {
	                currRow[j] = 0;
	              } else {
	                  currRow[j] = 1 + Math.min(prevRow[j - 1], Math.min(prevRow[j], currRow[j - 1]));
	              }
	              max = Math.max(max, currRow[j]);
	          }

	          if (i > 0) {
	              prevRow = Arrays.copyOf(currRow, n);
	          }
	      }
	      return max;
	  }


	public static void main(String[] args) {
		int[][] input = {{1,1,1,1},{1,1,1,1},{0,1,1,1},{1,1,1,1}};
		System.out.println(largestSquareOfOnes(input));
		System.out.println(largestSquareOfOnes(new int[][]{{1}}));
		System.out.println(largestSquareOfOnes(new int[0][0]));
		System.out.println(largestSquareOfOnes(new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}));
	}
}
