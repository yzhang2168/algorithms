
package BFS;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * given a matrix of n x m, each row and each column are sorted in ascending order
 * find the kth smallest number
 * pick keywords in problems and use a simple use case to understand the problem
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
		
		@Override
		public int compareTo(Cell another) {
			return Integer.compare(this.key, another.key);
		}
		
		@Override
		public int hashCode() {
			return row + col * 17 + key * 17 * 17; 
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			} else if (! (o instanceof Cell)) {
				return false;
			} else {
				Cell other = (Cell) o;
				return this.row == other.row && this.col == other.col && this.key == other.key;
			}
		}
		
		@Override
		public String toString() {
			return this.row + "," + this.col;
		}
	}

	public int kthSmallestInSortedMatrixI(int[][] matrix, int k) {
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
	
	/**
	 * use a hashset to dedup:
	 * need to rewrite hashCode() and equals() for object comparison
	 * */
	public int kthSmallestInSortedMatrixII(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
			throw new IllegalArgumentException(""); 
		}
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		PriorityQueue<Cell> minheap = new PriorityQueue<>();
		HashSet<Cell> visited = new HashSet<>();
		
		Cell curr = new Cell(0, 0, matrix[0][0]);
		minheap.offer(curr);
		visited.add(curr);
		
		for (int i = 0; i < k - 1; i++) {
			curr = minheap.poll();
			int currRow = curr.row;
			int currCol = curr.col;
			int nextRow = curr.row + 1;
			int nextCol = curr.col + 1;
			
			if (nextRow < rows) {
				curr = new Cell(nextRow, currCol, matrix[nextRow][currCol]);
				if (!visited.contains(curr)) {
					minheap.offer(curr);
					visited.add(curr);
				}
			}
			
			if (nextCol < cols) {
				curr = new Cell(currRow, nextCol, matrix[currRow][nextCol]);
				if (!visited.contains(curr)) {
					minheap.offer(curr);
					visited.add(curr);
				}
			}
		}
		return minheap.peek().key;
	}
	
	public int kthSmallestInSortedMatrixIII(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || k <= 0) {
			throw new IllegalArgumentException(""); 
		}
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		PriorityQueue<Cell> minheap = new PriorityQueue<>();
		HashSet<String> visited = new HashSet<>();
		
		Cell curr = new Cell(0, 0, matrix[0][0]);
		minheap.offer(curr);
		visited.add(curr.toString());
		
		for (int i = 0; i < k - 1; i++) {
			curr = minheap.poll();
			int currRow = curr.row;
			int currCol = curr.col;
			int nextRow = curr.row + 1;
			int nextCol = curr.col + 1;
			
			if (nextRow < rows) {
				curr = new Cell(nextRow, currCol, matrix[nextRow][currCol]);
				if (!visited.contains(curr.toString())) {
					minheap.offer(curr);
					visited.add(curr.toString());
				}
			}
			
			if (nextCol < cols) {
				curr = new Cell(currRow, nextCol, matrix[currRow][nextCol]);
				if (!visited.contains(curr.toString())) {
					minheap.offer(curr);
					visited.add(curr.toString());
				}
			}
		}
		return minheap.peek().key;
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{1,3,5,7},{2,4,8,9},{3,5,11,15},{6,8,13,18}};
		int k = 6;
		kthSmallest test = new kthSmallest();
		System.out.println(test.kthSmallestInSortedMatrixI(matrix, k));
		System.out.println(test.kthSmallestInSortedMatrixII(matrix, k));
		System.out.println(test.kthSmallestInSortedMatrixIII(matrix, k));
	}
}
