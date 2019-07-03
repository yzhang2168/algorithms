package DFS;

import java.util.ArrayList;
import java.util.List;

public class IfBlocks {

	/**
	 * Given an integer n, print/output all possible if blocks for it. Say n=2 output should be
if {
}
if {
}
<newline>
if {
  if {// here should exist two spaces before each inner block
  }
}
	 * time: 2^2n 
	 * space: n
	 * */

	// example solution
	public static void ifBlocks(int n) {
		if (n <= 0) {
			return;
		}
		
		char[] comb = new char[2 * n];
		ifBlocksHelper(n, 0, 0, 0, comb);
		System.out.println(comb);
	}
	
	private static void ifBlocksHelper(int n, int level, int l, int r, char[] comb) {
		if (l == n && r == n) {
			printSolution(comb);
			//System.out.println(comb);
			return;
		}
		
		if (l < n) {
			comb[level] = '{';
			ifBlocksHelper(n, level + 1, l + 1, r, comb);
			//comb[level] = ' ';// will be overwritten
		}
		
		if (r < l) {
			comb[level] = '}';
			ifBlocksHelper(n, level + 1, l, r + 1, comb);
			//comb[level] = ' '; // will be overwritten
		}
	}
	
	private static void printSolution(char[] input) {
		int indent = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == '{') {
				printSpaces(indent);
				System.out.println("if () {");
				indent += 2;
			} else if (input[i] == '}') {
				indent -= 2; // -2 before print
				printSpaces(indent);
				System.out.println("}");
			} 
		}
		System.out.println();
	}
	
	private static void printSpaces(int n) {
		while (n > 0) {
			System.out.print(" ");
			n--;
		}
	}
	
	// not best solution
	public static void validParentheses(int n) {
		List<String> result = new ArrayList<String>();
		
		if (n <= 0) {
			return;
		}
		
		StringBuilder prefix = new StringBuilder();
		validParenthesesHelper(n, 0, 0, prefix, result);
		printResult(result);
	}
	
	private static void validParenthesesHelper(int n, int l, int r, StringBuilder prefix, List<String> result) {
		if (l == n && r == n) {
			result.add(new String(prefix));
			return;
		}
		
		if (l < n) {
			prefix.append("(");
			validParenthesesHelper(n, l + 1, r, prefix, result);
			prefix.deleteCharAt(prefix.length() - 1);
		}
		
		if (r < l) {
			prefix.append(")");
			validParenthesesHelper(n, l, r + 1, prefix, result);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	
	private static void printResult(List<String> result) {
		int indent = 0;
		for (String combo : result) {
			for (int i = 0; i < combo.length(); i++) {
				if (combo.charAt(i) == '(') {
					int j = 0;
					while (j < indent) {
						System.out.print(" "); // indent
						j++;
					}
					System.out.println("if () {");
					indent = indent + 2;
				} else if (combo.charAt(i) == ')') {
					indent = indent - 2;
					int j = 0;
					while (j < indent) {
						System.out.print(" "); // indent
						j++;
					}
					System.out.println("}");
				}				
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		//validParentheses(2);
		ifBlocks(2);
	}
}
