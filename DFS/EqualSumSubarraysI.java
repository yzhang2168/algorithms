package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

public class EqualSumSubarraysI {

	/**
	 * given an array of integers
	 * how to divide the whole array into two parts with their sum equal to each other
	 * find all subsets with sum = total sum / 2
	 * 
	 * Example: input = {2, 1, 3, 4}
	 * 			output = {2, 3}, {1, 4}
	 * 
	 * testing example: include negative values
	 * need to get down to the bottom level such that all combinations are tried. 
	 * should not terminate early when sum == half because that would eliminate possible combination.
	 * */
	public static List<List<Integer>> findEqualSubarrays(int[] array) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		List<Integer> currSubset = new ArrayList<Integer>();
		
		if (array == null || array.length == 0) {
			return solution;
		}
		
		int sum = 0;
		for (int i : array) {
			sum = sum + i;
		}
		int half = sum / 2;
		
		findAllSubsets(array, half, 0, currSubset, solution);		
		return solution;
	}
	
	private static void findAllSubsets(int[] array, int half, int level, List<Integer> currSubset, List<List<Integer>> solution) {
		// base case
		if (level == array.length) {
			int currSum = 0;
			for (int i : currSubset) {
				currSum = currSum + i;
			}
			
			if (currSum == half) {
				solution.add(new ArrayList<Integer>(currSubset));
			}
			return;
		}
		
		// recursion
		currSubset.add(array[level]);
		findAllSubsets(array, half, level + 1, currSubset, solution);
		currSubset.remove(currSubset.size() - 1);

		findAllSubsets(array, half, level + 1, currSubset, solution);
	}
	
	
	public static void main(String[] args) {
		int[] array0 = {};
		int[] array1 = {2, 1, 3, 4};
		int[] array2 = {-10, -6, 6, 1, -1};
		System.out.println(findEqualSubarrays(array0));
		System.out.println(findEqualSubarrays(array1));
		System.out.println(findEqualSubarrays(array2));
	}	

}
