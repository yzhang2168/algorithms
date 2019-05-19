package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

public class EqualSumSubarraysII {

	public static List<List<Integer>> equalSumSubarrays(int[] input) {		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> comb = new ArrayList<Integer>();
		
		int sum = 0;
		for (int n : input) {
			sum += n;
		}
		equalSumSubarrayHelper(input, sum / 2, 0, comb, result);
		return result;
	}
	
	
	private static void equalSumSubarrayHelper(int[] input, int sum, int level, List<Integer> comb, List<List<Integer>> result) {
		 if (level == input.length) {
			 if (sum == 0) {
				 result.add(new ArrayList<Integer>(comb));
			 }
			 return;
		}
		
		comb.add(input[level]);
		sum -= input[level];
		equalSumSubarrayHelper(input, sum, level + 1, comb, result);
		comb.remove(comb.size() - 1);
		sum += input[level];
		
		equalSumSubarrayHelper(input, sum, level + 1, comb, result);
	}
	
	
	public static void main(String[] args) {
		int[] array0 = {};
		int[] array1 = {2, 1, 3, 4};
		int[] array2 = {-10, -6, 6, 1, -1};

		System.out.println(equalSumSubarrays(array0));
		System.out.println(equalSumSubarrays(array1));
		System.out.println(equalSumSubarrays(array2));
	}
}
