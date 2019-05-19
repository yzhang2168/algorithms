package algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Comparator;

public class KSmallestInArray {
	/**
	 * input: unsorted array/matrix of size n 
	 * output: kth smallest
	 * 
	 * 1. maxheap:
	 * time: k + (n - k) logk
	 * online
	 * */
	public Integer kthSmallestMaxheap(int[] array, int k) {
		if (k <= 0 || array == null || array.length < k) {
			return null;
		}
		
		//PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(k, Collections.reverseOrder());
		PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
				/*
				if (o1.equals(o2)) {
					return 0;
				}
				return o1 < o2 ? 1 : -1;
				*/
			}
		});
		
		for (int i = 0; i < array.length; i++) {
			if (i < k) {
				maxheap.offer(array[i]);
			} else if (array[i] < maxheap.peek()) {
					maxheap.poll();
					maxheap.offer(array[i]);
			}
		}
		return maxheap.poll();
	}
	
	/**
	 * offline
	 * */
	public Integer kthSmallestMinheap(int[] array, int k) {
		if (k <= 0 || array == null || array.length < k) {
			return null;
		}
		PriorityQueue<Integer> minheap = new PriorityQueue<Integer>(array.length);
		
		for (int i = 0; i < array.length; i++) {
			minheap.offer(array[i]);
		}
		
		Integer result = null; 
		for (int i = 0; i < k; i++) {
			result = minheap.poll();
		}
		return result;
	}
	
	/**
	 * average case: partition n + n / 2 + n / 4 + ... + 1 = O(n)
	 * worst case:   partition n + n - 1 + n - 2 + ... + 1 = O(n^2)
	 * */
	public Integer kthSmallestQuickSelect(int[] array, int k) {
		if (k <= 0 || array == null || array.length < k) {
			return null;
		}
		return quickSelect(array, k - 1, 0, array.length - 1);
	}
	
	// finds k smallest elements, but k elements are not sorted
	private int quickSelect(int[] array, int k, int l, int r) {
		int pivotIndex = partition(array, l, r);
		if (pivotIndex == k) {
		    /*
			int[] result = Arrays.copyOf(array, k + 1);
		    Arrays.sort(result);
			return result;
			*/
			return array[pivotIndex];
		} else if (pivotIndex < k) {
			return quickSelect(array, k, pivotIndex + 1, r);
		} else {
			return quickSelect(array, k, l, pivotIndex - 1);
		}		
	}
	
	private int partition(int[] array, int l, int r) {
		int pivotIndex = getRandomIndex(l, r);
		int pivotValue = array[pivotIndex];
		swap(array, pivotIndex, r);
		int leftBound = l;
		int rightBound = r - 1;
		while (leftBound <= rightBound) {
			if (array[leftBound] < pivotValue) {
				leftBound++;
			} else if (array[rightBound] >= pivotValue) {
				rightBound--;
			} else {
				swap(array, leftBound++, rightBound--);
			}
		}
		swap(array, leftBound, r);
		return leftBound;
	}
	
	private int getRandomIndex(int l, int r) {
		return l + (int) (Math.random() * (r - l + 1));
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] array = {5,3,4,2,1,1,2,1,8,4,4,9,13,5,8};
		int k = 15;
		int j = 5;
		KSmallestInArray test = new KSmallestInArray();
		System.out.println(test.kthSmallestQuickSelect(array, k));
		System.out.println(test.kthSmallestMaxheap(array, k));
		System.out.println(test.kthSmallestQuickSelect(array, j));
		System.out.println(test.kthSmallestMaxheap(array, j));
	}
	
}
