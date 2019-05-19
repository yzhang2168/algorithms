package algorithms.sorting;

import java.util.LinkedList;
import java.util.Deque;

public class SortWithStacks {

	/**
	 * Given an array that is initially stored in one stack, sort it with one additional stack (total 2 stacks).
	 * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.
	 * Assumptions:
	 * The given stack is not null.
	 * Requirements:
	 * No additional memory, time complexity = O(n ^ 2).
	 * */
	// simulate selection sort O(n^2)
	public static LinkedList<Integer> selectionSortWithStack(LinkedList<Integer> s1) {
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		while (!s1.isEmpty()) {
			int min = Integer.MAX_VALUE;
			int minCount = 0;

			while (!s1.isEmpty()) {
				if (s1.peek() < min) {
					min = s1.peek();
					minCount = 1;
				} else if (s1.peek() == min) {
					minCount++;
				}
				s2.push(s1.pop());
			}

			while (!s2.isEmpty() && s2.peek() >= min) {
				if (s2.peek() == min) {
					s2.pop();
				} else {
					s1.push(s2.pop());
				}
			}
			
			for (int i = 0; i < minCount; i++) {
				s2.offerLast(min);
			}
		}
		return s2;
	}

	public static void selectionSortWith2Stacks(LinkedList<Integer> s1) {
		LinkedList<Integer> s2 = new LinkedList<Integer>();
		int min = Integer.MAX_VALUE;
		int minCount = 0;
		while (!s1.isEmpty()) {
			if (s1.peek() < min) {
				min = s1.peek();
				minCount = 1;
			} else if (s1.peek() == min) {
				minCount++;
			}
			s2.push(s1.pop());
		}

		while (!s2.isEmpty()) {
			for (int i = 0; i < minCount; i++) {
				s1.offerLast(min);				
			}

			while (!s2.isEmpty()) {
				if (s2.peek() == min) {
					s2.pop();
				} else {
					s1.push(s2.pop());
				}
			}

			int minNew = Integer.MAX_VALUE;
			minCount = 0;
			while (!s1.isEmpty() && s1.peek() > min) {
				if (s1.peek() < minNew) {
					minNew = s1.peek();
					minCount = 1;
				} else if (s1.peek() == minNew) {
					minCount++;
				}
				s2.push(s1.pop());
			}
			min = minNew;
		}
	}

	/**
	 * Given an array that is initially stored in one stack, sort it with two additional stacks (total 3 stacks).
	 * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.
	 * Assumptions:
	 * The given stack is not null.
	 * Requirements:
	 * No additional memory, time complexity = O(n ^ 2).
	 * */
	// simulate selection sort O(n^2)
	public static void selectionSortWith3Stacks(LinkedList<Integer> s1) {
		// s1 for sorted result, s2 for buffer, s3 for input
		LinkedList<Integer> s2 = new LinkedList<Integer> ();
		LinkedList<Integer> s3 = new LinkedList<Integer> ();

		// shuffle things from s1 to s2 and find curr min
		int min = Integer.MAX_VALUE;
		int minCount = 0;
		while (!s1.isEmpty()) {
			if (s1.peekFirst() < min) {
				min = s1.peekFirst();
				minCount = 1;
			} else if (s1.peekFirst() == min) {
				minCount++;
			}

			s2.push(s1.pop());
		}

		// shuffle things from s2 to s3 except curr min
		while (!s2.isEmpty()) {
			for (int i = 0; i < minCount; i++) {
				s1.offerLast(min);
			}
			while (!s2.isEmpty()) {
				if (s2.peekFirst() == min) {
					s2.pop();
				} else {
					s3.push(s2.pop());
				}
			}

			// put curr min to s1, then shuffle things from s3 to s2
			min = Integer.MAX_VALUE;
			while (!s3.isEmpty()) {
				if (s3.peekFirst() < min) {
					min = s3.peekFirst();
					minCount = 1;
				} else if (s3.peekFirst() == min) {
					minCount++;
				}
				s2.push(s3.pop());
			}
		}	
	}


	public static void main(String[] args){
		LinkedList<Integer> s1 = new LinkedList<Integer>();
		s1.push(1);
		s1.push(3);
		s1.push(2);
		s1.push(4);
		s1.push(2);
		s1.offerFirst(5);
		s1.push(2);
		s1.offerLast(10);
		s1.push(2);
		System.out.println("input s1: " + s1);
		System.out.println("peek top: " + s1.peek());
		System.out.println(selectionSortWithStack(s1));
		//selectionSortWith2Stacks(s1);
		//selectionSortWith3Stacks(s1);
		System.out.println("resulting s1: " + s1);
	}

}
