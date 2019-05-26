package string;

public class ReverseWordsInSentence {
	/**  I   love yahoo    -> yahoo love I
	 * remove additional spaces
	 * */
	public static String reverseWords(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		
		char[] array = input.toCharArray();
		int rightBound = cleanSpaces(array);
		if (rightBound == -1) {
			return input;
		}
		
		reverseString(array, 0, rightBound);
		
		// start: 1st letter
		// end: 1st space
		int start = 0;
		for (int i = 0; i <= rightBound; i++) {
			if (array[i] == ' ') {
				reverseString(array, start, i - 1);
				start = i + 1;
			} else if (i == rightBound) {
				reverseString(array, start, i);
			}
		}
		return new String(array, 0, rightBound + 1);
	}
	
	public static void reverseString(char[] input, int start, int end) {
		if (input == null || input.length <= 1 || start < 0 || start > input.length - 1 || end < 0 || end > input.length - 1 ||start >= end) {
			return;
		}
		for (int i = start, j = end; i < j; i++, j--) {
			ReverseString.swap(input, i, j);
		}
	}
	
	// return right index of cleaned char[]
	public static int cleanSpaces(char[] input) {
		if (input == null || input.length == 0) {
			return -1;
		}
		
		// [0...slow - 1]: to keep
		// [fast...]: to explore
		int slow = 0;
		for (int fast = 0; fast < input.length; fast++) {
			if (input[fast] == ' ' && (slow == 0 || input[slow - 1] == ' ')) {
				continue;
			} else {
				input[slow] = input[fast];
				slow++;
			}
		}
		
		// post processing for trailing space
		if (slow > 0 && input[slow - 1] == ' ') {
			slow--;
		}
		return slow - 1;
	}
	
	public static void main(String[] args) {
		String s = "Ime love yahoo";
		System.out.println(reverseWords(s));
		System.out.println(reverseWords("  Ime    love yahoo  "));
		System.out.println(reverseWords("  "));
	}
}
