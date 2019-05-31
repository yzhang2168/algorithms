package char_int;

public class IntToString {

	public String intValue(int value) {
		StringBuilder sb = new StringBuilder();
		while (value != 0) {
			sb.insert(0, digitToChar(value % 10));
			value = value / 10;
		}
		
		return sb.toString();
	}
	
	public char digitToChar(int digit) {
		if (digit >= 10 || digit < 0) {
			throw new IllegalArgumentException(); 
		}
		// need explicit casting because it's from int 32 bits to char 16 bits
		return (char) ('0' + digit);
	}
	
	public static void main(String[] args) {
		IntToString test = new IntToString();
		int i = 1912;
		String s = test.intValue(i);
		System.out.println(s.length() + ": " + s);
	}
}
