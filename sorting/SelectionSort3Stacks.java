package algorithms.sorting;

import java.util.ArrayDeque;
import java.util.Deque;


public class SelectionSort3Stacks {

	public void selectionSort(Deque<Integer> input) {
		if (input == null || input.size() <= 1) {
			return;
		}

		Deque<Integer> buffer = new ArrayDeque<Integer>();
		Deque<Integer> result = new ArrayDeque<Integer>();

		while (!input.isEmpty()) {
			int min = findMin(input, buffer);
			partition(input, buffer, result, min);
		}
		
		while (!result.isEmpty()) {
			input.push(result.pop());
		}
	}

	public int findMin(Deque<Integer> input, Deque<Integer> buffer) {
		int min = Integer.MAX_VALUE;
		while (!input.isEmpty()) {
			int temp = input.pop();
			if (temp < min) {
				min = temp;
			} 
			buffer.push(temp);
		}
		return min;
	}

	public static void partition(Deque<Integer> input, Deque<Integer> buffer, Deque<Integer> result, int min) {
		while(!buffer.isEmpty()) {
			int temp = buffer.pop();
			if (temp == min) {
				result.push(temp);
			} else {
				input.push(temp);
			}
		}
	}

	public static void main(String[] args) {
		SelectionSort3Stacks obj = new SelectionSort3Stacks();
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(4);
		stack.push(2);
		stack.push(3);
		stack.push(1);
		stack.push(2);
		stack.push(2);
		System.out.println("input stack: " + stack);
		System.out.println("peek top: " + stack.peek());
		obj.selectionSort(stack);
		System.out.println("resulting stack: " + stack);
	}

}
