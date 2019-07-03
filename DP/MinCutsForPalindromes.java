package DP;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning 
 * if every substring of the partition is a palindrome. 
 * Determine the fewest cuts needed for a palindrome partitioning of a given string.
 * Assumptions
 * The given string is not null
 * Examples
 * “a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
 * The minimum number of cuts needed is 3.
 * 
 * */
public class MinCutsForPalindromes {

	public static int MinCutsPalindromes(String input) {
		if (input == null || input.length() <= 1) {
			return 0;
		}
		
		int n = input.length();
		int[] minCuts = new int[n];
		// minCuts[i]: mincuts for input.substring[0...i], maxCuts = i for length i + 1
		// base case: substring[0...0], 0 cuts
		minCuts[0] = 0;
		
		for (int i = 1; i < n; i++) {
			minCuts[i] = i;
			
			// case 1: [0...i] is palindrome
			if (isPalindrome(input, 0, i + 1)) {
				minCuts[i] = 0;
			
			// case 2: check all possible splits [0...j] || [j + 1...i], find min of all possible splits
			} else {
				for (int j = 0; j < i; j++) {
					if (isPalindrome(input, j + 1, i)) {
						minCuts[i] = Math.min(minCuts[i], minCuts[j] + 1);
					}
				}
			}
		}
		
		return minCuts[n - 1];
	}
	
	
	public static boolean isPalindrome(String input, int start, int end) {
		if (input == null || input.length() <= 1) {
			return true;
		}
		
		while (start < end) {
			if (input.charAt(start) != input.charAt(end)) {
				return false;
			} else {
				start++;
				end--;
			}
		}
		return true;
	}

	
	public static void main(String[] args) {
		System.out.println(MinCutsPalindromes("a"));
		System.out.println(MinCutsPalindromes("babbab"));
		System.out.println(MinCutsPalindromes("babbbab"));
		System.out.println(MinCutsPalindromes("ababbbabbababa"));
		System.out.println(MinCutsPalindromes("aaaaaabbabb"));
	}
}
