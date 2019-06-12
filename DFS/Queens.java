package DFS;

import java.util.ArrayList;
import java.util.List;

public class Queens {

	/**
	 * 8 * 8 board, place 8 queens, 1 queen per row, only need to check which col to place queens
	 * permutation of 0-7 columns
	 * 
	 * 						empty
	 * 					/||||||\
	 * level = 0	(0,0) (0,1) ... (0,7)
	 * 				/|||||\ /|||||\ /|||||\
	 * level = 1  (1,1) (1,2)
	 * 
	 * level = 2
	 * ...
	 * level = 8 base case add to solution
	 * 
	 * */
	public static List<List<Integer>> eightQueens() {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		List<Integer> currComb = new ArrayList<Integer>();
		int currRow = 0;
		eightQueens(solution, currComb, currRow);
		return solution;
	}
	
	private static void eightQueens(List<List<Integer>> solution, List<Integer> currComb, int currRow) {
		// base case
		if (currRow == 8) {
			solution.add(new ArrayList<Integer>(currComb));
			return;
		}
		
		// recursion: permute over 0-7 cols
		for (int col = 0; col < 8; col++) {
			if (valid(currComb, col)) {
				currComb.add(col);
				eightQueens(solution, currComb, currRow + 1);
				currComb.remove(currComb.size() - 1);
			}
		}
	}
	
	private static boolean valid(List<Integer> currComb, int col) {
		int numRows = currComb.size();
		for (int i = 0; i < numRows; i++) {
			if (currComb.get(i) == col || Math.abs(currComb.get(i) - col) == numRows - i) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(eightQueens());		
	}

}
