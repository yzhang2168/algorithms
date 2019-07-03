package DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ParenthesesIII {

	/**
	 * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
	 * priority restriction: {} > [] > ()
	 * ASCII: '{' > '[' > '('
	 * 		  '}' > ']' > ')'
	 * Assumptions
	 * l, m, n >= 0
	 * Examples
	 * l = 1, m = 1, n = 0, 
	 * all the valid permutations are [{}[], {[]}, []{}]
	 * valid: {[()]}
	 * invalid: ([{}])
	 * 
	 * # levels: 2l + 2m + 2n
	 * # states on each level: 6, ()<>{}
	 * 								""							
	 * 			/		/		|		|		\		\
	 * 			(		)		<		>		{		}
	 * 		 //||\\	  //||\\  //||\\  //||\\  //||\\  //||\\
	 * 	...														#levels: 2(l+m+n)
	 *  ...
	 * 	((()))<<>>{}  ((()))<<>{}>  ((()))<<{}>>...
	 * 
	 * */

	public static List<String> validParentheses(int l, int m, int n) {
		int[] numsToAdd = new int[] {l, l, m, m, n, n};
		Character[] parentheses = new Character[] {'{', '}', '[', ']', '(', ')'};
		int levels = 2 * (l + m + n);
		
		List<String> result = new ArrayList<String>();
		StringBuilder solutionPrefix = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<Character>(); 
		
		validParentheses(parentheses, numsToAdd, levels, stack, solutionPrefix, result);
		return result;
	}
	
	private static void validParentheses(Character[] parentheses, int[] numsToAdd, int levels, Deque<Character> stack, StringBuilder solutionPrefix, List<String> result) {
		// base case
		if (solutionPrefix.length() == levels) {
			result.add(solutionPrefix.toString());
			return;
		}
		
		// # levels: # positions 2 (l + m + n)
		// At each level: 6 branches
		
		for (int i = 0; i < parentheses.length - 1; i++) {
			if (i % 2 == 0) {
				// case 1: + parentheses[i] open parenthesis
				if ((numsToAdd[i] > 0 && stack.isEmpty()) || (numsToAdd[i] > 0 && !stack.isEmpty() && stack.peekFirst() >= parentheses[i])) {
					solutionPrefix.append(parentheses[i]);
					stack.addFirst(parentheses[i]);
					numsToAdd[i]--;
					
					validParentheses(parentheses, numsToAdd, levels, stack, solutionPrefix, result);
					
					solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
					stack.removeFirst();
					numsToAdd[i]++;
				}

				// case 2: + parentheses[i + 1]
				if (numsToAdd[i + 1] > numsToAdd[i] && !stack.isEmpty() && stack.peekFirst() == parentheses[i]) {
					solutionPrefix.append(parentheses[i + 1]);
					stack.removeFirst();
					numsToAdd[i + 1]--;
					
					validParentheses(parentheses, numsToAdd, levels, stack, solutionPrefix, result);
					
					solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
					stack.offerFirst(parentheses[i]);
					numsToAdd[i + 1]++;
				}
			}
		}	
	}
	
	
	public static void main(String[] args) {
		System.out.println('{' > '[');
		System.out.println('[' > '(');
		System.out.println('}' > ']');
		System.out.println(']' > ')');
		System.out.println(validParentheses(0, 0, 0));
		System.out.println(validParentheses(1, 1, 0));
		System.out.println(validParentheses(3, 0, 0));
		System.out.println(validParentheses(3, 1, 0));
		System.out.println(validParentheses(3, 2, 1));
		
	}
}
