
package algorithms.BFS;

import java.util.PriorityQueue;

/**
 * given a matrix of n x m, each row and each column are sorted in ascending order
 * find the kth smallest number
 * */
public class kthSmallest {
	
	private class Cell implements Comparable<Cell> {
		int row;
		int col;
		int key;
		
		public Cell(int row, int col, int key) {
			this.row = row;
			this.col = col;
			this.key = key;
		}
		
		public int compareTo(Cell another) {
			return Integer.valueOf(this.key).compareTo(another.key);
		}
	}

	public int kthSmallestInSortedMatrix(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
			throw new IllegalArgumentException(""); 
		}
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		PriorityQueue<Cell> minheap = new PriorityQueue<>();
		boolean[][] visited = new boolean[rows][cols];
		
		minheap.offer(new Cell(0, 0, matrix[0][0]));
		visited[0][0] = true;
		
		for (int i = 0; i < k - 1; i++) {
			Cell curr = minheap.poll();
			int currRow = curr.row;
			int currCol = curr.col;
			int nextRow = curr.row + 1;
			int nextCol = curr.col + 1;
			if (nextRow < rows && !visited[nextRow][currCol]) {
				minheap.offer(new Cell(nextRow, currCol, matrix[nextRow][currCol]));
				visited[nextRow][currCol] = true;
			}
			if (nextCol < cols && !visited[currRow][nextCol]) {
				minheap.offer(new Cell(currRow, currCol, matrix[currRow][nextCol]));
				visited[currRow][nextCol] = true;
			}
		}
		return minheap.peek().key;
	}
}
