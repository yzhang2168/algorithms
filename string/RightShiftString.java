package algorithms.string;

public class RightShiftString {
	/**
	 * right shift by 2
	 * abcdef -> efabcd
	 * step 1. reverse(0,n-1)
	 * step 2. reverse(0,1)
	 * step 3. reverse(2,n-1)
	 * */
	public static String rightShift(String input, int shift) {
		if (input == null || shift <= 0) {
			return input;
		}
		
		char[] array = input.toCharArray();
		shift = shift % input.length();
		ReverseWordsInSentence.reverseString(array, 0, array.length - 1);
		ReverseWordsInSentence.reverseString(array, 0, shift - 1);
		ReverseWordsInSentence.reverseString(array, shift, array.length - 1);
		
		return new String(array);
	}
	
	public static void main(String[] args) {
		System.out.println(rightShift("abcdef", 0));
		System.out.println(rightShift("abcdef", 1));
		System.out.println(rightShift("abcdef", 2));
		System.out.println(rightShift("abcdef", 5));
		System.out.println(rightShift("abcdef", 6));
	}
	
}
