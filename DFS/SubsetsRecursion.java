package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of characters represented by a String, return a list containing all subsets of the characters.
 * Assumptions
 * There are no duplicate characters in the original set.
 * ​Examples
 * Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
 * Set = "", all the subsets are [""]
 * Set = null, all the subsets are []
 * 
 * pure recursion solution
 */
public class SubsetsRecursion {

	public static List<String> subsets(String input) {
		List<String> result = new ArrayList<String>();
		if (input == null || input.length() == 0) {
			return result;
		}
		
		findSubsetsRecursion(input, 0, result);
		return result;
	}
	
	private static void findSubsetsRecursion(String input, int index, List<String> result) {
		// base case
		if (index == input.length()) {
			result.add("");
			return;
		}
		
		// subproblem: result already has solution for [index + 1...end]
		findSubsetsRecursion(input, index + 1, result);
		
		// logic at curr level
		int size = result.size();
		for (int i = 0; i < size; i++) {
			result.add(result.get(i) + input.charAt(index));
		}
	}
}
