package algorithms.string;

import java.util.ArrayList;
import java.util.List;

public class ReplaceSubstring {
	/**
	 * den -> XX
	 * student -> stuXXt
	 * 
	 * replace space with 20%
	 * 
	 * step 1. input string to char[]
	 * case 1. result gets shorter and equal length, use input char[]
	 * 	linear scan from left to right
	 * 	if a match is found, 
	 * 		copy replacement to array[slow...slow + replacement.length()], 
	 * 		slow += replacement.length(), 
	 * 		fast += substr.length()
	 * 	if no match, array[slow++] = array[fast++]
	 * case 2. result gets longer
	 * 	1. find # matches of substring
	 * 	2. calculate result char[] length
	 * 	3. linear scan from right to left, slow and fast pointers 
	 * */
	public static String replaceSubstring(String input, String toReplace, String sub) {
		if (input == null || input.length() == 0 || toReplace == null || toReplace.length() == 0 || sub == null) {
			return input;
		}
		
		char[] array = input.toCharArray();
		if (toReplace.length() >= sub.length()) {
			return replaceShorter(array, toReplace, sub);
		} else {
			return replaceLonger(array, toReplace, sub);
		}
	}
	
	private static String replaceShorter(char[] input, String toReplace, String sub) {
		int slow = 0;
		int fast = 0;
		while (fast < input.length) {
			if (fast <= input.length - toReplace.length() && findSubstring(input, fast, toReplace)) {
				copySubstring(input, slow, sub);
				slow += sub.length();
				fast += toReplace.length();
			} else {
				input[slow++] = input[fast++];
			}
		}
		return new String(input, 0, slow);
	}
	
	private static String replaceLonger(char[] input, String toReplace, String sub) {
		// this returns the end index of all matches
		List<Integer> matches = findAllMatches(input, toReplace);
		System.out.println(matches);
		
		// allocate a new longer array for result
		int length = input.length + (sub.length() - toReplace.length()) * matches.size();
		char[] result = new char[length];
		// fill result from right to left
		// result(slow...]: result to keep
		// input[0...fast]: to explore
		int slow = result.length - 1;
		int fast = input.length - 1;
		int lastMatch = matches.size() - 1;
		while (fast >= 0) {
			// fast is at the last index of a match
			if (lastMatch >= 0 && fast == matches.get(lastMatch)) {
				copySubstring(result, slow - sub.length() + 1, sub);
				lastMatch--;
				slow -= sub.length();
				fast -= toReplace.length();
			} else {
				result[slow--] = input[fast--];
			}
		}
		return new String(result);
	}
	
	// assumption substr.length() != 0
	private static List<Integer> findAllMatches(char[] text, String substr) {
		List<Integer> result = new ArrayList<Integer>();
		if (text == null || substr == null || text.length == 0 || text.length < substr.length()) {
			return result;
		}
		
		for (int i = 0; i <= text.length - substr.length(); i++) {
			int j = 0;
			for (; j < substr.length(); j++) {
				if (text[i + j] != substr.charAt(j)) {
					break;
				}
			}
			if (j == substr.length()) {
				result.add(i + substr.length() - 1);
				i = i + substr.length() - 1;
			}
		}
		return result;
	}
	
	public static boolean findSubstring(char[] text, int start, String sub) {
		if (text == null || sub == null || text.length == 0 || text.length - start < sub.length()) {
			return false;
		}
		
		if (sub.length() == 0) {
			return true;
		}
		
		for (int i = 0; i < sub.length(); i++) {
			if (text[start + i] != sub.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private static void copySubstring(char[] input, int start, String p) {
		for (int i = 0; i < p.length(); i++) {
			input[start + i] = p.charAt(i);
		}
	}
	
	/**
	 * uses Java .indexOf(substring, from Index) and StringBuilder
	 * */
	public static String replaceSubstringII(String input, String substr) {
		StringBuilder sb = new StringBuilder();
		int fromIndex = 0;
		int matchIndex = input.indexOf(substr, fromIndex);
		// if fromIndex is out of range, return -1 
		while (matchIndex != -1) {
			sb.append(input, fromIndex, matchIndex).append(substr);
			fromIndex = matchIndex + substr.length();
			matchIndex = input.indexOf(substr, fromIndex);
		}
		sb.append(input, fromIndex, input.length());
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(replaceSubstring("studentden", "", "X"));
		System.out.println(replaceSubstring("studentden", "den", ""));
		System.out.println(replaceSubstring("studentden", "den", "X"));
		System.out.println(replaceSubstring("studentden", "den", "XXXX"));
		System.out.println(replaceSubstring(" a  b c ", " ", "20%"));
		System.out.println(replaceSubstring("aaa", "aa", ""));
		System.out.println(replaceSubstring("aaa", "aa", "bbb")); // bbba
		System.out.println();

	}
}
