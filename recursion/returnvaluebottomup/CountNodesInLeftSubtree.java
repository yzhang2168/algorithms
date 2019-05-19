package algorithms.recursion.returnvaluebottomup;


public class CountNodesInLeftSubtree {
	class TreeNode {
		private int key;
		private TreeNode left;
		private TreeNode right;
		private int leftNodes;

		public TreeNode(int key){
			this.key = key;
		}

		public void getLeftNodes(TreeNode root){
			nodesInLeftSubtree(root);
		}

		private int nodesInLeftSubtree(TreeNode root) {
			if (root == null) {
				return 0;
			}

			// step 1: want from left and right subtrees
			int leftNodes = nodesInLeftSubtree(root.left);
			int rightNodes = nodesInLeftSubtree(root.right);

			// step 2: what to do in curr level
			root.leftNodes = leftNodes;

			// step 3: what to return to parent
			return 1 + leftNodes + rightNodes;
		}
	}
}
