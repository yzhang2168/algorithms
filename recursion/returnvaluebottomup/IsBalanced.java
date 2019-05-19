package algorithms.recursion.returnvaluebottomup;

import data_structures.TreeNode;

public class IsBalanced {

	public static boolean isBalanced(TreeNode root) {
		return getBalancedHeight(root) != -1;
	}
	
	public static int getBalancedHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		// step 1: want from left and right subtrees
		int leftHeight = getBalancedHeight(root.left);
		int rightHeight = getBalancedHeight(root.right);
		
		// step 2: what to do on the curr level
		// step 3: what to return to parent call
		if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		} else {
			return 1 + Math.max(leftHeight, rightHeight); 
		}
	}
}
