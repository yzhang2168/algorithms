package string;

public class DedupAdjacent {

	/**
	 * abbbaaazw -> abazw
	 * */
	public static String dedupAdjacentLetters(String input) {
		if (input == null || input.length() == 0) {
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
	
	/**
	 * abbbazw -> zw
	 * */
	public static String dedupAdjacentLettersRepeatedly(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		char[] array = input.toCharArray();
		// [0...end]: stack		
		int end = 0; // now stack has 1 element
		for (int fast = 1; fast < array.length; fast++) {
			// end == -1: stack is empty
			if (end == -1 || array[fast] != array[end]) {
				array[++end] = array[fast];
			} else { // array[fast] == array[slow] / stack top
				while (fast < array.length - 1 && array[fast + 1] == array[fast]) {
					fast++;
				}
				// fast == array.length - 1 || array[fast + 1] != array[fast]
				// array[fast] is the last == array[slow]
				end--;
			}
		}
		return new String(array, 0, end + 1); // [0, slow -1]
	}

	public static void main(String[] args) {
		System.out.println(5 ^ 6); // 3
		System.out.println(dedupAdjacentLetters("aaabbazwww"));
		System.out.println(dedupAdjacentLettersRepeatedly("abbbazw"));
	}
}
