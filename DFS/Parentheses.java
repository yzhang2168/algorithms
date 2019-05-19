package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

public class Parentheses {
	/**
	 * 					/		  \
	 * pos 0	     (			       )			
	 * 			   /	 \		   /	   \
	 * pos 1	 ((		  ()	  )(		 ))
	 * 			/	\	 /	\	 /   \	   /	\	
	 * pos 2  (((  (()	()(	())	)((	 )()  ))(   )))	
	 * ...
	 * pos 2n -1
	 * base case
	 * 
	 * time: O(2 ^ 2n)
	 * */
	public static List<String> validParentheses(int n) {
		List<String> result = new ArrayList<String>();
		StringBuilder solutionPrefix = new StringBuilder();
		validParentheses(n, 0, 0, solutionPrefix, result);
		return result;
	}
	
	private static void validParentheses(int n, int l, int r, StringBuilder solutionPrefix, List<String> result) {
		// base case having added all 2 * n positions
		if (l == n && r == n) {
			result.add(solutionPrefix.toString());
			return;
		}
		
		// left subtree
		if (l < n) {
			solutionPrefix.append('(');
			validParentheses(n, l + 1, r, solutionPrefix, result);
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
		}
		
		// right subtree
		if (r < l) {
			solutionPrefix.append(')');
			validParentheses(n, l, r + 1, solutionPrefix, result);
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(validParentheses(0));
		System.out.println(validParentheses(3));
	}
}
