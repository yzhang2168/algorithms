package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

public class SubsetsIIOfKSize {
	/**
	 * wrong definition, could call it subsequences
	 * given a sorted string of chars with DUPLICATES, return all subsequences
	 * example input: 1,2,2,2,3
	 * output: 
	 * 
	 * exponential time, if input is not sorted, sort first nlogn 
	 * */

	public static List<List<Integer>> findSubsetsIIKSize (int[] array, int k) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		if (k <= 0 || k >= array.length) {
			return solution;
		}
		
		List<Integer> currSubset = new ArrayList<Integer>();
		
		findSubsetsIIKSize(array, k, 0, currSubset, solution);
		return solution;
	}
	
	private static void findSubsetsIIKSize(int[] array, int k, int level, List<Integer> currSubset, List<List<Integer>> solution) {
		// base case
		if (currSubset.size() == k || level == array.length) {
			if (currSubset.size() == k) {
				solution.add(new ArrayList<Integer>(currSubset));
			}
			return;			
		}
		
		// case 1: add array[level]
		currSubset.add(array[level]);
		findSubsetsIIKSize(array, k, level + 1, currSubset, solution);
		currSubset.remove(currSubset.size() - 1);
		
		// case 2: add ""
		while (level < array.length - 1 && array[level + 1] == array[level]) {
			level++;
		}
		findSubsetsIIKSize(array, k, level + 1, currSubset, solution);
	}
	
	
	public static void main(String[] args) {
		int[] array0 = {};
		int[] array1 = {1, 2, 2, 2, 3, 4};
		System.out.println(findSubsetsIIKSize(array0, 1));
		System.out.println(findSubsetsIIKSize(array1, 0));
		System.out.println(findSubsetsIIKSize(array1, 1));
		System.out.println(findSubsetsIIKSize(array1, 2));
		System.out.println(findSubsetsIIKSize(array1, 3));
	}	

}
