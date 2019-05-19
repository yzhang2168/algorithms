package algorithms.recursion;


/**
 * internationalization = i18n
 * AndreessenHoriwitz = a16z.com
 * student = s2d2t
 * student != s3d1t
 * text: no digits
 * pattern: no 0
 * */

public class StringPattern {

	// O(n)
	// tail recursion
	public static boolean matchesPatternRecursive(String text, int textStart, String pattern, int patternStart) {
		// only when we run out of text and pattern at the same time, match
		if (textStart == text.length() && patternStart == pattern.length()) {
			return true;
		// run out of one of them, mismatch
		// >= index out of bound
		} else if (textStart >= text.length() || patternStart >= pattern.length()) {
			return false;
		}
		
		// case 1. pattern[start] is a char
		if (pattern.charAt(patternStart) < '0' || pattern.charAt(patternStart) > '9') {
			if (pattern.charAt(patternStart) != text.charAt(textStart)) {
				return false;
			} else {
				return matchesPatternRecursive(text, textStart + 1, pattern, patternStart + 1);
			}			
		// case 2. pattern[start] is a digit
		// from left to right, scan for all consecutive digits and calculate string-> int
		} else { 
			int count = 0;
			while (patternStart < pattern.length() && pattern.charAt(patternStart) >= '0' && pattern.charAt(patternStart) <= '9') {
				count = count * 10 + (pattern.charAt(patternStart) - '0');
				patternStart++;
			}
			
			return matchesPatternRecursive(text, textStart + count, pattern, patternStart); // out of bound covered by base case
			/*
			if (count > (text.length() - textStart)) {
				return false;
			} else {
				return matchesPatternRecursive(text, textStart + count, pattern, patternStart);
			}
			*/
		}		
	}
	
	//O(n ^ 2) because substring() is O(n)
	public static boolean matchesPatternRecursive0(String text, String pattern) {
		if (text.length() == 0 && pattern.length() == 0) {
			return true;
		} else if (text.length() == 0 || pattern.length() == 0) {
			return false;
		}
		
		if (Character.isLetter(pattern.charAt(0))) {
			if (pattern.charAt(0) != text.charAt(0)) {
				return false;
			} else {
				return matchesPatternRecursive0(text.substring(1), pattern.substring(1));
			}
		} else {
			int i = 0;
			int num = 0;
			while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
				num = num * 10 + (pattern.charAt(i) - '0');
				i++;
			}
			
			if (num > text.length()) {
				return false;
			} else {
				return matchesPatternRecursive0(text.substring(num), pattern.substring(i));
			}
		}
	}
	
	// assumptions: text, pattern not null
	// convert tail recursion to iterative 
	public static boolean matchesPatternInterative(String text, String pattern) {
		int textStart = 0;
		int patternStart = 0;
		
		while (textStart < text.length() && patternStart < pattern.length()) {
			if (pattern.charAt(patternStart) < '0' || pattern.charAt(patternStart) > '9') {
				if (pattern.charAt(patternStart) != text.charAt(textStart)) {
					return false;
				}
				textStart++;
				patternStart++;
			} else { // digit
				int count = 0;
				while (patternStart < pattern.length() && pattern.charAt(patternStart) >= '0' && pattern.charAt(patternStart) <= '9') {
					count = count * 10 + (pattern.charAt(patternStart) - '0');
					patternStart++;
				}
				textStart += count;
			}
		}
		
		// out of bound: last index + 1 -- exhausted, or > last index + 1, mismatch
		return textStart == text.length() && patternStart == pattern.length();
	}
	
	
	public static void main(String[] args) {
		System.out.println(matchesPatternRecursive("internationalization", 0, "i18n", 0));
		System.out.println(matchesPatternRecursive("AndreessenHoriwitz", 0, "A16z", 0));
		System.out.println(matchesPatternRecursive("student", 0, "s2d2t", 0));
		System.out.println(matchesPatternRecursive("student", 0, "s3d1t", 0));
		System.out.println();
		
		System.out.println(matchesPatternRecursive0("internationalization", "i18n"));
		System.out.println(matchesPatternRecursive0("AndreessenHoriwitz", "A16z"));
		System.out.println(matchesPatternRecursive0("student", "s2d2t"));
		System.out.println(matchesPatternRecursive0("student", "s3d1t"));
		System.out.println();
		
		System.out.println(matchesPatternInterative("internationalization", "i18n"));
		System.out.println(matchesPatternInterative("AndreessenHoriwitz", "A16z"));
		System.out.println(matchesPatternInterative("student", "s2d2t"));
		System.out.println(matchesPatternInterative("student", "s3d1t"));

	}
}
