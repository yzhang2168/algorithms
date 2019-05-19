package algorithms.DP;

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
		if (n == 0) {
			return 0;
		}

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
					int min = Math.min(result[i - 1][j - 1], result[i][j - 1]);
					min = Math.min(min, result[i - 1][j]);
					result[i][j] = 1 + min;	
				}
				
				if (result[i][j] > max) {
					max = result[i][j];
				}
			}
		}

		return max;
	}

	public static int largestSquareOfOnes0(int[][] input) {
		if (input == null) {
			return 0;
		}

		int n =  input.length;
		if (n == 0) {
			return 0;
		}

		int max = 0;
		int[][] result = new int[n][n];

		// m[i][j]: largest square of 1s with i, j as the right bottom position
		for (int i = 0; i < n; i++) {
			if (input[i][0] == 0) {
				result[i][0] = 0;
			} else {
				result[i][0] = 1;
				max = 1;
			}  
		}

		for (int j = 0; j < n; j++) {
			if (input[0][j] == 0) {
				result[0][j] = 0;
			} else {
				result[0][j] = 1;
				max = 1;
			}
		}

		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (input[i][j] == 0) {
					result[i][j] = 0;
				} else { // input[i][j] == 1
					int min = Math.min(result[i - 1][j - 1], result[i][j - 1]);
					min = Math.min(min, result[i - 1][j]);
					result[i][j] = 1 + min;
					if (result[i][j] > max) {
						max = result[i][j];
					}
				}
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int[][] input = {{1,1,1,1},{1,1,1,1},{0,1,1,1},{1,1,1,1}};
		System.out.println(largestSquareOfOnes(input));
		System.out.println(largestSquareOfOnes(new int[][]{{1}}));
		System.out.println(largestSquareOfOnes(new int[][]{{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}));
	}
}
