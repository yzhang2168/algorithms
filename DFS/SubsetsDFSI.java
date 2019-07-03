package DFS;

import java.util.ArrayList;
import java.util.List;

public class SubsetsDFSI {
	/**
	 * Given a set of characters represented by a String, return a list containing all subsets of the characters.
	 * Assumptions
	 * There are no duplicate characters in the original set.
	 * ​Examples
	 * Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
	 * Set = "", all the subsets are [""]
	 * Set = null, all the subsets are []
	 * 						
	 *					/			\
	 * pos 0|a		  a							
	 * 			    /	 \		   /	\
	 * pos 1|b	  ab	  a		 b		 
	 * 			/	\	 /	\	/	\	/	\	
	 * pos 2|c abc	ab	ac	a	bc	b   c	
	 * base case
	 * 
	 * time: O(2 ^ n)
	 * 		branching factor = 2, 1 + 2 + 4 + ... 2 ^ n = 2 ^ (n + 1)
	 * 		Cn0 + Cn1 + ... + Cnn = 2 ^ n * 2
	 * */
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
		// base case, having traversed all chars in input, save in result
		// covers corner case set = "", empty resultPrefix.toString() returns ""
		if (index == input.length) {
			result.add(resultPrefix.toString());
			// do not forget return, otherwise recursion will keep going deep
			return;
		}
		
		// left subtree
		// case 1: left child: add input[index] to solutionPrefix
		resultPrefix.append(input[index]);
		// breaking point: index + 1 - next level in recursion tree
		// traverse all left subtree, return here
		findSubsets(input, index + 1, resultPrefix, result);
		// delete added char in curr node
		resultPrefix.deleteCharAt(resultPrefix.length() - 1);

		// right subtree
		// case 2: right child: add nothing to solutionPrefix
		findSubsets(input, index + 1, resultPrefix, result);		
	}
	
	
	/**
	 * pure recursion solution
	 * recursion tree: a single chain
	 * time: add up all layers 2^(n-1) + 2^(n-2) + 2^(n-3) + ... + 1 = 2^n
	 * StringBuilder can optimize the str + str concatenation
	 * */
	public static List<String> findSubsetsRecursion(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		char[] input = set.toCharArray();
		findSubsetsRecursion(input, 0, result);
		return result;
	}
	
	/*
	 * problem   : subsets for set index 0...end
	 * subproblem: subsets for set index 1...end
	 * base case : set is empty, add "" to result
	 * recursion rule: result for subproblem + (curr char added to all strings in result)
	 * */
	private static void findSubsetsRecursion(char[] input, int index, List<String> result) {
		// base case: input is empty, add "" to result
		if (index == input.length) {
			result.add("");
			return;
		}
		
		// assume result now has solution to subproblem, do logic on the current layer
		findSubsetsRecursion(input, index + 1, result);
		//System.out.println("current result: " + result);
		int size = result.size();
		for (int i = 0; i < size; i++) {
			// with result from subproblems, double the result by adding each string + input[index]
			result.add(result.get(i) + input[index]);
		}
	}
	
	public static List<String> findSubsetsRecursion2(String set) {
		List<String> result = new ArrayList<String>();
		if (set == null) {
			return result;
		}
		
		char[] input = set.toCharArray();
		List<StringBuilder> resultSB = new ArrayList<StringBuilder>();
		findSubsetsRecursion2(input, 0, resultSB);
		// convert each StringBuilder object to a String
		for (StringBuilder e : resultSB) {
			result.add(e.toString());
		}
		return result;
	}
	
	/*
	 * problem   : subsets for set index 0...end
	 * subproblem: subsets for set index 1...end
	 * base case : set is empty, add "" to result
	 * recursion rule: result for subproblem + (curr char (index = 0) added to all strings in result)
	 * */
	private static void findSubsetsRecursion2(char[] input, int index, List<StringBuilder> resultSB) {
		if (index == input.length) {
			resultSB.add(new StringBuilder(""));
			return;
		}
		
		// assume result now has solution to subproblem, do logic on the current layer
		findSubsetsRecursion2(input, index + 1, resultSB);
		
		int size = resultSB.size();
		for (int i = 0; i < size; i++) {
			// with result from subproblems, double the result by adding each char + curr char
			//resultSB.add(new StringBuilder(resultSB.get(i).append(input[index]))); // --> identical StringBuilder objects???
			
			// resultSB.get(i).append(input[index]) returns a ref to this object
			// better to use sb.toString()
			resultSB.add(new StringBuilder(resultSB.get(i)).append(input[index])); // make a new object and then modify it
		}
	}
	
	
	public static void main(String[] args) {
		StringBuilder empty = new StringBuilder();
		System.out.println(empty.toString());
		System.out.println(findSubsets(null));
		System.out.println(findSubsets(""));
		System.out.println(findSubsets("abc"));
		System.out.println(findSubsetsRecursion("abc"));
		System.out.println(findSubsetsRecursion2("abc"));
	}
	

}
