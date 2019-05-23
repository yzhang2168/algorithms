package string;

import java.util.ArrayList;
import java.util.List;

public class FindSubstrings {

	public List<Integer> findSubstrings(String text, String pattern) {
		List<Integer> result = new ArrayList<Integer>();
		if (text == null || text.length() == 0 || pattern == null || pattern.length() == 0 || text.length() < pattern.length()) {
			return result;
		}
		
		// verify indices: text and pattern are both 1 char, need to enter for() once
		for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
			if (match(text, pattern, i)) {
				result.add(i);
			}
		}
		return result;
	}
	
	private boolean match(String text, String pattern, int start) {
		for (int i = 0; i < pattern.length(); i++) {
			if (text.charAt(start + i) != pattern.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isSubstringNaive(String text, String pattern) {
		if (text == null || pattern == null || text.length() < pattern.length()) {
			return false;
		}
		
		if (pattern.length() == 0) {
			return true;
		}
		
		boolean found = false;
		for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
			int j = 0;
			for (; j < pattern.length(); j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if (j == pattern.length()) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	// returns start index of substring in text
	public static int substringNaive(String text, String pattern) {
		if (text == null || pattern == null || text.length() < pattern.length()) {
			return -1;
		}
		
		if (pattern.length() == 0) {
			return 0;
		}
		
		if (text.length() == 0) {
			return -1;
		}
		
		for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
			int j = 0;
			for (; j < pattern.length(); j++) {
				if (text.charAt(i + j) != pattern.charAt(j)) {
					break;
				}
			}
			if (j == pattern.length()) {
				return i;
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		System.out.println(isSubstringNaive(null, null));
		System.out.println(isSubstringNaive("", ""));
		System.out.println(isSubstringNaive("abbcd", "bcd"));
		System.out.println(isSubstringNaive("abbcd", "bcde"));
		System.out.println();
		
		System.out.println(substringNaive(null, null));
		System.out.println(substringNaive("", ""));
		System.out.println(substringNaive("abbcd", "bcd"));
		System.out.println(substringNaive("abbcd", "bcde"));
	}
}
