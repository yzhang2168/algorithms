package recursion.returnvaluebottomup;


import tree.TreeNode;

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
public class LCAClassic<E extends Comparable<E>> {
	public TreeNode<E> lowestCommonAncestor(TreeNode<E> root, TreeNode<E> one, TreeNode<E> two) {
		TreeNode<E> ancestor = LCA(root, one, two);
		// case 1: one is ancestor of two: LCA(one, two, two) returns two
		// case 2: two is not in this tree: LCA(one, two, two) returns null
		if (ancestor == one && LCA(one, two, two) == null) {
			return null;
		} else if (ancestor == two && LCA(two, one, one) == null) {
			return null;
		} else {
			return ancestor;
		}     
	}

	public TreeNode<E> LCA(TreeNode<E> root, TreeNode<E> a, TreeNode<E> b) {
		// base case
		if (root == null || root == a || root == b) {
			return root;
		}

		// step 1
		TreeNode<E> left = LCA(root.left, a, b);
		TreeNode<E> right = LCA(root.right, a, b);

		// step 2
		if (left != null && right != null) {
			return root;
		} else {
			return left != null ? left : right;
		}		
	}
}
