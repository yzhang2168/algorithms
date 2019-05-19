package algorithms.recursion.deserialization;

import java.util.Collections;
/**
 * Given an integer n, print/output all possible if blocks for it. 
 * Say n=2 output should be
	if {
	}
	if {
	}	
	<newline>
	if {
	  if {// here should exist two spaces before each inner block
	  }
	}

 * data structure
 * 	char[] to save {} combinations
 * 
 * de-couple a bigger problem into smaller ones:
 * 	1. all permutations of ()
 * 	2. indentations
 * 		a pair of parentheses cancel them off
 * 		if { + spaces
 * 		} - spaces
 * 
 * */
public class IfBlocks {

	public static void ifBlockPermutations(int n) {
		char[] permutations = new char[2 * n];
		int left = n;
		int right = n;
		int level = 0;
		ifBlockPermutations(permutations, left, right, level);
	}
	
	public static void ifBlockPermutations(char[] permutations, int left, int right, int level) {
		// base case
		//if (level == permutations.length) {
		if (left == 0 && right == 0) {
			printSolution(permutations);
			return;
		}
		
		if (left > 0) {
			permutations[level] = '{';
			ifBlockPermutations(permutations, left - 1, right, level + 1);
		}
		
		if (right > left) {
			permutations[level] = '}';
			ifBlockPermutations(permutations, left, right - 1, level + 1);
		}
	}
	
	private static void printSolution(char[] input) {
		int indents = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == '{') {
				printSpaces(indents);
				System.out.println("if {");
				indents += 2;
			} else {
				indents -= 2;
				printSpaces(indents);
				System.out.println("}");
			}
		}
		System.out.println();
	}
	
	private static void printSpaces(int n) {
		System.out.print(String.join("", Collections.nCopies(n, " ")));
	}
	
	public static void main(String[] args) {
		ifBlockPermutations(1);
		
		ifBlockPermutations(2);

		ifBlockPermutations(3);
	}
}
