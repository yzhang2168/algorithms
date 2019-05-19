package algorithms.sorting;

import java.util.ArrayDeque;
import java.util.Deque;

public class SelectionSort2Stacks {
	/**
	selection sort with 2 stacks
	input  [5 3 2 7 -> [2 3 || 7 5
					  sorted||unsorted
	buffer [
	min
	minCount
	sorted count

	algorithm:
	init: 
	for each step:
		shuffle input (top to unsorted) > buffer and record min and minCount
		push min * minCount to input
		push non-min from buffer > input
		border += minCount
	termination: input.size() == border
	
	s1: input
	s2: buffer + curr result
	
	method 2:
	start: s1 has input [5 2 3 0
	1. s1 > s2, find min and count
	2. s2 (> min) > s1, discard == min
	3. put min in s2
	end: s2 has sorted elements [0 2 3 5
	 */

	public static void sortTwoStacks(Deque<Integer> input) {
		if (input == null || input.size() <= 1) {
			return;
		}

		Deque<Integer> buffer = new ArrayDeque<Integer>();
		
		while (!input.isEmpty()) {
			// 1. shuffle input into buffer and find min, counter
			int[] min = findMin(input, buffer);
			
			// 2. shuffle everything != min back to input
			while(!buffer.isEmpty() && buffer.peek() >= min[0]) {
				int temp = buffer.pop();
				if (temp != min[0]) {
					input.push(temp);
				}
			}
			
			// now buffer has elements already in-place
			// 3. add min from last round
			for (int i = 0; i < min[1]; i++) {
				buffer.push(min[0]);
			}
		}
		
		// buffer -> input: sorted [5 3 2 0
		while (!buffer.isEmpty()) {
			input.push(buffer.pop());
		}		
	}
	
	private static int[] findMin(Deque<Integer> input, Deque<Integer> buffer) {
		int[] min = new int[] {Integer.MAX_VALUE, 0};
		while (!input.isEmpty()) {
			int temp = input.pop();
			if (temp < min[0]) {
				min[0] = temp;
				min[1] = 1;
			} else if (temp == min[0]) {
				min[1]++;
			}
			buffer.push(temp);
		}
		return min;
	}
	
	
	public void selectionSort(Deque<Integer> input) {
		if (input == null || input.size() <= 1) {
			return;
		}

		Deque<Integer> buffer = new ArrayDeque<Integer>();
		int n = input.size();

		while (!input.isEmpty()) {
			buffer.push(input.pop());
		}
		
		int sorted = 0;
		while (sorted < n) {
			int[] min = findMin(input, buffer, n - sorted);
			partition(input, buffer, min);
			sorted += min[1];
		}
		
		// buffer saves elements in descending order, shuffle to reverse order 
		while (!buffer.isEmpty()) {
			input.push(buffer.pop());
		}
	}

	private int[] findMin(Deque<Integer> input, Deque<Integer> buffer, int unsorted) {
		int min[] = new int[] {Integer.MAX_VALUE, 0};

		while (unsorted > 0) {
			int temp = buffer.pop();
			if (temp < min[0]) {
				min[0] = temp;
				min[1] = 1;
			} else if (temp == min[0]) {
				min[1]++;
			}
			input.push(temp);
			unsorted--;
		}

		return min;
	}

	private void partition(Deque<Integer> input, Deque<Integer> buffer, int[] min) {
		for (int i = 0; i < min[1]; i++) {
			buffer.push(min[0]);
		}

		while(!input.isEmpty()) {
			int temp = input.pop();
			if (temp > min[0]) {
				buffer.push(temp);
			}
		}	
	}

	public static void main(String[] args) {
		SelectionSort2Stacks obj = new SelectionSort2Stacks();
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(4);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.push(2);
		stack.push(2);
		System.out.println("input stack: " + stack);
		System.out.println("peek top: " + stack.peek());
		//obj.selectionSort(stack);
		//System.out.println("resulting stack: " + stack); // [2, 2, 3, 2, 4, 1]

		sortTwoStacks(stack);
		System.out.println("resulting stack: " + stack);   // [1, 2, 2, 2, 3, 4]
	}
}
