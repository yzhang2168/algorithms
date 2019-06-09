package string;

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
	 * 
	 * case 2. result gets longer
	 * 	1. find # matches of substring
	 * 	2. calculate result char[] length
	 * 	3. linear scan from right to left, slow and fast pointers 
	 * */
	public String replace(String input, String pattern, String replacement) {
		if (input == null || pattern == null || pattern.length() == 0 || replacement == null) {
			return input;
		}

		char[] array = input.toCharArray();
		if (pattern.length() >= replacement.length()) {
			return replaceShorter(array, pattern, replacement);
		} else {
			return replaceLonger(array, pattern, replacement);
		}
	}

	private String replaceShorter(char[] array, String pattern, String replacement) {
		// result: [0, s - 1]
		int s = 0;
		int f = 0;
		while (f < array.length) {
			if ((f <= array.length - pattern.length()) && isMatch(array, pattern, f)) {
				replace(array, replacement, s);
				s += replacement.length();
				f += pattern.length();
			} else {
				array[s++] = array[f++];
			}
		}
		return new String(array, 0, s);
	}

	private String replaceLonger(char[] array, String pattern, String replacement) {
		List<Integer> matches = findAllMatches(array, pattern);

		// result: [s + 1, end]
		char[] result = new char[array.length + matches.size() * (replacement.length() - pattern.length())];
		int lastIndex = matches.size() -1;
		int s = result.length - 1;
		int f = array.length - 1;

		while (f >= 0) {
			// check out of bound
			if (lastIndex >= 0 && f == matches.get(lastIndex)) {
				replace(result, replacement, s - replacement.length() + 1);
				s -= replacement.length();
				f -= pattern.length();
				lastIndex--;
			} else {
				result[s--] = array[f--];
			}
		}

		return new String(result);
	}

	private boolean isMatch(char[] array, String pattern, int startIndex) {
		if (startIndex > array.length - pattern.length()) {
			return false;
		}
		
		for (int i = 0; i < pattern.length(); i++) {
			// out of bound
			if (array[startIndex + i] != pattern.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private List<Integer> findAllMatches(char[] array, String pattern) {
		List<Integer> matches = new ArrayList<Integer>();
		int i = 0; 
		while (i <= array.length - pattern.length()) {
			if (isMatch(array, pattern, i)) {
				matches.add(i + pattern.length() - 1);
				i += pattern.length();
			} else {
				i++;
			}
		}
		return matches;
	}

	private void replace(char[] array, String replacement, int startIndex) {
		for (int i = 0; i < replacement.length(); i++) {
			array[startIndex + i] = replacement.charAt(i);
		}
	}

	public static void main(String[] args) {
		ReplaceSubstring t = new ReplaceSubstring();
		System.out.println(t.replace("studentden", "", "X"));
		System.out.println(t.replace("studentden", "den", ""));
		System.out.println(t.replace("studentden", "den", "X"));
		System.out.println(t.replace("studentden", "den", "XXXX"));
		System.out.println(t.replace(" a  b c ", " ", "20%"));
		System.out.println(t.replace("aaa", "aa", ""));
		System.out.println(t.replace("aaa", "aa", "bbb")); // bbba
		System.out.println();
	}
}
