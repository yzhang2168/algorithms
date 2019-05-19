package algorithms.recursion.returnvaluebottomup;

import data_structures.TreeNode;

/**
 * any node to any node
 * the two nodes can be the same node
 * they can only be on the same path from root to one of the leaf nodes 
 * 
 * step 1: want from left and right subtrees
 * max single straight path in left subtree that starts from left child: /
 * max single straight path in right subtree that starts from left child: \
 * 
 * step 2: logic in current level
 * combine 2 straight paths with curr node into /\
 * only if left max sum > 0 and right max sum > 0
 * update globalmax if needed
 * 
 * step 3: return to parent
 * max straight path
 * if left max sum <= 0, discard
 * if right max sum <= 0, discard
 * max (left max, right max) + root.value  
 * */
public class MaxSumPathAnyPaths {

	public static int maxSumPathAnyPaths (TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int[] globalMax = {0};
		
		maxSumPathAnyPaths(root, globalMax);
		
		return globalMax[0];
	}
	
	// return max sum from one single straight path
	private static int maxSumPathAnyPaths (TreeNode root, int[] globalMax) {
		if (root == null) {
			return 0;
		}
		
		// step 1: want from left and right subtrees
		int leftSinglePath = maxSumPathAnyPaths(root.left, globalMax);
		int rightSinglePath = maxSumPathAnyPaths(root.right, globalMax);		
		
		// step 2: logic at curr level
		leftSinglePath = leftSinglePath < 0 ? 0 : leftSinglePath;
		rightSinglePath = rightSinglePath < 0 ? 0 : rightSinglePath; 
		int totalSum = leftSinglePath + rightSinglePath + root.value;		
		globalMax[0] = Math.max(globalMax[0], totalSum);
		
		// step 3: what to return to parent
		int singlePathSum = Math.max(leftSinglePath, rightSinglePath) + root.value;
		return singlePathSum;		
	}
}
