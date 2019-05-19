package algorithms.string;

/**
 * given a string in compressed form, decompress it to the original string
 * a1c0c2 -> acc
 * assumptions:
 * 1. string is not null
 * 2. charsets a-z
 * 3. no adjacent repeated chars with length > 9
 * */
public class DecompressString {
	// in-place: the input is long enough and original string only occupies part of it
	public static String decompressI(String input) {
		if (input.isEmpty()) {
			return input;
		}
		
		char[] array = input.toCharArray();
		// case 1. a0, a1, a2, the decoded string is shorter
		// case 2. a3, a4..., the decoded string is longer
		return decodeLonger(array, decodeShorter(array));
	}

	// the decoded string is shorter, decode from left to right
	private static int decodeShorter(char[] input) {
		int end = 0;
		for (int i = 0; i < input.length; i += 2) {
			int digit = getDigit(input[i + 1]);
			if (digit >= 0 && digit <= 2) {
				for (int j = 0; j < digit; j++) {
					input[end++] = input[i];
				}
			} else {
				// copy a3, a4... and handle it elsewhere
				input[end++] = input[i];
				input[end++] = input[i + 1];
			}
		}
		return end;
	}
	
	// the decoded string is longer, decode from right to left
	// length: the length of valid partition starting from index 0
	// [a, 3, b, b, d, d, e, f, 3,|| 1, f, 3] length = 9
	private static String decodeLonger(char[] input, int length) {
		// calculate the new required length
		int newLength = length;
		for (int i = 0; i < length; i++) {
			int digit = getDigit(input[i]);
			if (digit > 2 && digit <= 9) {
				newLength += digit - 2;
			}
		}
		
		// note: if it is required to do this in-place, 
		// usually the input array is sufficiently long
		char[] result = new char[newLength];
		int end = newLength - 1;
		for (int i = length - 1; i >= 0; i--) {
			int digit = getDigit(input[i]);
			if (digit > 2 && digit <= 9) {
				i--;
				for (int j = 0; j < digit; j++) {
					result[end--] = input[i];
				}
			} else {
				// we already handled a0, a1, a2 cases
				// copy as is
				result[end--] = input[i];
			}
		}
		return new String(result);
	}
	
	private static int getDigit(char ch) {
		return ch - '0';
	}
	
	public static String decompressII(String input) {
		if (input.isEmpty()) {
			return input;
		}
		char[] array = input.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			char ch = array[i++];
			int count = array[i] - '0';
			for (int c = 0; c < count; c++) {
				sb.append(ch);
			}
		}
		return sb.toString();		
	}	

}
