package string;

import java.util.ArrayDeque;
import java.util.Deque;

public class DedupAdjacentRepeatedly {

	/**
	 * "zabbbazw" -> "w"
	 * space O(n): need to optimize this to O(1)
	 * */
	public static String dedupAdjacentRepeatedly(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] array = input.toCharArray();
		Deque<Character> stack = new ArrayDeque<Character>();
		
		for (int fast = 0; fast < array.length; fast++) {
			if (stack.isEmpty() || array[fast] != stack.peekFirst()) {
				stack.offerFirst(array[fast]);
			} else {
				while (fast + 1 < array.length && array[fast + 1] == stack.peekFirst()) {
					fast++;
				}
				stack.pollFirst();
			}
		}
		
		return stackToString(stack);
	}

	private static String stackToString(Deque<Character> stack) {
		if (stack == null || stack.size() == 0) {
			return new String();
		}
		
		char[] result = new char[stack.size()];
		for (int i = result.length - 1; i >= 0; i--) {
			result[i] = stack.pollFirst();
		}
		return new String(result);
	}
	
	public static void main(String[] args) {
		System.out.println(new String());
		System.out.println(dedupAdjacentRepeatedly("zabbbazw"));
	}
}
