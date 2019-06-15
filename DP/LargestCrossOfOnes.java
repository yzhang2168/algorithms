package DP;

import java.util.Arrays;

public class LargestCrossOfOnes {
	/**
	 * Given a matrix that contains only 1s and 0s, find the largest cross which contains only 1s, 
	 * with the same arm lengths and the four arms joining at the central point.
	 * Return the arm length of the largest cross.
	 * Assumptions
	 * The given matrix is not null, has size of N * M, N >= 0 and M >= 0.
	 * Examples
	 * { {0, 0, 0, 0},
	     {1, 1, 1, 1},
	     {0, 1, 1, 1},
	     {1, 0, 1, 1} }
	 * the largest cross of 1s has arm length 2.
	 * 
	 * brute-force:
	 * 	for each input[i][j], scan all 4 directions, O(n * 4) = O(n)
	 *  total: O(n ^ 2 * n) = O(n ^ 3)
	 *  
	 * DP: 
	 * process longest consecutive 1s in all 4 directions using longestConsecutiveOnes()
	 * leftToRight: longest left arm at each input[i][j]
	 * rightToLeft: longest right arm
	 * topToBottom: longest head 
	 * bottomToTop: longest legs
	 * 
	 * m[i][j] = min(leftToRight[i][j], rightToLeft[i][j], topToBottom[i][j], bottomToTop[i][j])
	 * among all the cells, get global max
	 * */	
	public int largestCrossOfOnes(int[][] matrix) {
		int n = matrix.length;
		if (n == 0) {
			return 0;
		}
		
		// n != 0 to avoid NPE 
		int m = matrix[0].length;
		if (m == 0) {
			return 0;
		}
		
		// left and up arm lengths
		int[][] leftUp = leftUp(matrix, n, m);
		// right and bottom arm lengths
		int[][] rightDown = rightDown(matrix, n, m);
		return merge(leftUp, rightDown, n, m);		
		
	}
	
	private int[][] leftUp(int[][] matrix, int n, int m) {
		int[][] left = new int[n][m];
		int[][] up = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (matrix[i][j] == 1) {
					if (i == 0 && j == 0) {
						left[i][j] = 1;
						up[i][j] = 1;
					} else if (i == 0) {
						up[i][j] = 1;
						left[i][j] = left[i][j - 1] + 1;
					} else if (j == 0) {
						left[i][j] = 1;
						up[i][j] = up[i - 1][j] + 1;
					} else {
						left[i][j] = left[i][j - 1] + 1;
						up[i][j] = up[i - 1][j] + 1;
					}
				}
			}
		}
		merge(left, up, n, m);
		return left;
	}
	
	private int[][] rightDown(int[][] matrix, int n, int m) {
		int[][] right = new int[n][m];
		int[][] down = new int[n][m];
		
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (matrix[i][j] == 1) {
					if (i == n - 1 && j == m - 1) {
						right[i][j] = 1;
						down[i][j] = 1;
					} else if (i == n - 1) {
						down[i][j] = 1;
						right[i][j] = right[i][j + 1] + 1;
					} else if (j == m - 1) {
						right[i][j] = 1;
						down[i][j] = down[i + 1][j] + 1;
					} else {
						right[i][j] = right[i][j + 1] + 1;
						down[i][j] = down[i + 1][j] + 1;
					}
				}
			}
		}
		merge(right, down, n, m);
		return right;
	}
	
	// take min for each cell and overwrite m1
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
	
	public static int LargestCross(int[][] matrix) {
		int n = matrix.length;
		if (n == 0) {
			return 0;
		}
		
		int m = matrix[0].length;
		if (m == 0) {
			return 0;
		}
		
		int[][] leftToRight = toLongestConsecutiveOnesLeft(matrix);
		int[][] rightToLeft = toLongestConsecutiveOnesRight(matrix);
		int[][] topToBottom = toLongestConsecutiveOnesTop(matrix);
		int[][] bottomToTop = toLongestConsecutiveOnesBottom(matrix);
		
		int max = merge(leftToRight, rightToLeft, topToBottom, bottomToTop);
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
				if (j == 0) {
					result[i][j] = matrix[i][j] == 1? 1 : 0;
				} else if (matrix[i][j] == 1) {
					result[i][j] = result[i][j - 1] + 1;
				} else {
					result[i][j] = 0;
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
	
	// top to bottom: top arm length
	private static int[][] toLongestConsecutiveOnesTop(int[][] matrix) {
		if (matrix == null || matrix.length == 0) {
			return null;
		}
		
		int n = matrix.length;
		int m = matrix[0].length;
		int[][] result = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == 0) {
					result[i][j] = matrix[i][j] == 1? 1 : 0;
				} else if (matrix[i][j] == 1) {
					result[i][j] = result[i - 1][j] + 1;
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
	
	public int largest(int[][] matrix) {
	      int n = matrix.length;
	      int m = matrix[0].length;

	      int[][] topLeft = topLeft(matrix, n, m);
	      int[][] rightDown = rightDown1(matrix, n, m);
	      return merge(topLeft, rightDown, n, m);
	  }

	  private int[][] topLeft(int[][] matrix, int n, int m) {
	      int[][] top = new int[n][m];
	      int[][] left = new int[n][m];
	      for (int i = 0; i < n; i++) {
	          for (int j = 0; j < m; j++) {
	              if (matrix[i][j] == 1) {
	                if (i == 0 && j == 0) {
	                    top[i][j] = 1;
	                    left[i][j] = 1;
	                } else if (i == 0) {
	                    top[i][j] = 1;
	                    left[i][j] = 1 + left[i][j - 1];
	                } else if (j == 0) {
	                    top[i][j] = 1 + top[i - 1][j];
	                    left[i][j] = 1;
	                } else {
	                  top[i][j] = 1 + top[i - 1][j];
	                  left[i][j] = 1 + left[i][j - 1]; 
	                }
	              }
	          }
	      }
	      merge(top, left, n, m);
	      return top;
	  }

	  private int[][] rightDown1(int[][] matrix, int n, int m) {
	      int[][] right = new int[n][m];
	      int[][] down = new int[n][m];
	      for (int i = n - 1; i >= 0; i--) {
	          for (int j = m - 1; j >= 0; j--) {
	              if (matrix[i][j] == 1) {
	                if (i == n - 1 && j == m - 1) {
	                    right[i][j] = 1;
	                    down[i][j] = 1;
	                } else if (i == n - 1) {
	                    right[i][j] = 1 + right[i][j + 1];
	                    down[i][j] = 1;
	                } else if (j == m - 1) {
	                    right[i][j] = 1;
	                    down[i][j] = 1 + down[i + 1][j];
	                } else {
	                    right[i][j] = 1 + right[i][j + 1];
	                    down[i][j] = 1 + down[i + 1][j]; 
	                }
	              }
	          }
	      }
	      merge(right, down, n, m);
	      return down;
	  }
	
	public static void main(String[] args) {
		int[][] input = { {0, 1, 0, 0},
			     		  {1, 1, 1, 1},
			     		  {0, 1, 0, 0},
			     		  {0, 1, 0, 0} };
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesLeft(input)));
		System.out.println();
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesRight(input)));
		System.out.println();
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesTop(input)));
		System.out.println();
		
		System.out.println(Arrays.deepToString(toLongestConsecutiveOnesBottom(input)));
		System.out.println();
		
		System.out.println(LargestCross(input));
		System.out.println();
		
		input = new int[][]{{1,1,1,1,1},{1,0,0,1,1},{1,1,1,1,1},{1,1,1,1,0},{0,0,0,1,1}};
		LargestCrossOfOnes test = new LargestCrossOfOnes();
		System.out.println(test.largestCrossOfOnes(input));
		System.out.println(test.largest(input));
	}

}
