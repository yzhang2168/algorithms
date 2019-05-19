package algorithms.recursion.straightpath;

import java.util.ArrayList;
import java.util.List;

import data_structures.TreeNode;

/**
 * 直上直下(非人字形)的题目
 * maintain a 直上直下 path prefix
 * 	complete path from leaf to root
 * 	a subsection of path from leaf to root
 * preorder traversal, maintain a path prefix (root to current node)
 * */
public class MaxSumLeafToRoot {

	public static int maxSumPathLeafToRoot(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		// use an array such that the variable is seen across all levels
		int[] globalMax = {0};
		// local variable on stack
		int prefixSum = 0;
		
		maxSumPath(root, prefixSum, globalMax);		
		return globalMax[0];
	}
	
	// this saves path info besides max sum
	private static void maxSumPath(TreeNode root, List<Integer> pathPrefix, int[] globalMax) {
		// base case: the parent of a null node may not be leaf
		if (root == null) {
			return;
		}
		
		// base case: leaf node
		if (root.left == null && root.right == null) {
			globalMax[0] = Math.max(globalMax[0], root.value + sum(pathPrefix));
			return;
		}
		
		pathPrefix.add(root.value);
		maxSumPath(root.left, pathPrefix, globalMax);
		maxSumPath(root.right, pathPrefix, globalMax);
		pathPrefix.remove(pathPrefix.size() - 1);
	}
	
	private static int sum(List<Integer> list) {
		int sum = 0;
		for (Integer i : list) {
			if (i != null) {
				sum += i;
			}
		}		
		return sum;
	}
	
	// prefixSum is a local var on stack
	private static void maxSumPath(TreeNode root, int prefixSum, int[] globalMax) {
		// corner case: the parent of a null node may not be leaf, so this is not base case
		if (root == null) {
			return;
		}
		
		// base case leaf node
		if (root.left == null && root.right == null) {
			globalMax[0] = Math.max(globalMax[0], root.value + prefixSum);
			return;
		}
		
		maxSumPath(root.left, prefixSum + root.value, globalMax);
		maxSumPath(root.right, prefixSum + root.value, globalMax);
	}
	
	
	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i <= 10; i++) {
			l.add(i);
		}
		System.out.println(l);
		System.out.println(sum(l));
	}
}
