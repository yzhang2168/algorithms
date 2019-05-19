package algorithms.string;

public class DedupAdjacentRepeatedlyOptimized {

	/**
	 * "zabbbazw" -> "w"
	 * conceptually build a stack: [0...slow-1] all processed, to keep 
	 * space O(1)
	 * 
	 * */
	public static String dedupAdjacentRepeatedlyOptimized(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] array = input.toCharArray();
		int slow = 1;
		for (int fast = 1; fast < array.length; fast++) {
			if (slow == 0 || array[fast] != array[slow - 1]) {
				array[slow++] = array[fast];
			} else { //if (array[fast] == array[slow - 1]) {
				while (fast + 1 < array.length && array[fast + 1] == array[slow - 1]) {
					fast++;
				}
				slow--;
			}
		}
		return new String(array, 0, slow);
	}	

	public static void main(String[] args) {
		System.out.println(new String());
		System.out.println(dedupAdjacentRepeatedlyOptimized("zabbbazw"));
	}

}
