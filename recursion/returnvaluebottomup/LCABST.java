package recursion.returnvaluebottomup;

import java.util.List;

/**
 * The main idea of the solution is, while traversing from top to bottom, the
 * first node n we encounter with value between n1 and n2, i.e., n1 < n < n2 or
 * same as one of the n1 or n2, is LCA of n1 and n2 (assuming that n1 < n2). So
 * just recursively traverse the BST in, if node’s value is greater than both n1
 * and n2 then our LCA lies in left side of the node, if it’s is smaller than
 * both n1 and n2, then LCA lies on right side. Otherwise root is LCA (assuming
 * that both n1 and n2 are present in BST)
 */
public class LCABST {
	class TreeNode {
		private int key;
		TreeNode left;
		TreeNode right;

		public TreeNode(int key) {
			this.key = key;
			this.left = null;
			this.right = null;
		}
	}

	public TreeNode lcaBST(TreeNode root, int p, int q) {
		if (root == null || root.key == p || root.key == q) {
			return root;
		}

		if (p < root.key && q < root.key) {
			return lcaBST(root.left, p, q);
		} else if (p > root.key && q > root.key) {
			return lcaBST(root.right, p, q);
		} else {
			return root;
		}
	}
}
