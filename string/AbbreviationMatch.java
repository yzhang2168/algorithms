package string;

public class AbbreviationMatch {
	/**
	 * Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

	Assumptions:

	The original string only contains alphabetic characters.
	Both input and pattern are not null.
	Pattern would not contain invalid information like "a0a","0".
	Examples:

	pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
	 * */
	public boolean match(String input, String pattern) {
		return match(input, 0, pattern, 0);
	}

	private boolean match(String text, int ti, String pattern, int pi) {
		// base case
		if (ti == text.length() && pi == pattern.length()) {
			return true;
		} else if (ti >= text.length() || pi >= pattern.length()) {
			return false;
		} 

		if (isLetter(pattern.charAt(pi))) {
			if (text.charAt(ti) != pattern.charAt(pi)) {
				return false;
			} else {
				return match(text, ti + 1, pattern, pi + 1);
			}
		} else { // digit
			int count = 0;
			while (pi < pattern.length() && isDigit(pattern.charAt(pi))) {
				count = 10 * count + pattern.charAt(pi) - '0';
				pi++;
			}
			// out of bound is handled by base case
			return match(text, ti + count, pattern, pi);			
		}
	}

	public boolean matchIterative(String text, String pattern) {
		int ti = 0;
		int pi = 0;
		while (pi < pattern.length() && ti < text.length()) {
			char c = pattern.charAt(pi);
			if (isLetter(c)) {
				if (c != text.charAt(ti)) {
					return false;
				} else {
					pi++;
					ti++;
				}
			} else {
				int count = 0;
				while (pi < pattern.length() && isDigit(pattern.charAt(pi))) {
					count = 10 * count + (pattern.charAt(pi) - '0');
					pi++;
				}
				ti += count; // if out of bound, will exit loop
			}
		}

		return ti == text.length() && pi == pattern.length();
	}

	private boolean isLetter(char c) {
		return c >= 'a' && c <= 'z';
	}

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}
}
