package char_int;


public class StringToInt {
	
	/**
	 * SPC::=' '
	 * NUM::='0'|'1'|...|'9'|
	 * INTEGER::=(SPC*)['+'|'-'](NUM+)(SPC*)
	 * 
	 * overflow
	 * */
	public int atoi(String s) {
		if (s == null) {
			return 0;
		}
		
		s.trim();
		int i = 0;
		boolean seenSign = false;
		boolean positive = true;
		long result = 0;
		while (i < s.length()) {
			char c = s.charAt(i);
			if (c == '+' || c == '-') {
				if (seenSign) {
					throw new IllegalArgumentException();
				}
				seenSign = true;
				positive = c == '+' ? true : false; 
			} else if (c >= '0' && c <= '9') {
				result = result * 10 + c - '0';
				// to deal with MIN overflow - 1 more than MAX
				if (result > (long) Integer.MAX_VALUE + 1) {
					break;
				}
			} else {
				throw new IllegalArgumentException();
			}
			
			i++;
		}
		
		result = positive ? result : -result;
		if (result > (long) Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (result < (long) Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else {
			return (int) result;
		}
	}
	
	public int intValue(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		
		int result = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				throw new IllegalArgumentException();
			}
			result = result * 10 + (s.charAt(i) - '0');
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		StringToInt test = new StringToInt();
		String s = "";
		System.out.println(test.atoi(s));
	}
}
