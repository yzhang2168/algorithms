package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QueensOptimized {
	
	/**
	 * checks validity of new col position with 3 hashsets at O(1) average
	 * */
	public List<List<Integer>> nqueens(int n) {
	      List<List<Integer>> result = new ArrayList<List<Integer>>();
	      List<Integer> curr = new ArrayList<Integer>();
	      HashSet<Integer> usedCols = new HashSet<>();
	      HashSet<Integer> usedDiagonals = new HashSet<>();
	      HashSet<Integer> usedRevDiagonals = new HashSet<>();
	      nqueens(n, result, curr, 0, usedCols, usedDiagonals, usedRevDiagonals);
	      return result;
	  }

	  private void nqueens(int n, List<List<Integer>> result, List<Integer> curr, int row, HashSet<Integer> usedCols, HashSet<Integer> usedDiagonals, HashSet<Integer> usedRevDiagonals) {
	      if (row == n) {
	          result.add(new ArrayList<>(curr));
	          return;
	      }

	      for (int col = 0; col < n; col++) {
	          if (isValid(row, col, usedCols, usedDiagonals, usedRevDiagonals)) {
	              mark(row, col, usedCols, usedDiagonals, usedRevDiagonals);
	              curr.add(col);
	              nqueens(n, result, curr, row + 1, usedCols, usedDiagonals, usedRevDiagonals);
	              unmark(row, col, usedCols, usedDiagonals, usedRevDiagonals);
	              curr.remove(curr.size() - 1);
	          }
	      }
	  }

	  private boolean isValid(int row, int col, HashSet<Integer> usedCols, HashSet<Integer> usedDiagonals, HashSet<Integer> usedRevDiagonals) {
	      return !usedCols.contains(col) && !usedDiagonals.contains(col - row) && !usedRevDiagonals.contains(col + row);
	  }

	  private void mark(int row, int col, HashSet<Integer> usedCols, HashSet<Integer> usedDiagonals, HashSet<Integer> usedRevDiagonals) {
	      usedCols.add(col);
	      usedDiagonals.add(col - row);
	      usedRevDiagonals.add(col + row);
	  }

	  private void unmark(int row, int col, HashSet<Integer> usedCols, HashSet<Integer> usedDiagonals, HashSet<Integer> usedRevDiagonals) {
	      usedCols.remove(col);
	      usedDiagonals.remove(col - row);
	      usedRevDiagonals.remove(col + row);
	  }
	  
	  public List<List<Integer>> nqueensI(int n) {
	      List<List<Integer>> result = new ArrayList<List<Integer>>();
	      List<Integer> curr = new ArrayList<Integer>();
	      boolean[] usedCols = new boolean[n];
	      boolean[] usedDiagonals = new boolean[2 * n - 1];
	      boolean[] usedRevDiagonals = new boolean[2 * n - 1];
	      helper(n, result, curr, 0, usedCols, usedDiagonals, usedRevDiagonals);
	      return result;
	  }
	  
	  private void helper(int n, List<List<Integer>> result, List<Integer> curr, int row, boolean[] usedCols, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
	      if (row == n) {
	          result.add(new ArrayList<>(curr));
	          return;
	      }
	      
	      for (int col = 0; col < n; col++) {
	    	  if (valid(n, row, col, usedCols, usedDiagonals, usedRevDiagonals)) {
	    		  mark(n, row, col, usedCols, usedDiagonals, usedRevDiagonals);
	    		  curr.add(col);
	    		  helper(n, result, curr, row + 1, usedCols, usedDiagonals, usedRevDiagonals);
	    		  unmark(n, row, col, usedCols, usedDiagonals, usedRevDiagonals);
                  curr.remove(curr.size() - 1);
	    	  }
	      }
	  }
	  
	  private boolean valid(int n, int row, int col, boolean[] usedCols, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		  return !usedCols[col] && !usedDiagonals[col + row] && !usedRevDiagonals[col - row + n - 1];
	  }
	  
	  private void mark(int n, int row, int col, boolean[] usedCols, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		  usedCols[col] = true;
		  usedDiagonals[col + row] = true;
		  usedRevDiagonals[col - row + n - 1] = true;
	  }
	  
	  private void unmark(int n, int row, int col, boolean[] usedCols, boolean[] usedDiagonals, boolean[] usedRevDiagonals) {
		  usedCols[col] = false;
		  usedDiagonals[col + row] = false;
		  usedRevDiagonals[col - row + n - 1] = false;
	  }
}
