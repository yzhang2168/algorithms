package DFS;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

	/**
	 * 8 * 8 board
	 * queen: rules its row, col, and 2 diagonal lines
	 * 
	 * place 8 queens
	 * 1 queen/row
	 * 1 queen/col
	 * it's 1D rather than 2D: only need to think about which col to place each queen
	 * permutations of 0-7
	 * 
	 * root = 0 queens have been put on the board
	 * 							/		|		|		|		|		|		|		\
	 * level 1: Q0 is put on (0,0)   Q0 (0,1) ...									Q0 (0,7)
	 * 					   /||||||\  /||||||\										/||||||\
	 * level 2: Q1 is put on valid positions
	 * ...
	 * level 8: 
	 * 
	 * DFS by row
	 * base case: the last row is done, curr row == 8
	 * recursive rule: if position(i,j) is valid, go to the next row i+1
	 * 
	 * time: O(8^8 * 8)
	 * 
	 * int A[n] stores the current solution 
	 * A[8] = {1, 3, 4,...}
	 * index: row number
	 * A[index]: col number
	 * 1 stands for 1st queen in (1, 1)
	 * 3 stands for 2nd queen in (2, 3)
	 * 
	 * with obstacles: one row is broken into 2 rows
	 * */	
	// place 8 queens, each queen at (i, list.get(i))
	public static List<List<Integer>> eightQueens() {
		List<List<Integer>> result = new ArrayList<List<Integer>>(); 
		List<Integer> curr = new ArrayList<Integer>();		
		eightQueens(result, curr);
		return result;
	}
	
	private static void eightQueens(List<List<Integer>> result, List<Integer> curr) {
		if (curr.size() == 8) {
			result.add(new ArrayList<Integer>(curr));
			return;
		}
		
		// try all n cols to place a new queen in the curr row
		for (int col = 0; col < 8; col++) {
			// check if the position is valid considering result[0...currRow - 1]
			if (validPosition(curr, col)) {
				// col: i
				// row: curr.size() 
				curr.add(col);
				eightQueens(result, curr);
				curr.remove(curr.size() - 1);
			}
		}
	}
	
	private static boolean validPosition(List<Integer> curr, int col) {
		int numRows = curr.size();
		// check all previous rows
		for (int i = 0; i < numRows; i++) {
			if (curr.get(i) == col || Math.abs(curr.get(i) - col) == numRows - i) {
				return false;
			}
		}
		return true;
	}
	
	
	// place n queens
	public static List<List<Integer>> nQueens(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (n <= 0) {
			return result;
		}
		
		List<Integer> curr = new ArrayList<Integer>();
		nQueenshelper(n, curr, result);
		return result;
	}
	
	private static void nQueenshelper(int n, List<Integer> curr, List<List<Integer>> result) {
		// base case
		if (curr.size() == n) {
			result.add(new ArrayList<Integer>(curr));
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (valid(curr, i)) {
				curr.add(i);
				nQueenshelper(n, curr, result);
				curr.remove(curr.size() - 1);
			}
		}
	}
	
	private static boolean valid(List<Integer> curr, int col) {
		int row = curr.size();
		for (int i = 0; i < row; i++) {
			if (curr.get(i) == col || Math.abs(curr.get(i) - col) == row - i) {
				return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		System.out.println(eightQueens());
		System.out.println();
		
		System.out.println(nQueens(8));
		System.out.println();

		System.out.println(nQueens(6));
	}	
}
