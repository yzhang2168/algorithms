package algorithms.DP;

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
		int[][] input = { {0, 1, 0, 0},
			     		  {1, 1, 1, 1},
			     		  {0, 1, 0, 0},
			     		  {0, 1, 0, 0} };
		
		System.out.println();
		util.Util.printArray(toLongestConsecutiveOnesLeft(input));
		System.out.println();
		
		util.Util.printArray(toLongestConsecutiveOnesRight(input));
		System.out.println();
		
		util.Util.printArray(toLongestConsecutiveOnesTop(input));
		System.out.println();
		
		util.Util.printArray(toLongestConsecutiveOnesBottom(input));
		System.out.println();
		
		System.out.println(LargestX(input));
		System.out.println();
	}
}
