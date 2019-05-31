package recursion.returnvaluebottomup;

import tree.TreeNode;

public class TreeHeight {

	public static <E  extends Comparable<E>> int getHeight(TreeNode<E> root) {
		if (root == null) {
			return 0;
		}
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		return 1 + Math.max(left, right);
	}
	
}
