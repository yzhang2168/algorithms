package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * given a 2d array n x n, fill out the array in spiral order
 *  1  2  3  4 5
 * 16 17 18 19 6
 * 15 24 25 20 7
 * 14 23 22 21 8
 * 13 12 11 10 9
 * */
public class MatrixSpiralTraversalIterative {

	public static List<Integer> spiralIterative(int[][] input) {
		List<Integer> result = new ArrayList<Integer>();

		if (input == null || input.length == 0 || input.length != input[0].length) {
			return result;
		}
		
		int start = 0;
		int end = input.length - 1;
		
		// base case: 0 or 1 element (start == end) in the submatrix
		while (start < end) {
			for (int i = start; i < end; i++) {
				result.add(input[start][i]);
			}
			
			for (int i = start; i < end; i++) {
				result.add(input[i][end]);
			}
			
			for (int i = end; i > start; i--) {
				result.add(input[end][i]);
			}
			
			for (int i = end; i > start; i--) {
				result.add(input[i][start]);
			}
			start++;
			end--;
		}
		
		if (start == end) {
			result.add(input[start][start]);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[][] test1 = null; 
		System.out.println(spiralIterative(test1));
		
		int[][] test2 = new int[0][0]; 
		System.out.println(spiralIterative(test2));
		
		int[][] test3 = new int[][]{{1}}; 
		System.out.println(spiralIterative(test3));
		
		int[][] test4 = new int[][] {{1,2,3,4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}; 
		System.out.println(spiralIterative(test4));
		
		int[][] test5 = new int[][] {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}}; 
		System.out.println(spiralIterative(test5));
	}
	
}
