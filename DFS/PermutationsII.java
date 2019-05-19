package algorithms.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PermutationsII {

	/**
	 * given two copies of all integers from 1...n
	 * input[2n] = {1, 1, 2, 2, 3, 3,..., n, n}
	 * determine if it is possible to shuffle the numbers 
	 * such that for each i, there are exactly i numbers between the two i's 
	 * 
	 * solution 0: find all permutations of {1, 1, 2, 2, 3, 3,..., n, n}
	 * 				at each base case, check if it satisfies the requirement 
	 * 				time: O((2n)! * n)
	 * */
	public static List<String> permutations0(char[] input) {
		List<String> result = new ArrayList<String>();
		if (input == null) {
			return result;
		}

		permutations0(input, 0, result);
		return result;
	}

	private static void permutations0(char[] input, int index, List<String> result) {
		// base case
		if (index == input.length - 1) {
			if (correctSpacing(input)) {
				result.add(new String(input));
				return;
			}
		}

		// recursion: swap and swap
		HashSet<Character> used = new HashSet<Character>(); 
		for (int i = index; i < input.length; i++) {
			if (used.add(input[i])) {
				swap(input, index, i);
				permutations0(input, index + 1, result);
				swap(input, index, i);
			}
		}
	}


	/**
	 * check if comb meets requirement as soon as possible
	 * # levels: 2n
	 * each level corresponds to one position in the solution
	 * # branches/level: n options for one position
	 * case 1: if a number has never been used, just use it as a new branch, and insert it into HashMap
	 * case 2: if a number has been used once before, we must check if putting it here meets requirement
	 * hashmap <num, count>: check in array/solutionPrefix, if curr position - level -  1 is the curr number
	 * hashmap <num, level>: check if the interval between this level and its previous level is i
	 * 
	 * 								  empty
	 * 							/		|		\
	 * level 0:			1				2				3
	 * 				/	|	\		/	|	\		/	|	\	
	 * level 1:	   1	2	 3	   1	2	 3	   1	2	 3
	 * 			  /|\  /|\  /|\   /|\  /|\  /|\   /|\  /|\  /|\
	 * level 2:  1 2 3 
	 * 			 input[level - i - 1] should be i
	 * ...
	 * level 2n-1
	 * */
	public static List<String> permutations1(int n) {
		List<String> result = new ArrayList<String>();
		if (n <= 1) {
			return result;
		}

		// a global map
		HashMap<Integer, Integer> usedNumbers = new HashMap<Integer, Integer>(); 		
		StringBuilder solutionPrefix = new StringBuilder();
		permutations(n, 0, usedNumbers, solutionPrefix, result);
		return result;
	}

	private static void permutations(int n, int level, HashMap<Integer, Integer> usedNumbers, StringBuilder solutionPrefix, List<String> result) {
		// base case
		if (level == 2 * n) {
			result.add(solutionPrefix.toString());
			return;
		}

		// recursion
		for (int i = 1; i <= n; i++) {
			int count = usedNumbers.getOrDefault(i, 0);

			if (count == 0) {
				solutionPrefix.append(i);
				usedNumbers.put(i, count + 1);
				permutations(n, level + 1, usedNumbers, solutionPrefix, result);

				solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
				usedNumbers.put(i, count);

			} else if (count == 1) {
				int prev = level - i - 1;
				if (prev >= 0 && ((int) solutionPrefix.charAt(prev) == i + '0')) { // StringBuilder.charAt() returns a char
					solutionPrefix.append(i);
					usedNumbers.put(i, count + 1);

					permutations(n, level + 1, usedNumbers, solutionPrefix, result);

					solutionPrefix.deleteCharAt(solutionPrefix.length() - 1);
					usedNumbers.put(i, count);
				}
			}
		}
	}


	/**
	 * check if comb meets requirement as soon as possible
	 * # levels: 2n
	 * each level corresponds to one position in the solution
	 * # branches/level: n options for one position
	 * case 1: if a number has never been used, just use it as a new branch, and insert it into HashMap
	 * case 2: if a number has been used once before, we must check if putting it here meets requirement
	 * hashmap <num, count>: check in array, if curr position - level -  1 is the curr number
	 * hashmap <num, level>: check if the interval between this level and its previous level is i
	 * */
	public static List<String> permutations2(int n) {
		List<String> result = new ArrayList<String>();
		if (n <= 1) {
			return result;
		}

		//input array {'1', '1', '2', '2',..., 'n', 'n'}
		char[] array = new char[n * 2];
		for (int i = 0; i < 2 * n; i++) {
			array[i] = (char) (i / 2 + 1 + '0');
		}
		//util.Util.printArray(array);

		// a global map
		HashMap<Character, Integer> usedNumbers = new HashMap<Character, Integer>(); 		

		permutations2(n, array, 0, usedNumbers, result);
		return result;
	}

	private static void permutations2(int n, char[] input, int level, HashMap<Character, Integer> usedNumbers, List<String> result) {
		// base case
		if (level == 2 * n ) {
			result.add(new String(input));
			return;
		}

		// recursion
		// set is a local for each node
		HashSet<Character> set = new HashSet<Character>();
		for (int i = 1; i <= n; i++) {
			if (set.add(input[i])) {
				char currChar = (char) (i + '0'); 
				// map is global for the method
				int count = usedNumbers.getOrDefault(currChar, 0);

				// if i is never used before
				if (count == 0) {
					usedNumbers.put(currChar, count + 1);
					swap(input, level, i);

					permutations2(n, input, level + 1, usedNumbers, result);

					usedNumbers.put(currChar, count);
					swap(input, level, i);

					// if i has been used once before
				} else if (count == 1) {
					int prev = level - i - 1;
					if (prev >= 0 && input[prev] == currChar) {
						swap(input, level, i);
						usedNumbers.put(currChar, count + 1);

						permutations2(n, input, level + 1, usedNumbers, result);

						swap(input, level, i);
						usedNumbers.put(currChar, count);
					}
				}
			}
		}

	}


	/**
	 * place digits into valid positions
	 * # levels: n
	 * each level places one pair of integers into valid positions
	 * # branches/level: # valid positions left
	 * n = 3
	 * 										xxxxxx
	 * 									/			\
	 * level 1: locations of 3s		3xxx3x			x3xxx3
	 * 								  |				   |
	 * level 2: locations of 2s		3x2x32			23x2x3
	 * 								  |				   |
	 * level 1: locations of 1s		312132			231213
	 * */
	
	
	
	/**
	 * assumption: input array only contains 2 copies of 1...n
	 * at each i
	 * 	count == 1: if next in array range, check if curr == next, else false
	 *  count == 2: 
	 *  count >= 3: false
	 * */
	private static boolean correctSpacing(char[] input) {
		if (input == null || input.length == 0) {
			return true;
		}

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < input.length; i++) {
			Integer count = map.get(input[i]);
			if (count == null) {
				count = 1;
			} else if (count >= 2) { // input validation
				return false;
			} else {
				count++;
			}			
			map.put(input[i], count);

			int next = i + input[i] - '0' + 1;

			if (count <= 1 && next >= input.length){
				return false;
			} else if (count <= 1 && next <= input.length - 1) {
				if (input[next] != input[i]) {
					return false;
				}
			}
		}	
		return true;

	}

	public static void swap(char[] input, int a, int b) {
		char temp = input[a]; 
		input[a] = input[b];
		input[b] = temp;
	}

	
	public static void main(String[] args) {
		System.out.println(correctSpacing("312132".toCharArray())); // true
		System.out.println();

		System.out.println(permutations0(new char[] {'1', '1', '2', '2', '3', '3'}));
		//System.out.println(permutations0(new char[] {'1', '1', '2', '2', '3', '3', '4', '4', '5', '5', '6', '6'}));
		System.out.println();

		StringBuilder sb = new StringBuilder();
		sb.append(1);
		sb.append(2);
		System.out.println(sb); // String 12
		System.out.println(sb.charAt(0) == '1'); // returns a char
		System.out.println(sb.charAt(0) == 1 + '0'); // compares ASCII code
		System.out.println(permutations1(3));
		System.out.println();

		System.out.println(permutations2(3));
	}	
}
