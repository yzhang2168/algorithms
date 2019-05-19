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
 * DFSII, as an FYI
 * 	
 *					/	  |		\
 * 		          a		  b		 c			
 * 			    /	\	  |
 *      	  ab	 ac   b
 * 			   |	
 *           abc	
 * base case
 * 
 * time: O(2 ^ n)
 * 		branching factor = 2, 1 + 2 + 4 + ... 2 ^ n = 2 ^ (n + 1)
 * 		Cn0 + Cn1 + ... + Cnn = 2 ^ n * 2
 * */

public class SubsetsDFSII {

	public static List<String> findSubsets(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		
		char[] input = set.toCharArray(); // set == null, NPE
		StringBuilder resultPrefix = new StringBuilder();
		findSubsets(input, 0, resultPrefix, result);
		return result;
	}
	
	private static void findSubsets(char[] input, int index, StringBuilder resultPrefix, List<String> result) {
		// add each node to result
		// base case is implicit: index == input.length
		result.add(resultPrefix.toString());
		
		// maintains ascending order of the indices of picked chars
		// choose the next index in range [index...end]
		for (int i = index; i < input.length; i++) {
			resultPrefix.append(input[i]);
			findSubsets(input, index + 1, resultPrefix, result);
			resultPrefix.deleteCharAt(resultPrefix.length() - 1);
		}
	}

}
