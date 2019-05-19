package algorithms.histogram;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class largestRectangleOptimal {
	/**
	 * linear scan
	 * for each col, input[i] vs input[i - 1]
	 * decreasing, prev col's right border is determined, 
	 * 		linear scan and look back at prev cols til the col < curr col
	 * 		all prev cols' right border is determined
	 * 		and curr col's left border is determined: 1st col >= ccurr col
	 * when a col's left and right borders are determined calculate area and discard the col
	 * increasing, curr col's left border is determined
	 * termination: imagine a col of 0
	 * 
	 * data structure: stack
	 * 		stores indices of cols in ascending order
	 * 
	 * new element:
	 * 		increase: add it to stack, because its left border is curr index, right border not known yet
	 * 		decrease: left element's right border is curr index - 1, calculate area and pop it
	 * 				  keep looking to the left, till an element < curr element - ascending order
	 * 
	 * O(n) 
	 * amortized pop(), push(), each element can only be inserted and popped out of the stack once and only once
	 * assumptions: array elements >= 0
	 * */
	public static int largestRectangle(int[] input) {
		int result = 0;
		// stack contains indices, not the actual values
		Deque<Integer> stack = new ArrayDeque<Integer>();
		
		for (int i = 0; i <= input.length; i++) {
			// need a way of popping out all elements at last
			// so we explicitly added a bar of height 0
			int curr = i == input.length ? 0: input[i];
			while (!stack.isEmpty() && input[stack.peekFirst()] >= curr) {
				// new element < prev
				//int left = stack.peekFirst();
				int height = input[stack.pollFirst()];
				// determine the left border of 
				// the largest rectangle with height
				int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
				// determine the right border of 
				// the largest rectangle with height of the popped element
				result = Math.max(result, height * (i - left));
			}
			// stack is empty || new element < stack top 
			stack.offerFirst(i);
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		System.out.println(largestRectangle(new int[] {2,1,3,4,5,2,6}));
	}
}
