package algorithms.string;

/**
 * aaaabcca -> a4b1c2a1
 * [0...slow) to keep
 * [fast…] to explore
 * while array[fast+1] == array[fast], fast++
 * array[slow] = array[fast-1]
 * array[slow + 1] = length (if length > 1)
 * 
 * assumption: adjacent char length < 10
 * */
public class RunLengthEncoding {

	public static String encoding(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}

		char[] array = input.toCharArray();
		int slow = 0;
		int fast = 0;
		int singletons = 0;
		int start = 0;
		
		while (fast < array.length) {	
			while (fast + 1 < array.length && array[fast + 1] == array[fast]) {
				fast++;
			}
			
			if (fast == start) {
				array[slow++] = array[fast++];
				singletons++;
			} else {
				array[slow] = array[fast];
				int length = fast - start + 1;
				slow++;
				fast++;
				
				if (length < 10) {
					array[slow] = (char) ('0' + length);
					slow++;
				}	
			}
			start = fast;
		}
		
		if (singletons == 0) {
			return new String(array, 0, slow);
		} else {
			return expandSingletons(array, slow, singletons);
		}
	}
	
	// aaaabcca -> a4bc2a -> a4b1c2a1
	// note that array has additional chars not as part of the result [a, 4, b, c, 2, a, c, a]
	// need to pass length so that we know the boundary is [0...length-1]
	private static String expandSingletons(char[] array, int length, int singletons) {
		char[] result = new char[length + singletons];
		int end = result.length - 1;
		for (int i = length - 1; i >= 0; i--) {
			if (isLetter(array[i])) {
				// singleton letter
				if (i == array.length - 1 || isLetter(array[i + 1])) {
					result[end--] = '1';
					result[end--] = array[i];
				} else {
					result[end--] = array[i];
				}
			// digit
			} else {
				result[end--] = array[i];
			}
		}	
		return new String(result);
	}

	public static boolean isLetter(char c) {
		return c < '0' || c > '9';
	}
	
	
	public static void main(String[] args) {
		System.out.println(encoding("aabbbbcc"));
		System.out.println(encoding("aaaabcca"));
	}
}
