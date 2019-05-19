package algorithms.sorting;

import java.util.LinkedList;

public class SelectionSortWithStacks {

	/**
	 * given a stack of int, use selection sort and 2 additional stacks 
	 * to put numbers in input in ascending order from top to bottom
	 * time: O(n^2)
	 * space: no additional  
	 * */

	public static void selectionSortThreeStacks(LinkedList<Integer> input) {
		// corner
		if (input == null || input.size() <= 1) {
			return;
		}

		// input will store sorted elements
		// _unsorted will store unsorted elements as input for next iteration
		// _buffer 
		LinkedList<Integer> _buffer = new LinkedList<Integer>();
		LinkedList<Integer> _unsorted = new LinkedList<Integer>();

		while (!input.isEmpty()) {
			_unsorted.offerFirst(input.pollFirst());
		}

		while (!_unsorted.isEmpty()) {
			int min = Integer.MAX_VALUE;
			min = findMin(_unsorted, _buffer, min);
			partition(input, _buffer, _unsorted, min);
		}

	}

	private static int findMin(LinkedList<Integer> _unsorted, LinkedList<Integer> _buffer, int min) {
		while (!_unsorted.isEmpty()) {
			int temp = _unsorted.pollFirst();
			if (temp <= min) {
				min = temp;
			}
			_buffer.offerFirst(temp);
		}
		return min;
	}

	private static void partition(LinkedList<Integer> input, LinkedList<Integer> _buffer, LinkedList<Integer> _unsorted,
			int min) {
		while (!_buffer.isEmpty()) {
			int temp = _buffer.pollFirst();
			if (temp == min) {
				input.offerLast(temp);
			} else {
				_unsorted.offerFirst(temp);
			}
		}
	}

	/**
	 * given a stack of int, use selection sort and 1 additional stack 
	 * to put numbers in input in ascending order from top to bottom
	 * time: O(n^2)
	 * space: no additional
	 * */

	public static void selectionSortTwoStacks(LinkedList<Integer> input) {
		// corner
		if (input == null || input.size() <= 1) {
			return;
		}

		// input will store sorted | unsorted 
		// _buffer 
		LinkedList<Integer> _buffer = new LinkedList<Integer>();

		// input --> _buffer, find min 
		int min = Integer.MAX_VALUE;
		int minCount = 0;
		while (!input.isEmpty()) {
			int temp = input.pollFirst();
			if (temp < min) {
				min = temp;
				minCount = 1;
			} else if (temp == min) {
				minCount++;
			}
			_buffer.offerLast(temp);
		}

		// termination: input in order -> no more elements popped from input -> _buffer.isEmpty()
		while (!_buffer.isEmpty()) {
			// min to input
			for (int i = 0; i < minCount; i++) {
				input.offerLast(min);
			}
			// non-min to input
			while (!_buffer.isEmpty()) {
				int temp = _buffer.pollFirst();
				if (temp > min) {
					input.offerLast(temp);
				}
			}
			
			// input [sorted | unsorted]
			// input | unsorted --> _buffer, find new min within unsorted elements
			int minCurr = Integer.MAX_VALUE;
			minCount = 0;
			// > min: do not move sorted elements
			while (!input.isEmpty() && input.peekLast() > min) {
				int temp = input.pollLast();
				if (temp < minCurr) {
					minCurr = temp;
					minCount = 1;
				} else if (temp == minCurr) {
					minCount++;
				}
				_buffer.offerLast(temp);
			}
			min = minCurr;
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> stack = new LinkedList<>();
		stack.push(4);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.push(2);
		stack.push(2);
		System.out.println("input stack: " + stack);
		System.out.println("peek top: " + stack.peek());
		selectionSortTwoStacks(stack);
		System.out.println("resulting stack: " + stack);
	}
}
