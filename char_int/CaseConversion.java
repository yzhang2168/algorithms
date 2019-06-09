package char_int;

public class CaseConversion {

	public char lowerToUpper(char c) {
		// explicit casting is needed because int 32 to char 16 - ignore 1st half
		return (char) (c - 'a' + 'A');
	}
}
