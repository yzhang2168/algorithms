package algorithms.recursion;

import data_structures.TreeNode;

/**
 * given a binary tree where all right nodes are leaf nodes
 * flip it upside down and turn it into a tree where all left nodes are leaf nodes
 * 		 1
 * 		/ \
 *	   2   3
 *	  / \
 *   4   5
 *  / \
 * 6   7  
 * 
 * 		 1
 * 		/ 
 *	   2 - 3
 *	  / 
 *   4 - 5
 *  / 
 * 6 - 7
 * 6 is new root  
 * */
public class ReverseBinaryTree {

	public static TreeNode flipBinaryTree(TreeNode root) {
		if (root == null || root.left == null) {
			return root;
		}
		
		TreeNode newRoot = flipBinaryTree(root.left);
		TreeNode leftChild = root.left;
		leftChild.left = root.right;
		leftChild.right = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}
}
