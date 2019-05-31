package recursion.straightpath;

import recursion.TreeNode;

public class PathSumEqualsToTarget {
	/**
	 * Given a binary tree and a target sum, 
	 * determine if the tree has a root-to-leaf path 
	 * such that adding up all the values along the path equals the given target.
	 * */
	public boolean exist(TreeNode root, int target) {
		int presum = 0;
		return exists(root, target, presum);
	}

	private boolean exists(TreeNode root, int target, int presum) {
		// null under node with a single child
		if (root == null) {
			return false;
		}

		// leaf
		if (root.left == null && root.right == null) {
			presum += root.value;
			if (presum == target) {
				return true;
			}
			return false;
		}

		presum += root.value;
		if (exists(root.left, target, presum) || exists(root.right, target, presum)) {
			return true;
		} else {
			return false;
		}
	}
}
