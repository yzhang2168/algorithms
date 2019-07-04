package recursion.returnvaluebottomup;

import java.util.List;
import data_structures.TreeNode;

/**
 * LCA for two nodes in k-nary tree
 * */
public class LCAKnaryTree {

	public class TreeNode {
		 private int value;
		 List<TreeNode> children;
		
		 public TreeNode(int key) {
			 this.value = key;
		 }
	}
	
	public static TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
		// base case
		if (root == null || root == a || root == b) {
			return root;
		}
		
		// step 1
		int counter = 0;
		TreeNode temp = null;
		for (TreeNode child : root.children) {
			TreeNode node = LCA(child, a, b);
			if (node != null) {
				counter++;
				if (counter == 2) {
					return root;
				}
				temp = node;
			}			
		}
		return temp;
	}
}
