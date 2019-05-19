package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

public class SubsetsOfKSize {

	/**
	 * given an array of size n,
	 * output all combinations of k elements in the array
	 * 
	 * subsets: base case: level == array.length || size == k
	 * 
	 * 						{}
	 * 				/				 \
	 * a		   a				  	{}
	 * 		     /	 \				/	    \	
	 * b		ab	  a	 		  b		 	{}
	 * 		  	     /  \		 /  \		/  \
	 * c			ac   a 		bc 	 b 	   c	{}
	 *				    / \     	/ \	  / \   / \
	 * d 			   ad  a  	    bd b cd  c d   {}
	 * 
	 * no duplicates
	 * time: O(2 ^ n)
	 * space: O(n), n levels * O(1)
	 * */
	
	public static List<List<Integer>> subsetsKSize (int[] array, int k) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		if (k <= 0 || k >= array.length) {
			return solution;
		}
		
		List<Integer> currSubset = new ArrayList<Integer>();
		
		subsetsKSize(array, k, 0, currSubset, solution);
		return solution;
	}
	
	private static void subsetsKSize (int[] array, int k, int level, List<Integer> currSubset, List<List<Integer>> solution) {
		// base case: subset size is k
		// a trimmed DFS tree
		if (currSubset.size() == k || level == array.length) {
			if (currSubset.size() == k) {
				solution.add(new ArrayList<Integer>(currSubset));
			}
			return;
		}
		
		// recursion
		currSubset.add(array[level]);
		subsetsKSize(array, k, level + 1, currSubset, solution);
		currSubset.remove(currSubset.size() - 1);

		subsetsKSize(array, k, level + 1, currSubset, solution);
	}
	
	
	public static List<List<Integer>> subsetsKSize0 (int[] array, int k) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		if (k <= 0 || k >= array.length) {
			return solution;
		}
		
		List<Integer> currSubset = new ArrayList<Integer>();
		
		subsetsKSize0(array, k, 0, currSubset, solution);
		return solution;
	}
	
	private static void subsetsKSize0(int[] array, int k, int level, List<Integer> currSubset, List<List<Integer>> solution) {
		// base
		if (level == array.length) {			
			if (currSubset.size() == k) {
				solution.add(new ArrayList<Integer>(currSubset));
			}
			return;
		}
		
		// recursion
		currSubset.add(array[level]);
		subsetsKSize0(array, k, level + 1, currSubset, solution);
		currSubset.remove(currSubset.size() - 1);
		
		subsetsKSize0(array, k, level + 1, currSubset, solution);		
	}
	
	public static void main(String[] args) {
		int[] array0 = {};
		int[] array1 = {2, 1, 3, 4};
		System.out.println(subsetsKSize(array0, 1));
		System.out.println(subsetsKSize(array1, 0));
		System.out.println(subsetsKSize(array1, 1));
		System.out.println(subsetsKSize(array1, 2));
		System.out.println(subsetsKSize(array1, 3));
	}	

}
