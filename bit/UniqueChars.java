package bit;

public class UniqueChars {

	// assumption: ASCII charset
	public boolean uniqueCharsI(String s) {
		boolean[] seen = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			// char to int: short to long, pad with 0s, auto casting
			// get ASCII code
			int index = s.charAt(i);
			if (seen[index]) {
				return false;
			} else {
				seen[index] = true;
			}
		}
		return true;
	}
	
	// assumption: a-z 26
	public boolean uniqueCharsII(String s) {
		int seen = 0;
		for (int i = 0; i < s.length(); i++) {
			int k = (int) s.charAt(i);
			if (((seen >> k) & 1) == 1) {
				return false;
			} else {
				seen = seen | (1 << i);
			}
		}
		return true;
	}	
	
	// assumption: ASCII charset
	// bit vector - int[]
	public boolean uniqueCharsIII(String s) {
		int[] seen = new int[8]; // 0 -256
		for (int i = 0; i < s.length(); i++) {
			int k = (int) s.charAt(i);
			int row = k / 32;
			int col = k % 32;
			if (((seen[row] >> col) & 1) == 1) {
				return false;
			} else {
				seen[row] = seen[row] | (1 << col);
			}
		}
		return true;
	}

}
