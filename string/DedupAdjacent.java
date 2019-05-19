package algorithms.string;

public class DedupAdjacent {

	public static String dedupAdjacentLetters(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] array = input.toCharArray();
		int slow = 0;
		for (int fast = 1; fast < array.length; fast++) {
			if (slow == 0 || array[fast] != array[slow - 1]) {
				array[slow++] = array[fast];
			}
		}
		return new String(array, 0, slow);
	}

	public static void main(String[] args) {
		System.out.println(dedupAdjacentLetters("aaabbazw"));
	}
}
