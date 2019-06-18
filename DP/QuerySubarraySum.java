package DP;

public class QuerySubarraySum {
	/**
	 * given an integer array a[n]
	 * repeated queries for the sum between a[i] and a[j]
	 * how to speed up such queries?
	 * 
	 * brute force
	 * for i
	 * 	   for j // (i...j)
	 *         sum(a[i]...a[j]) => sum[j] = sum[j - 1] + a[j]
	 * time: n ^ 3 => n ^2
	 * space: n ^ 2
	 * query time: O(1)
	 * 
	 * optimal solution: prefix sum
	 * 计算中间元素的和，把0...i存下来，query时候做减法
	 * sum[i...j] = m[0...j] - m[0...i] + a[i]
	 * 
	 * 
	 * time: O(n)
	 * space: O(n)
	 * */
	// prefixSum: 0...i
	private int[] prefixSum(int[] array) {
		int n = array.length;
		int[] prefixSum = new int[n];		
		for (int i = 0; i < n; i++) {
			prefixSum[i] += array[i];
		}
		return prefixSum;
	}
	
	public int querySubarraySum(int[] array, int i, int j) {
		int[] prefixSum = prefixSum(array);
		// to handle i == 0
		return prefixSum[j] - prefixSum[i] + array[i];
		// prefixSum[j] - prefixSum[i - 1] corner case i == 0
	}
}
