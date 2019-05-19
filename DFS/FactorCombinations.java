package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {

	public static List<List<Integer>> findFactorCombinations(int n) {
		List<List<Integer>> solution = new ArrayList<List<Integer>>();
		List<Integer> factors = findFactors(n);
		List<Integer> currComb = new ArrayList<Integer>();
		
		if (n <= 1) {
			currComb.add(n);
			solution.add(new ArrayList<Integer>(currComb));
			return solution;
		}
		
		findFactorCombinations(n, factors, 0, currComb, solution);
		
		return solution;
	}
	
	private static void findFactorCombinations(int n, List<Integer> factors, int level, List<Integer> currComb, List<List<Integer>> solution) {
		//TODO
	}
	
	// excluding n and 1
	public static List<Integer> findFactors(int n) {
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = n / 2; i > 1; i--) {
			if (n % i == 0) {
				factors.add(i);
			}
		}
		return factors;
	}
	
	
	public static void main(String[] args) {
		System.out.println(findFactors(0));
		System.out.println(findFactors(1));
		System.out.println(findFactors(12));
		
		System.out.println(findFactorCombinations(0));
		System.out.println(findFactorCombinations(1));
		System.out.println(findFactorCombinations(12));
	}
}
