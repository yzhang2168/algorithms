package DP;

import java.util.Arrays;

public class LargestXOfOnes {

	/**
	 * Given a matrix that contains only 1s and 0s, 
	 * find the largest X shape which contains only 1s, 
	 * with the same arm lengths and the four arms joining at the central point.
	 * Return the arm length of the largest X shape.
	 * Assumptions
	 * The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
	 * Examples
{ {0, 0, 0, 0},
  {1, 1, 1, 1},
  {0, 1, 1, 1},
  {1, 0, 1, 1} }
the largest X of 1s has arm length 2.
	 * */
	public int largestXOfOnes(int[][] matrix) {
		int n = matrix.length;
		if (n == 0) {
			return 0;
		}
		
		int m = matrix[0].length;
		if (m == 0) {
			return 0;
		}
		
		int[][] topLeftRight = topLeftRight(matrix, n, m);		
		int[][] bottomLeftRight = bottomLeftRight(matrix, n, m);		
		return merge(topLeftRight, bottomLeftRight, n, m);
	}
	
	private int[][] topLeftRight(int[][] matrix, int n, int m) {
		int[][] topLeft = new int[n][m];
		int[][] topRight = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					if (i == 0) {
						topLeft[i][j] = 1;
						topRight[i][j] = 1;
					} else if (j == 0) {
						topLeft[i][j] = 1;
						topRight[i][j] = topRight[i - 1][j + 1] + 1;
					} else if (j == m - 1) {
						topLeft[i][j] = topLeft[i - 1][j - 1] + 1;
						topRight[i][j] = 1;
					} else {
						topLeft[i][j] = topLeft[i - 1][j - 1] + 1;
						topRight[i][j] = topRight[i - 1][j + 1] + 1;
					}
					
				}
			}
		}
		merge(topLeft, topRight, n, m);
		return topLeft;
	}
	
	private int[][] bottomLeftRight(int[][] matrix, int n, int m) {
		int[][] bottomLeft = new int[n][m];
		int[][] bottomRight = new int[n][m];
		
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					if (i == n - 1) {
						bottomLeft[i][j] = 1;
						bottomRight[i][j] = 1;
					} else if (j == 0) {
						bottomLeft[i][j] = 1;
						bottomRight[i][j] = bottomRight[i + 1][j + 1] + 1;
					} else if (j == m - 1) {
						bottomLeft[i][j] = bottomLeft[i + 1][j - 1] + 1;
						bottomRight[i][j] = 1;
					} else {
						bottomLeft[i][j] = bottomLeft[i + 1][j - 1] + 1;
						bottomRight[i][j] = bottomRight[i + 1][j + 1] + 1;
					}
					
				}
			}
		}
		
		merge(bottomLeft, bottomRight, n, m);
		return bottomLeft;
	}
	
	// merge by taking min from 1 matrices
	// max is arm length, not entire /\ length
	private int merge(int[][] m1, int[][] m2, int n, int m) {
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				m1[i][j] = Math.min(m1[i][j], m2[i][j]);
				max = Math.max(max, m1[i][j]);
			}
		}
		return max;
	}

	public static int LargestX(int[][] matrix) {
		int n = matrix.length;
		if (n == 0) {
			return 0;
		}
		
		int m = matrix[0].length;
		if (m == 0) {
			return 0;
		}
		
		int[][] leftToDown = toLongestConsecutiveOnesLeft(matrix);
		int[][] rightToDown = toLongestConsecutiveOnesRight(matrix);
		int[][] LeftToTop = toLongestConsecutiveOnesTop(matrix);
		int[][] rightToTop = toLongestConsecutiveOnesBottom(matrix);
		
		int max = merge(leftToDown, rightToDown, LeftToTop, rightToTop);
		return max;
	}
	
	/**
	 * input: square matrix of 0s/1s
	 * output: matrix[i][j] - longest consecutive 1s at this position
	 * */
	private static int[][] toLongestConsecutiveOnesLeft(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return null;
		}
		
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					result[i][j] = getNumber(result, i- 1, j - 1, n, m) + 1;
				}
			}
		}
		return result;
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
				if (matrix[i][j] == 1) {
					result[i][j] = getNumber(result, i - 1, j + 1, n, m) + 1;
				}
			}
		}
		return result;
	}
	
	// top to bottom: top arm length
	private static int[][] toLongestConsecutiveOnesTop(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return null;
		}
		
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] result = new int[n][m];
		
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					result[i][j] = getNumber(result, i + 1, j - 1, n, m) + 1;
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
			for (int j = m - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					result[i][j] = getNumber(result, i + 1, j + 1, n, m) + 1;
				}
			}
		}
		return result;
	}
	
	private static int getNumber(int[][] input, int x, int y, int n, int m) {
		if (x < 0 || x >= n || y < 0 || y>= m) {
			return 0;
		} else {
			return input[x][y];
		}
	}
	
	// dimensions are the same
	private static int merge(int[][] leftToRight, int[][] rightToLeft, int[][] topToBottom, int[][] bottomToTop) {
		int n = leftToRight.length;
		int m = leftToRight[0].length;
		if (rightToLeft.length != n || topToBottom.length != n || bottomToTop.length != n || 
			rightToLeft[0].length != m || topToBottom[0].length != m || bottomToTop[0].length != m 	) {
			return -1;
		}
		
		int[][] total = new int[n][m];
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int temp1 = Math.min(leftToRight[i][j], rightToLeft[i][j]);
				int temp2 = Math.min(topToBottom[i][j], bottomToTop[i][j]);
				total[i][j] = Math.min(temp1, temp2);
				max = Math.max(max, total[i][j]);
			}
		}
		return max;
	}
	
	
	public static void main(String[] args) {
		int[][] input = { {1, 0, 1, 0},
			     		  {0, 1, 0, 1},
			     		  {1, 0, 1, 0},
			     		  {0, 1, 0, 0} };
		
		LargestXOfOnes test = new LargestXOfOnes();
		System.out.println(test.largestXOfOnes(input));
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesLeft(input)));
		System.out.println();
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesRight(input)));
		System.out.println();
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesTop(input)));
		System.out.println();
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesBottom(input)));
		System.out.println();
		
		System.out.println(LargestX(input));
		System.out.println();
		
		input = new int[][]{{1,1,1,1,1},{1,0,0,1,1},{1,1,1,1,1},{1,1,1,1,0},{0,0,0,1,1}};
		System.out.println(test.largestXOfOnes(input));

	}
}
