package DP;

import java.util.Arrays;

/**
 * n^2 top left corner
 * n^2 bottom right corner
 * n^4 submatrices
 * 
 * ===================================================================
 * 0. brute force:
 * for for for for each submatrix
 * 		calculate its sum O(n^2)
 * 
 * time: O(n^6) waaaaaa
 * ===================================================================
 * 1. DP1: prefix sum in 1D
 * O(n) to compute each submatrix sum
 * prefixSum[i][j] = a[i][0] +...+ a[i][j]
 * for for for for each submatrix
 * 		calculate each row's sum using prefix sum for that row O(n * each row O(1))
 * 
 * time: O(n^5)
 * ===================================================================
 * 2. DP2:
 * O(1) to compute each submatrix sum
 * prefixSum[i][j] = a[0][0] +...+ a[i][j]
 * sum((k,t)...(i,j)) = prefixSum[i][j] - prefixSum[k - 1][j] - prefixSum[i][t - 1] + prefixSum[k - 1][t - 1] 
 * O(1)
 * how to preprocess 2D prefix sum?
 * m[i][j] = m[i - 1][j] + m[i][j - 1] - m[i - 1][j - 1] + a[i][j]
 * O(n^2) for preprocessing
 * 
 * time: O(n^4)
 * ===================================================================
 * 3. DP3:
 * 1D array largest sum of all subarrays
 * n^2 subarrays => O(n) to find max
 * 
 * 2D array largest sum of all submatrices
 * n^4 submatrices: for top row, for bottom row, for left col, for right col
 * 没有榨干DP的价值
 * DP就不需要enumerate all submatrices
 * linear scan O(n^3)
 * high level idea: use 1D subarray max as a helper function
 * 
 * step 1. 
 * 	preprocess: prefix sum for each column
 * 	O(n^2)
 * 
 * step 2.
 * for top row
 * 	for bottom row
 * 		2.1 use column wise prefix sum to flatten target region (top to bottom)
 * 			O(n)
 * 			s[j] = prefixSum[bottomRow][j] - prefixSum[topRow][j] + a[topRow][j]
 * 		2.2 call largest sum of subarrays
 * 	n^2 * (flatten n + prefixsum n) = O(n^3)		
 * 
 * total time: O(n^3)
 *  
 *find left and right bound
 * 		do not enumerate for left, for right, because for 1D subarray, we didn't enumerate left and right, only 1 linear scan
 * 		linear scan and look back: max sum, left border, right border in O(n)
 * input
 * 1 2 3 4 5
 * 2 1 1 1 1
 * 2 3 1 1 2
 * 
 * 1d column wise prefix sum [i][j]
 * 1 2 3 4 5
 * 3 3 4 5 6
 * 5 6 5 6 8
 * 
 * flatten rows 1-2
 * 4 4 2 2 3   => as input of largest sum of subarrays
 * ===================================================================
 * */
public class LargestSumSubmatrix {
	public int largest(int[][] matrix) {
	      int n = matrix.length;
	      int m = matrix[0].length;
	      int[][] prefixSum = prefixSum(matrix, n, m);
	      System.out.println(Arrays.deepToString(prefixSum));
	      int globalMax = Integer.MIN_VALUE;

	      for (int top = 0; top < n; top++) {
	          for (int bottom = top; bottom < n; bottom++) {
	              int[] currArray = flatten(prefixSum, matrix, m, top, bottom);
	              int max = maxSumSubarray(currArray);
	              globalMax = Math.max(globalMax, max);
	          }
	      }
	      return globalMax;
	  }

	  private int[][] prefixSum(int[][] matrix, int n, int m) {
	      int[][] prefixSum = new int[n][m];
	      for (int j = 0; j < m; j++) {
	          for (int i = 0; i < n; i++) {
	        	  if (i == 0) {
	        		  prefixSum[i][j] = matrix[i][j];
	        	  } else {
		              prefixSum[i][j] = prefixSum[i - 1][j] + matrix[i][j];	        		  
	        	  }
	          }
	      }
	      
	      return prefixSum;
	  }

	  private int[] flatten(int[][] prefixSum, int[][] matrix, int m, int top, int bottom) {
	      int[] result = new int[m];
	      for (int j = 0; j < m; j++) {
	          result[j] = prefixSum[bottom][j] - prefixSum[top][j] + matrix[top][j];
	      }
	      return result;
	  }

	  private int maxSumSubarray(int[] array) {
	      int curr = array[0];
	      int max = curr;
	      for (int i = 1; i < array.length; i++) {
	          curr = Math.max(array[i], curr + array[i]);
	          max = Math.max(max, curr);
	      }
	      return max;
	  }
	
	public int[] maxSumSubmatrices(int[][] matrix) {
		int n = matrix.length;
		if (n == 0) {
			return new int[0];
		}
		
		int m = matrix[0].length;
		if (m == 0) {
			return new int[0];
		}
		
		// prefix sum for each col - as a 1D array
		int[][] prefixSum = new int[n][m];
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				if (i == 0) {
					prefixSum[i][j] = matrix[i][j];
				} else {
					prefixSum[i][j] = prefixSum[i - 1][j] + matrix[i][j];
				}
			}
		}
				
		// enumerate top row, bottom row, then flatten the region
		int currLeft = -1;
		int globalLeft = -1;
		int globalRight = -1;
		int globalMax = Integer.MIN_VALUE;

		for (int top = 0; top < n; top++) {
			for (int bottom = top; bottom < n; bottom++) {
				int[] curr = new int[m];
				int currSum = Integer.MIN_VALUE;
				for (int j = 0; j < m; j++) {
					curr[j] = prefixSum[bottom][j] - prefixSum[top][j] + matrix[top][j];
					if (currSum > 0) {
						currSum += curr[j];
					} else {
						currSum = curr[j];
						currLeft = j;
					}
					
					if (currSum > globalMax) {
						globalMax = currSum;
						globalLeft = currLeft;
						globalRight = j;
					}
				}				
			}
		}
		
		return new int[] {globalMax, globalLeft, globalRight};		
	}
	
	public static void main(String[] args) {
		int[][] input = {{1,2,3,4,5},
						 {-1,1,-20,1,2},
						 {3,1,2,1,3}};
		
		LargestSumSubmatrix test = new LargestSumSubmatrix();
		int[] result = test.maxSumSubmatrices(input);
		System.out.println(Arrays.toString(result));

		input = new int[][]{{-4, 2,-1,0, 2},
			                 {2, 3, 2,1,-3},
			                {-3,-3,-2,2, 4},
			                 {1, 1, 2,-2,5},
			                {-4, 0, 1,1,-4}};
		System.out.println(test.largest(input));
	}
}
