package algorithms.recursion.straightpath;

import data_structures.TreeNode;
/**
 * from any node to any node (can be the same node) on the same straight path
 * */
public class MaxSumStraightPath {

	public static int maxSumStraightPathI(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		// lastSum records largest sum from curr node to root, including curr node
		int lastSum = Integer.MIN_VALUE;
		int[] globalMax = {Integer.MIN_VALUE};
		maxSumStraightPathI(root, lastSum, globalMax);
		return globalMax[0];
	}
	
	public static void maxSumStraightPathI(TreeNode root, int lastSum, int[] globalMax) {
		if (root == null) {
			return;
		}

		if (lastSum <= 0) {
			lastSum = root.value;
		} else {
			lastSum += root.value;
		}
		
		globalMax[0] = Math.max(globalMax[0], lastSum);
		
		maxSumStraightPathI(root.left, lastSum, globalMax);
		maxSumStraightPathI(root.right, lastSum, globalMax);	
	}
	
	
	public static int maxSumStraightPathII(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int[] max = {Integer.MIN_VALUE}; 
		maxSumStraightPathII(root, max);
		return max[0];
	}	
	
	/**
	 * 3-step recursion: easier for me
	 * */
	public static int maxSumStraightPathII(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		// step 1: want from left and right subtrees
		int leftSum = maxSumStraightPathII(root.left, max);
		int rightSum = maxSumStraightPathII(root.right, max);
				
		// step 2: curr level
		leftSum = leftSum < 0 ? 0 : leftSum; 
		rightSum = rightSum < 0 ? 0 : rightSum; 
		int curr = Math.max(leftSum, rightSum) + root.value;
		// the most important line
		max[0] = Math.max(max[0], curr);
		
		// step 3: what to return to parent call
		return curr;
	}	
		
}
