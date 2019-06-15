package DP;

import java.util.Arrays;

public class LargestSubsquare {
	/**
	 * Given a matrix that contains only 1s and 0s, 
	 * find the largest subsquare surrounded by 1s.
	 * Assumptions
	 * The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
	 * Examples
		{ {1, 0, 1, 1, 1},  <- direction 1
		  {1, 1, 1, 1, 1},
		  {1, 1, 0, 1, 0},
		  {1, 1, 1, 1, 1},
		  {1, 1, 1, 0, 0} }
			  ^
			  |
		  direction 2

	 * # squares in the matrix: O(n ^ 3)
	 * 
	 * solution 0: brute-force
	 * for for (i, j) { all points in the matrix O(n ^ 2) 
	 * 	for (edge length) {  O(n)
	 * 		run 4 for ()s to check each edge O(n) 
	 *  }
	 * }
	 * 
	 * time: O(n ^ 4)
	 * 
	 * solution 1: DP
	 * step 1: fill in two 2d matrices: 
	 * 		rightToLeft[n][n]: horizontal edge lengths of 1s
	 * 		bottomToTop[n][n]: vertical edge lengths of 1s
	 * 		O(n ^ 2)
	 * 
	 * step 2:
	 * 		for (i) {
	 * 			for (j) {
	 * 				(i, j) as the upper-left corner
	 * 				m1[i][j]: top edge length
	 * 				m2[i][j]: left edge length
	 * 				for each possible edge length min(m1[i][j], m2[i][j]) down to 1 {
	 * 					check right edge length: if top right corner in direction 2 >= edge length
	 * 					check bottom edge length: if bottom left corner in direction 1 >= edge length 
	 * 			}
	 * 		}
	 * return globalMax
	 * 
	 * time: O(n ^ 2) + O(n ^ 2 * n) = O(n ^ 3)
	 * */
	public int largestSquareOfOnes(int[][] matrix) {
		if (matrix == null) {
			return 0;
		}
		
		int n = matrix.length;
		int m = matrix[0].length;
		
		if (n == 0 || m == 0) {
			return 0;
		}
		
		int max = 0;
		// pre-process from left to right, top to bottom so that we can look up previously saved values
		int[][] leftToRight = new int[n][m];
		int[][] topToBottom = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) { 
			// (i, j) bottom right corner of squares
				if (matrix[i][j] == 1) {
					leftToRight[i][j] = j == 0 ? 1 : 1 + leftToRight[i][j - 1];
					topToBottom[i][j] = i == 0 ? 1 : 1 + topToBottom[i - 1][j];
					
					int maxLength = Math.min(leftToRight[i][j], topToBottom[i][j]);
					for (; maxLength >= 1; maxLength--) {
						if (leftToRight[i - maxLength + 1][j] >= maxLength && topToBottom[i][j - maxLength + 1] >= maxLength) {
							max = Math.max(max, maxLength);
							break;
						}
					} 
				}
			}
		}
		
		System.out.println(Arrays.deepToString(leftToRight));
		System.out.println(Arrays.deepToString(topToBottom));
		return max;
	}
	
	public static int largestSquareSurroundedByOnes(int[][] matrix) {
		if (matrix == null) {
			return 0;
		}
		
		int n = matrix.length;
		int m = matrix[0].length;
		
		if (n == 0 || m == 0) {
			return 0;
		}
		
		int[][] rightArms = toLongestConsecutiveOnesRight(matrix);
		int[][] bottomArms = toLongestConsecutiveOnesBottom(matrix);
		
		int globalMax = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int edge = Math.min(rightArms[i][j], bottomArms[i][j]);
				if (edge > 0) {
					for (int k = edge; k >= 1; k--) {
						// top right corner: [i][j + edge - 1]
						// bottom left corner: [i + edge - 1][j]
						if (bottomArms[i][j + k - 1] >= k && rightArms[i + k - 1][j] >= k) {
							globalMax = Math.max(globalMax, k);
							break;
						}
					}
				}
			}
		}
		return globalMax;
	}

	// right to left: right arm length
	private static int[][] toLongestConsecutiveOnesRight(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return null;
		}

		int n = matrix.length;
		int m = matrix[0].length;
		int[][] result = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = m - 1; j >= 0; j--) {
				if (j == m - 1) {
					result[i][j] = matrix[i][j] == 1? 1 : 0;
				} else if (matrix[i][j] == 1) {
					result[i][j] = result[i][j + 1] + 1;
				} else {
					result[i][j] = 0;
				}
			}
		}
		return result;
	}

	// bottom to top: bottom arm length
	private static int[][] toLongestConsecutiveOnesBottom(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return null;
		}

		int n = matrix.length;
		int m = matrix[0].length;
		int[][] result = new int[n][m];

		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < m; j++) {
				if (i == n - 1) {
					result[i][j] = matrix[i][j] == 1? 1 : 0;
				} else if (matrix[i][j] == 1) {
					result[i][j] = result[i + 1][j] + 1;
				} else {
					result[i][j] = 0;
				}
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		int[][] input = { {1, 0, 1, 1, 1},
						  {1, 1, 1, 1, 1},
						  {1, 1, 0, 1, 0},
						  {1, 1, 1, 1, 1},
						  {1, 1, 1, 0, 0}  };
		
		LargestSubsquare test = new LargestSubsquare();
		System.out.println(test.largestSquareOfOnes(input));
		System.out.println(largestSquareSurroundedByOnes(input));		
	}
}
