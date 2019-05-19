package algorithms.sliding_window;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * data structure: deque
 * 	keeps elements in the sliding window that are either max or could become max in the future
 * 	when input[i] and input[j] are both in the window, if i < j and input[i] < input[j], 
 *  we can remove input[i] from the deque without affecting the max, 
 *  because input[i] can no longer be the max
 * 	又大又新
 *  all elements in descending order
 *  max is the head of deque
 * algorithm: 
 * 	linear scan and look back at all previous elements
 * 
 * each sliding: 
 * 	worst case: O(k)
 * 	amortized: O(1)
 * total: O(n)	
 * */
public class SlidingWindowQueue {

	class Element implements Comparable<Element> {
		private int value;
		private int index;
		
		public Element (int value, int index) {
			this.value = value;
			this.index = index;
		}
		
		@override
		public int compareTo(Element other) {
			if (this == other || this.value == other.value) {
				return 0;
			} else {
				return this.value < other.value ? -1 : 1;
			}
		}
	}
	
	
	public List<Integer> maxInWindows(int[] input, int k) {
		List<Integer> result = new ArrayList<Integer>();
		
		if (input == null || input.length == 0 || k <= 0) {
			return result;
		}
		
		k = Math.min(k, input.length);
		
		Deque<Element> deque = new ArrayDeque<Element>(); 
		
		for (int i = 0; i < input.length; i++) {
			while (!deque.isEmpty() && deque.peekLast().value <= input[i]) {
				deque.pollLast();
			}
			
			if (!deque.isEmpty() && deque.peekFirst().index < i - k + 1) {
				deque.pollFirst();
			}
			deque.offerLast(new Element(input[i], i));
			
			if (i >= k - 1) {
				result.add(deque.peekFirst().value);				
			}			
		}
		return result;
	}
	
	/**
	 * data structure: deque
	 * 	stores indices instead of actual values in the input
	 * 	only indices in the curr sliding window
	 * 	for any index, the previous index corresponding to a smaller value is polled from the deque
	 * */
	public List<Integer> maxInWindowsSimpler(int[] input, int k) {
		List<Integer> result = new ArrayList<Integer>();
		
		if (input == null || input.length == 0 || k <= 0) {
			return result;
		}
		
		k = Math.min(k, input.length);
		
		Deque<Integer> deque = new ArrayDeque<Integer>(); 
		for (int i = 0; i < input.length; i++) {
			// discard any index with a smaller value than index i
			while (!deque.isEmpty() && input[deque.peekLast()] <= input[i]) {
				deque.pollLast();
			}
			// the head element could be out of window, if so, discard it
			if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
				deque.pollFirst();
			}
			deque.offerLast(i);
			
			// check window size
			if (i >= k - 1) {
				result.add(input[deque.peekFirst()]);				
			}
		}
		
		return result;		
	}
	
	
	public static void main(String[] args) {
		int[] input = {1, 3, 2, 5, 8, 9, 4, 7, 3};
		SlidingWindowQueue obj = new SlidingWindowQueue();
		System.out.println(obj.maxInWindows(input, 0)); // []
		System.out.println(obj.maxInWindows(input, 2)); // [3, 3, 5, 8, 9, 9, 7, 7]
		System.out.println(obj.maxInWindows(input, 3)); // [3, 5, 8, 9, 9, 9, 7]
		System.out.println(obj.maxInWindows(input, 30));// [9]
		
		System.out.println(obj.maxInWindowsSimpler(input, 0));
		System.out.println(obj.maxInWindowsSimpler(input, 2));
		System.out.println(obj.maxInWindowsSimpler(input, 3));
		System.out.println(obj.maxInWindowsSimpler(input, 30));
	}
}
