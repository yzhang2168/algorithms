package char_int;

public class StringToNumeric {

	/**
	 * (SPC*)
	 * (NUM*)[DOT(NUM*)]
	 * (SPC*)
	 * 
	 * "." => true
	 * ""  => true
	 * */
	public boolean isNumeric(String s) {
		if (s == null) {
			return false;
		} 
		
		s.trim();
		boolean seenNumber = false;
		boolean seenDot = false;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '.') {
				if (seenDot) {
					return false;
				}
				seenDot = true;
			} else if (c < '0' || c > '9') {
				return false;
			} else {
				seenNumber = true;
			}
		}
		return seenNumber;
	}
	
	/**
	 * (SPC*)
	 * [+|-](NUM+)[DOT(NUM*)]
	 * [(E|e)([+|-](NUM+))]
	 * (SPC*) 
	 * */
	public boolean isScientificNumeric(String s) {
		if (s == null) {
			return false;
		} 
		
		s.trim();
		boolean seenDot = false;
		boolean seenE = false;
		boolean seenNumber = false;
		boolean seenNumberAfterE = false;
		boolean seenSignBeforeE = false;
		boolean seenSignAfterE = false;
				
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+' || c == '-') {
				// should be 1st char before E
				if (!seenE) {
					if (seenNumber || seenDot || seenSignBeforeE) {
						return false;
					} else {
						seenSignBeforeE = true;
					}						
				} else if (seenE) {
					if (seenSignAfterE || seenNumberAfterE) {
						return false;
					} else {
						seenSignAfterE = true;
					}
				}
			} else if (c >= '0' && c <= '9') {
				seenNumber = true;
				if (seenE) {
					seenNumberAfterE = true;
				}
			} else if (c == '.') {
				if (seenDot || seenE) {
					return false;
				} else {
					seenDot = true;					
				}
			} else if (c == 'E' || c == 'e') {
				if (seenE || !seenNumber) {
					return false;
				} else {
					seenE = true;
				}
			} else {
				return false;
			}
		}
		
		if (seenE) {
			return seenNumberAfterE;
		} else {
			return seenNumber;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(" a ".trim());
	}
}
