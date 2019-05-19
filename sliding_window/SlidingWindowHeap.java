package algorithms.sliding_window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * data structure: 
 * 	max heap that stores all elements in the window + some preceding elements to be lazily deleted
 * 	each element <value, index>
 *  heap property: 
 *  	lazy deletion: 
 *		  	check heap top, if index < left border, pop it till top is in window; otherwise, keep it in heap.
 *  	eager deletion: delete elements not in window, not easy for heap
 * algorithm
 * 	initialization: insert all k elements into max heap
 *  for each step: (k+1)th...end
 *  	insert new element into max heap
 *   	keep popping the max heap until the top is within the sliding window
 *   	add top of max heap to result
 * time:
 * 	for each sliding: 
 * 		offer() O(logn)
 * 		 poll() O(logn)
 *  # sliding: n - k
 *  total: (n - k)logn
 * */
public class SlidingWindowHeap {
	// inner class
	class Element implements Comparable<Element> {
		private int value;
		private int index;
		
		public Element(int value, int index) {
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
		
		@override
		public String toString() {
			return String.valueOf(this.value);
		}
	}
	/*
	private Queue<Element> maxheap;
	
	public SlidingWindowHeap() {
		//this.maxheap = new PriorityQueue<Element>(); // minheap to track min in each sliding window
		this.maxheap = new PriorityQueue<Element>(Collections.reverseOrder()); // maxheap to track max in each sliding window
	}
	*/
	public List<Integer> maxInWindows(int[] input, int k) {
		List<Integer> result = new ArrayList<Integer>(); 
		
		if (input == null || input.length == 0 || k <= 0) {
			return result;
		}
		
		k = Math.min(k, input.length);
		
		Queue<Element> maxheap = new PriorityQueue<Element>(Collections.reverseOrder());
		
		for (int i = 0; i < k; i++) {
			maxheap.offer(new Element(input[i], i));	
		}
		result.add(maxheap.peek().value);
		
		for (int j = k; j < input.length; j++) {
			maxheap.offer(new Element(input[j], j));
			while (maxheap.peek().index < j - k + 1) {
				maxheap.poll();
			}
			//if (!maxheap.isEmpty()) {
			result.add(maxheap.peek().value);	
			//}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		int[] input = {1, 3, 2, 5, 8, 9, 4, 7, 3};
		SlidingWindowHeap obj = new SlidingWindowHeap();
		System.out.println(obj.maxInWindows(input, 0));
		obj = new SlidingWindowHeap();
		System.out.println(obj.maxInWindows(input, 2));
		obj = new SlidingWindowHeap();
		System.out.println(obj.maxInWindows(input, 3));
		obj = new SlidingWindowHeap();
		System.out.println(obj.maxInWindows(input, 30));
	}
}
