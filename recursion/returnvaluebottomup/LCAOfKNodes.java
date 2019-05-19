package algorithms.recursion.returnvaluebottomup;

import java.util.HashSet;
import java.util.List;

import data_structures.TreeNode;

public class LCAOfKNodes {
	/**
	 * general ideas to solve k-__
	 * solution 1: iterative
	 * a
	 * b -> LCA(a, b)
	 * 			c	  -> LCA(a...c)
	 * 						d		-> LCA(a...d)
	 * 										...		-> LCA(a...k)
	 * # LCA() calls: k
	 * time for each call: O(n)
	 * total time: O(kn)
	 * 
	 * ==========================================
	 * solution 2: binary reduction
	 * a
	 * b -> LCA(a, b)
	 * 				  -> LCA(a...d)
	 * c
	 * d -> LCA(c, d)				-> LCA(a...h)
	 * 
	 * 		  k / 2		   k / 4...	
	 * 
	 * # LCA() calls: k/2 + k/4 + k/8 +... = k
	 * time for each call: O(n)
	 * total time: O(kn)
	 * ==========================================
	 * solution 3: 1 LCA() call, k all togetherHashSet<E> 
	 * 
	 * 			 1
	 * 		  /		\
	 * 		2=d		 3
	 * 	   /  \     /  \
	 *    4    5=a 6   7=b
	 *   /  \
	 *  8    9=c 
	 * */
	public TreeNode LCAKNodes(TreeNode root, List<TreeNode> nodes) {
		HashSet<TreeNode> set = new HashSet<TreeNode>();
		for (TreeNode node : nodes) {
			set.add(node);
		}
		return LCAKNodes(root, set);
	}
	
	public TreeNode LCAKNodes(TreeNode root, HashSet<TreeNode> set) {
		if (root == null || set.contains(root)) {
			return root;
		}
		
		TreeNode left = LCAKNodes(root.left, set);
		TreeNode right = LCAKNodes(root.right, set);
		if (left != null && right != null) {
			return root;
		} else {
			return left != null ? left : right;
		}
	}

}
