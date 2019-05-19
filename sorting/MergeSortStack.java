package algorithms.sorting;

import java.util.Deque;
import java.util.LinkedList;

/**
 * given numbers in stack1, use mergesort to sort them
 * after sorting, numbers in stack1 should be in ascending order from top to bottom
 * */
public class MergeSortStack {
	public static void sort(LinkedList<Integer> s1) {
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		LinkedList<Integer> s3 = new LinkedList<Integer>();
		sort(s1, s2, s3, s1.size());	
	}
	
	private static void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int length) {
		if (length <= 1) {
			return;
		}
		int mid1 = length / 2;
		int mid2 = length - length / 2;
		for (int i = 0; i < mid1; i++) {
			s2.offerFirst(s1.pollFirst());
		}
		
		// use the other 2 stacks to sort s2 or s1
		// after sorting, numbers in s2 or s1 are in ascending order 
		// from top to bottom in the two stacks
		// s2: LeftHalf, to sort, s3: [] buffer, s1: RightHalf, buffer
		sort(s2, s3, s1, mid1); 
		// s2: RightHalf, to sort, s3: [] buffer, s1: LeftHalf, buffer
		sort(s1, s3, s2, mid2);
		
		// add s1, s2 length elements to s3, resulting in descending order
		int i = 0;
		int j = 0;
		while (i < mid1 && j < mid2) {
			if (s2.peekFirst() < s1.peekFirst()) {
				s3.offerFirst(s2.pollFirst());
				i++;
			} else {
				s3.offerFirst(s1.pollFirst());
				j++;
			}
		}
		
		while (i < mid1) {
			s3.offerFirst(s2.pollFirst());
			i++;
		}
		
		while (j < mid2) {
			s3.offerFirst(s1.pollFirst());
			j++;
		}
		
		// After merging, numbers are in descending order from top to bottom in s3.
		// Need to push them back to s1 so that they are in ascending order
		for (int index = 0; index < length; index++) {
			s1.offerFirst(s3.pollFirst());
		}
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> s1 = new LinkedList<Integer>();
		s1.push(4);
		s1.push(2);
		s1.push(3);
		s1.push(1);
		sort(s1);
		System.out.println(s1);
	}
}
