package BFS;

import java.util.PriorityQueue;

public class KthSmallestInTwoSortedArrays {
	/**
	 * Given two sorted arrays A and B, with their sizes to be m and n, respectively. 
	 * We can pick one element a from A and the other element b from B, and their sum s is defined to be s = a + b. 
	 * How to find k-th smallest s from all possible values of s. 
	 * Assumption: k < m * n. e.g. A = {1, 3, 5}, B = {2, 3}, 
	 * k = 1, the result is A[0] + B[0] = 3 
	 * k = 2, the result is A[0] + B[1] = 4 
	 * k = 3, the result is A[1] + B[0] = 5
	 * 
	 * pick keywords in problems and use a simple use case to understand the problem
	 * A [1, 4, 5]
	 * B [2, 3, 6]
	 * B[i] + A[j]: n*m pairs
	 * 
	 *    1  4  5  A
	 * 2  3  6  7
	 * 3  4  7  8
	 * 6  7 10 11
	 * B
	 * matrix[i][j] = B[i] + A[j]
	 * 
	 * 0. represent the state B[i] + A[j]
	 * 1. initial state: <0,0>
	 * 2. expand - generation rule: PriorityQueue <i+1, j> <i, j+ 1>
	 * 3. expand k times
	 * 4. dedup: boolean[][]
	 * */
	public int kthSmallest(int[] A, int[] B, int k) {
		if (A == null || A.length == 0) {
			return B[k - 1];
		} else if (B == null || B.length == 0) {
			return A[k - 1];
		} 
		
		int n = A.length;
		int m = B.length;
		
		PriorityQueue<Sum> minheap = new PriorityQueue<Sum>();
		boolean[][] visited = new boolean[m][n];
		
		minheap.offer(new Sum(0, 0, A[0] + B[0]));
		visited[0][0] = true;
		
		// minheap poll() 1...(k-1)th
		for (int i = 1; i < k; i++) {
			Sum curr = minheap.poll();
			int currRow = curr.row;
			int currCol = curr.col;
			int nextRow = curr.row + 1;
			int nextCol = curr.col + 1;
			if (nextRow < m && !visited[nextRow][currCol]) {
				minheap.offer(new Sum(nextRow, currCol, B[nextRow] + A[currCol]));
				visited[nextRow][currCol] = true;
			}
			
			if (nextCol < n && !visited[currRow][nextCol]) {
				minheap.offer(new Sum(currRow, nextCol, B[currRow] + A[nextCol]));
				visited[currRow][nextCol] = true;
			}
		}
		
		// kth
		return minheap.peek().sum;
	}
	
	public class Sum implements Comparable<Sum> {
		int row;
		int col;
		int sum;
		
		public Sum(int row, int col, int sum) {
			this.row = row;
			this.col = col;
			this.sum = sum;
		}
		
		@Override
		public int compareTo(Sum other) {
			return Integer.compare(this.sum, other.sum);
		}
	}
	
	public static void main(String[] args) {
		int[] A = {1, 3, 5};
		int[] B = {2, 3};
		KthSmallestInTwoSortedArrays test = new KthSmallestInTwoSortedArrays();
		System.out.println(test.kthSmallest(A, B, 1));
		System.out.println(test.kthSmallest(A, B, 2));
		System.out.println(test.kthSmallest(A, B, 3));
		
	}
}
