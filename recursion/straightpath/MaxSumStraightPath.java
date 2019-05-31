package recursion.straightpath;

import recursion.TreeNode;
/**
 * from any node to any node (can be the same node) on the same straight path
 * */
public class MaxSumStraightPath {
	
	public int maxPathSumStraightPath(TreeNode root) {
		// root to curr
		int currSum = 0;
		int[] max = new int[] {Integer.MIN_VALUE};
		maxPathSumStraightPath(root, max, currSum);
		return max[0];
	}
	
	private void maxPathSumStraightPath(TreeNode root, int[] max, int currSum) {
		if (root == null) {
			return;
		}
		
		currSum = currSum < 0 ? root.value : currSum + root.value;
		max[0] = Math.max(max[0], currSum);
		maxPathSumStraightPath(root.left, max, currSum);
		maxPathSumStraightPath(root.right, max, currSum);		
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
