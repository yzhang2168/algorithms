package string;

import java.util.HashSet;

public class RemoveChars {
	/**
	 * Remove given characters in input string, the relative order of other characters should be remained. 
	 * Return the new string after deletion.
	 * Assumptions
	 * The given input string is not null.
	 * The characters to be removed is given by another string, it is guaranteed to be not null.
	 * Examples
	 * input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
	 * */
	public static String removeChars(String input, String toRemove) {
		if (input == null || input.length() == 0 || toRemove == null || toRemove.length() == 0) {
			return input;
		}
		
		char[] inputArray = input.toCharArray();
		HashSet<Character> toRemoveSet = stringToHashSet(toRemove);
		/**
		 * [0...s) to keep
		 * [f...end] searching space 
		 * init: s = 0; f = 0;
		 * for each step
		 * 	case 1: not keep inputArray[f], f++;
		 * 	case 2: keep inputArray[f], inputArray[s] = inputArray[f]; s++; f++;
		 * termination: f > n - 1;
		 * */
		int slow = 0;
		/*
		int fast = 0;
		while (fast < inputArray.length) {
			if (toRemoveSet.contains(inputArray[fast])) {
				fast++;
			} else {
				inputArray[slow] = inputArray[fast];
				slow++;
				fast++;
			}
		}
		*/
		for (int fast = 0; fast < inputArray.length; fast++) {
			if (!toRemoveSet.contains(inputArray[fast])) {
				inputArray[slow++] = inputArray[fast];
			}
		}
		
		return new String(inputArray, 0, slow); // [0...s) is result
	}
	
	private static HashSet<Character> stringToHashSet(String input) {
		HashSet<Character> result = new HashSet<Character>();
		for (int i = 0; i < input.length(); i++) {
			result.add(input.charAt(i));
		}
		return result;
	}
	
	public static void main(String[] args) {
		String s1 = "dcabbaceg";
		String s2 = "ab";
		String s3 = "";
		System.out.println(removeChars(s1, s2));
		System.out.println(removeChars(s1, s3));
	}
}
