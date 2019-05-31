package DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Permutations {
	/**
	 * assumption: no duplicates
	 * input.length levels: 
	 * each level corresponds to the index of the curr position 
	 * at each level - index, swap (index...end] to index position, recursion call, then swap back 
	 * 																    time				space
	 * 									(abc)							n (for())			n
	 * 						/		 	 |				\		
	 *					a(bc)			b(ac)			c(ab)			n(n-1)				n-1
	 * 					/	\			/	\			/	\	
	 * 				ab(c)	ac(b)	ba(c)	bc(a)	ca(b)	cb(a)		n(n-1)(n-2)			n-2
	 * 				  |		  |		  |		  |		  |		  |
	 * 				abc		acb		bac		bca		cab		cba
	 * time: look up the recursion tree, add up time for all nodes. 
	 * 		n!: # leaf nodes
	 * 		complete binary tree: # leaf nodes = # all internal nodes
	 * 		branching > 2: # leaf nodes > # all internal nodes
	 * 		use # leaf nodes for O
	 * 		n * n!: n for base case new String(input) * n! leaf nodes
	 * space: deepest path n levels, each node extra space hashset: n + n-1 + n-2 + ... + 1 = n ^ 2 
	 * */
	public static List<String> permutations(String input) {
		List<String> result = new ArrayList<String>();
		if (input == null) {
			return result;
		}
		
		char[] inputArray = input.toCharArray();
		permutations(inputArray, 0, result);
		//permutationsDedup(inputArray, 0, result);
		return result;
	}
	
	public static void permutations(char[] input, int index, List<String> result) {
		// base case: have chosen chars for all positions
		if (index == input.length) {
			result.add(new String(input));
			return;
		}
		// swap all possible chars to the curr position index - one of the implementation options
		// could use a boolean array to record used chars
		// candidate chars for the index position: index...array.length - 1
		for (int i = index; i < input.length; i++) {
			swap(input, index, i);
			permutations(input, index + 1, result);
			// back to the previous level, swap back
			swap(input, index, i);			
		}
	}
	
	public static List<String> permutationsDedup(String input) {
		List<String> result = new ArrayList<String>();
		if (input == null) {
			 return result;
		 }
		
		char[] inputArray = input.toCharArray();
		permutationsDedup(inputArray, 0, result);
		return result;
	 }
	
	private static void permutationsDedup(char[] input, int index, List<String> result) {
		// base case: have chosen chars for all positions
		if (index == input.length) {
			result.add(new String(input)); // <------------- convert char[] to String!!
			return;
		}
		
		// set is a local for each node
		HashSet<Character> used = new HashSet<Character>(); 
		// swap all possible chars to the curr position index
		// candidate chars for the index position: index...array.length - 1
		for (int i = index; i < input.length; i++) {
			if (used.add(input[i])) { // returns true if the set did not contain the element
			//if (!used.contains(input[i])) { // one more lookup
				//used.add(input[i]);	
				swap(input, index, i);
				permutationsDedup(input, index + 1, result);
				// back to the previous level, swap back
				swap(input, index, i);
			}
		}
	}
	
	public static void swap(char[] input, int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static void main(String[] args) {
		char[] array = {'a', 'b'};
		//printArray(array);
		System.out.println(permutations("abca"));
		System.out.println(permutationsDedup("abca"));
	}	
}
