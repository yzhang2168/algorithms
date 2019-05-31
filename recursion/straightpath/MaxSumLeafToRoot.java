package recursion.straightpath;

import java.util.ArrayList;
import java.util.List;
import recursion.TreeNode;

/**
 * 直上直下(非人字形)的题目
 * maintain a 直上直下 path prefix
 * 	complete path from leaf to root
 * 	a subsection of path from leaf to root
 * preorder traversal, maintain a path prefix (root to current node)
 * */
public class MaxSumLeafToRoot {
	public int maxPathSumRootToLeaf(TreeNode root) {
		int presum = 0;
		int[] max = new int[] {Integer.MIN_VALUE};
		maxPathSumRootToLeaf(root, max, presum);
		return max[0];
	}
	
	private void maxPathSumRootToLeaf(TreeNode root, int[] max, int presum) {
		// under node with 1 child only
		if (root == null) {
			return;
		}
		
		// leaf node
		if (root.left == null && root.right == null) {
			presum += root.value;
			max[0] = Math.max(max[0], presum);
			return;
		}
		
		presum += root.value;
		maxPathSumRootToLeaf(root.left, max, presum);
		maxPathSumRootToLeaf(root.right, max, presum);		
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

	
	
	public static void main(String[] args) {

	}
}
