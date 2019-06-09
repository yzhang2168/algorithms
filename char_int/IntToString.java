package char_int;

public class IntToString {

	public String valueOf(int value) {
		StringBuilder sb = new StringBuilder();
		while (value != 0) {
			//sb.insert(0, digitToChar(value % 10));
			sb.insert(0, value % 10); // takes int, long, etc
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
		String s = test.valueOf(i);
		System.out.println(s.length() + ": " + s);
	}
}
