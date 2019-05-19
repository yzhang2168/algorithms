package algorithms.recursion.returnvaluebottomup;

import java.util.List;
import java.util.Set;

import algorithms.recursion.returnvaluebottomup.LCAKnaryTree.TreeNode;

public class LCAOfMNodesKnaryTree {
	
	public class TreeNode {
		 private int value;
		 List<TreeNode> children;
		
		 public TreeNode(int key) {
			 this.value = key;
		 }
	}
	
	public static TreeNode LCAMNodesKnary(TreeNode root, Set<TreeNode> nodes) {
		// base case
		if (root == null || nodes.contains(root)) {
			return root;
		}
		
		int counter = 0;
		TreeNode temp = null;
		for (TreeNode child : root.children) {
			TreeNode node = LCAMNodesKnary(child, nodes);
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
