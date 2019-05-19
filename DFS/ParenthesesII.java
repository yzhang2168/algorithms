package algorithms.DFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class ParenthesesII {

	/**
	 * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
	 * Assumptions
	 * l, m, n >= 0
	 * Examples
	 * l = 1, m = 1, n = 0, 
	 * all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
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
	 * */
	public static List<String> validParentheses0(int l, int m, int n) {
		int[] numsToAdd = new int[] {l, l, m, m, n, n};
		int levels = 2 * (l + m + n);
		List<String> result = new ArrayList<String>();
		StringBuilder solutionPrefix = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<Character>(); 
		
		validParentheses0(numsToAdd, levels, 0, stack, solutionPrefix, result);
		return result;
	}
	
	private static void validParentheses0(int[] numsToAdd, int levels, int level, Deque<Character> stack, StringBuilder solutionPrefix, List<String> result) {
		// base case
		if (level == levels) {
		//if (solutionPrefix.length() == levels) {
			result.add(solutionPrefix.toString());
			return;
		}
		
		// 6 branches
		if (numsToAdd[0] > 0) {
			solutionPrefix.append('(');
			stack.addFirst('(');
			numsToAdd[0]--;
			
			validParentheses0(numsToAdd, levels, level + 1, stack, solutionPrefix, result);
			
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
			stack.removeFirst();
			numsToAdd[0]++;
		}
		
		if (numsToAdd[1] > numsToAdd[0] && !stack.isEmpty() && stack.peekFirst() == '(') {
			solutionPrefix.append(')');
			stack.removeFirst();
			numsToAdd[1]--;
			
			validParentheses0(numsToAdd, levels, level + 1, stack, solutionPrefix, result);
			
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
			stack.addFirst('(');
			numsToAdd[1]++;
		}
		
		if (numsToAdd[2] > 0) {
			solutionPrefix.append('<');
			stack.addFirst('<');
			numsToAdd[2]--;
			validParentheses0(numsToAdd, levels, level + 1, stack, solutionPrefix, result);
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
				stack.removeFirst();
			numsToAdd[2]++;
		}
		
		if (numsToAdd[3] > numsToAdd[2] && !stack.isEmpty() && stack.peekFirst() == '<') {
			solutionPrefix.append('>');
			stack.removeFirst();
			numsToAdd[3]--;
			
			validParentheses0(numsToAdd, levels, level + 1, stack, solutionPrefix, result);
			
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
			stack.addFirst('<');
			numsToAdd[3]++;
		}
		
		if (numsToAdd[4] > 0) {
			solutionPrefix.append('{');
			stack.addFirst('{');
			numsToAdd[4]--;
			
			validParentheses0(numsToAdd, levels, level + 1, stack, solutionPrefix, result);
			
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
			stack.removeFirst();
			numsToAdd[4]++;
		}
		
		if (numsToAdd[5] > numsToAdd[4] && !stack.isEmpty() && stack.peekFirst() == '{') {
			solutionPrefix.append('}');
			stack.removeFirst();
			numsToAdd[5]--;
			
			validParentheses0(numsToAdd, levels, level + 1, stack, solutionPrefix, result);
			
			solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
			stack.addFirst('{');
			numsToAdd[5]++;
		}
		
	}
	
	public static List<String> validParentheses(int l, int m, int n) {
		int[] numsToAdd = new int[] {l, l, m, m, n, n};
		char[] parentheses = new char[] {'(', ')', '<', '>', '{', '}'};
		int levels = 2 * (l + m + n);
		
		List<String> result = new ArrayList<String>();
		StringBuilder solutionPrefix = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<Character>(); 
		
		validParentheses(parentheses, numsToAdd, levels, stack, solutionPrefix, result);
		return result;
	}
	
	private static void validParentheses(char[] parentheses, int[] numsToAdd, int levels, Deque<Character> stack, StringBuilder solutionPrefix, List<String> result) {
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
				if (numsToAdd[i] > 0) {
					solutionPrefix.append(parentheses[i]);
					stack.offerFirst(parentheses[i]);
					numsToAdd[i]--;
					
					validParentheses(parentheses, numsToAdd, levels, stack, solutionPrefix, result);
					
					solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
					stack.pollFirst();
					numsToAdd[i]++;
				}

				// case 2: + parentheses[i + 1]
				if (numsToAdd[i + 1] > numsToAdd[i] && !stack.isEmpty() && stack.peekFirst() == parentheses[i]) {
					solutionPrefix.append(parentheses[i + 1]);
					stack.pollFirst();
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
		//System.out.println(validParentheses0(0, 0, 0));
		//System.out.println(validParentheses0(3, 0, 0));
		//System.out.println(validParentheses0(3, 1, 1));
		//System.out.println(validParentheses(0, 0, 0));
		//System.out.println(validParentheses(3, 0, 0));
		//System.out.println(validParentheses(3, 1, 0));
		//System.out.println(validParentheses0(3, 2, 1));
		System.out.println(validParentheses(3, 2, 1));
	}
	
}
