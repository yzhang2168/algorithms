package string;

public class ReverseString {
	/**
	 * in-place reverse "apple" -> "elppa"
	 * */
	public static String reverse(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		
		char[] array = input.toCharArray();
		int i = 0;
		int j = array.length - 1;
		while (i < j) {
			swap(array, i, j);
			i++;
			j--;
		}
		return new String(array);
	}
	
	public static String reverseRecursion(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		
		char[] array = input.toCharArray();
		helper(array, 0, array.length - 1);
		return new String(array);
	}
	
	private static void helper(char[] input, int left, int right) {
		if (left >= right) {
			return;
		}
		swap(input, left, right);
		helper(input, left + 1, right - 1);
	}
	
	public static void swap(char[] input, int i, int j) {
		if (input == null || input.length == 0 || i < 0 || i >= input.length || j < 0 || j >= input.length) {
			return;
		}
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static void main(String[] args) {
		System.out.println(reverse(null));
		System.out.println(reverseRecursion(null));
		System.out.println(reverse(""));
		System.out.println(reverseRecursion(""));
		System.out.println(reverse("apple"));
		System.out.println(reverseRecursion("apple"));
	}
}
