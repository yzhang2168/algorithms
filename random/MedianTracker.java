package algorithms.random;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Given an unlimited flow of numbers, keep track of the median of all elements seen so far.
 * You will have to implement the following two methods for the class
 * read(int value) - read one value from the flow
 * median() - return the median at any time, return null if there is no value read so far
 * Examples
 * read(1), median is 1
 * read(2), median is 1.5
 * read(3), median is 2
 * read(10), median is 2.5
 * 
 * data structure:
 * an hour glass of maxheap-minheap
 * 	smaller half stored in maxheap: we care about the max, which is (part of) the solution
 * 	larger half stored in minheap: we care about the min, which can be part of the solution
 * 	two properties:
 * 		1. smaller half <= larger half
 * 		2. size of smaller half == size of larger half
 * 		   size of smaller half == size of larger half + 1
 * 
 * algorithm:
 * initialization: maxheap {}, minheap {}
 * for each step: 
 * 	1. compare	
 * 		if new element < min of larger half, insert it into smaller half
 * 		if new element >= min of larger half, insert it into larger half
 * 	2. adjust size if needed
 * 		if size of smaller half == size of larger half: ignore
 * 		if size of smaller half < size of larger half: move min of larger half to smaller half
 * 		if size of smaller half > size of larger half + 1: move max of smaller half to larger half
 * */
public class MedianTracker {
	private PriorityQueue<Integer> smallerHalf; // max heap, max of this max heap is (part of) the solution 
	private PriorityQueue<Integer> largerHalf; // min heap, min of this min heap can be part of the solution
	
	public MedianTracker() {
		this.smallerHalf = new PriorityQueue<Integer>(Collections.reverseOrder()); // max heap!
		this.largerHalf = new PriorityQueue<Integer>();
	}
	
	public void read(int value) {
		if (smallerHalf.isEmpty() || value <= smallerHalf.peek()) {
			smallerHalf.add(value);			
		} else {
			largerHalf.add(value);	
		}
		
		// need to main the size property
		// size(smallerHalf) == size(largerHalf) for even number of values
		// size(smallerHalf) == size(largerHalf) + 1 for odd number of values
		if (smallerHalf.size() > largerHalf.size() + 1) {
			int temp = smallerHalf.poll();
			largerHalf.offer(temp);
		} else if (smallerHalf.size() < largerHalf.size()) {
			int temp = smallerHalf.poll();
			largerHalf.offer(temp);
		}
	}
	
	public Double getMedian() {
		int size = size();
		if (size == 0) {
			return null;
		} else if (size % 2 == 0) {
			return (largerHalf.peek() + smallerHalf.peek()) / 2.0;
		} else {
			return (double) largerHalf.peek();
		}
	}
	
	private int size() {
		return smallerHalf.size() + largerHalf.size();
	}
}
