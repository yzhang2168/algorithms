package algorithms.recursion.returnvaluebottomup;

import data_structures.TreeNode;

/**
 * Given two nodes in a binary tree, find their lowest common ancestor.
 * Assumptions
 * There is no parent pointer for the nodes in the binary tree
 * The given two nodes are guaranteed to be in the binary tree
 * Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5
The lowest common ancestor of 2 and 9 is 9


case 1: if a and b are under root, return root
case 2: if either a or b is under root, return a/b
case 3: if neither a nor b is under root, return null

if it returns c: c is the LCA of a and b
if it returns a: a is the LCA for a and b, or b is not found
to check if b is in this tree, call find(b), or LCA(root, b, b) 
 * */
public class LCAClassic {

	public static TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
		// base case
		if (root == null || root == a || root == b) {
			return root;
		}
		
		// step 1
		TreeNode left = LCA(root.left, a, b);
		TreeNode right = LCA(root.right, a, b);
		
		// step 2
		if (left != null && right != null) {
			return root;
		} else {
			return left != null ? left : right;
		}		
	}
}
