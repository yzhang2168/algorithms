package string;

import java.util.Arrays;

public class DedupAdjacentI {

	/**
	 * abbbaaazw -> abazw
	 * keep one copy for each repeated letter
	 */
	public static String dedupAdjacentLetters(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}

		char[] array = input.toCharArray();
		// [0...slow-1]: result
		int slow = 1;
		for (int fast = 1; fast < array.length; fast++) {
			if (array[fast] != array[slow - 1]) {
				array[slow++] = array[fast];
			}
		}
		return new String(array, 0, slow); // [0, slow -1]
	}

	public static String dedupAdjacentLettersII(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}

		char[] array = input.toCharArray();
		// [0...slow]: result
		int slow = 0;
		for (int fast = 1; fast < array.length; fast++) {
			if (array[fast] != array[slow]) {
				array[++slow] = array[fast];
			}
		}
		return new String(array, 0, slow + 1); // [0, slow]
	}

	public static void main(String[] args) {
		System.out.println(dedupAdjacentLetters("aaabbazwww"));
	}
}
