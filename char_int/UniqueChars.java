package char_int;

public class UniqueChars {
	// assumption: ASCII charset
	public boolean uniqueChars(String s) {
		boolean[] seen = new boolean[128];
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
	
	public boolean uniqueCharsOptimized(String s) {
		int[] seen = new int[8]; // 8 * 32 = 256
		for (int i = 0; i < s.length(); i++) {
			// char to int: short to long, pad with 0s, auto casting
			// get ASCII code
			int c = s.charAt(i);
			int row = c / 32;
			int col = 1 << (c % 32);
			if ((seen[row] & col) != 0) {
				return false;
			} else {
				seen[row] |= col;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		UniqueChars t = new UniqueChars();
		System.out.println(t.uniqueChars("ABA"));
	}
}
