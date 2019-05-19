package algorithms.recursion.returnvaluebottomup;

import data_structures.TreeNode;

public class TreeHeight {

	public static int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		return 1 + Math.max(left, right);
	}
	
}
