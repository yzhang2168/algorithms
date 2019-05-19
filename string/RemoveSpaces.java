package algorithms.string;

public class RemoveSpaces {

	/**
	 * [0...s): to keep
	 * [f...n-1]: searching space
	 * 
	 * case 1. keep array[fast], array[slow++] = array[fast++]
	 * case 2. not keep array[fast], fast++
	 * 
	 * array[fast] != ' ', keep
	 * array[fast] = ' ' && slow != 0 && array[slow - 1] != ' ', keep
	 * array[fast] = ' ' && slow = 0, leading space, not keep
	 * array[fast] = ' ' && slow != 0 && array[slow - 1] == ' ', not keep
	 * 
	 * simplified to:
	 * case 1. array[fast] != ' ', keep
	 * case 2. array[fast] == ' '
	 * 	case 2.1 slow == 0 || array[slow - 1] == ' ', not keep
	 * 	case 2.2 otherwise, keep 
	 * 
	 * post-processing: remove trailing space if there is one
	 * array[slow - 1] = ' ', slow--
	 * 
	 * time:  O(n) traversed once
	 * space: O(1) excluding char[], or O(n) including char[]
	 * */
	public static String removeSpaces(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}

		char[] array = input.toCharArray();
		int slow = 0;
		/*
		int fast = 0;
		while (fast < array.length) {
			if (array[fast] == ' ' && (slow == 0 || array[slow - 1] == ' ')) {
				fast++;
			} else {
				array[slow++] = array[fast++];
			}
		}
		*/
		for (int i = 0; i < array.length; i++) {
			if (array[i] == ' ' && (slow == 0 || array[slow - 1] == ' ')) {
				continue;
			} else {
				array[slow++] = array[i];
			}
		}

		// post-processing
		if (slow > 0 && array[slow - 1] == ' ') {
			slow--;
		}

		return new String(array, 0, slow);
	}

	public static void main(String[] args) {
		System.out.println(removeSpaces("  a   bc  d  "));
	}
}
