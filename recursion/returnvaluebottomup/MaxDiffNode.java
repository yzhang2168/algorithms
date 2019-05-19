package algorithms.recursion.returnvaluebottomup;

import data_structures.TreeNode;

public class MaxDiffNode {

	public static TreeNode maxDiffNode(TreeNode root) {
		if (root == null) {
			return root;
		}
		
		int[] max = {0};
		TreeNode[] node = new TreeNode[1];
		//TreeNode node = root; // WRONG! node is not returned to parent call, therefore gets lost
		
		maxDiffNode(root, max, node);
		// solution is not returned value, but node
		return node[0];
	}
	
	
	private static int maxDiffNode(TreeNode root, int[] max, TreeNode[] node) {
		// base case
		if (root == null) {
			return 0;
		}
		
		// step 1: want from left and right subtrees
		int leftNodes = maxDiffNode(root.left, max, node);
		int rightNodes = maxDiffNode(root.right, max, node);
		
		// step 2: logic in current level
		int diff = Math.abs(leftNodes - rightNodes);
		if (diff > max[0]) {
			max[0] = diff;
			node[0] = root;
		}
		
		// step 3: what to return to parent: # nodes 
		return 1 + leftNodes + rightNodes;
	}
	
	
	public static void main(String[] args) {
	/*
		  -15
		  /    \
		2      11
	   /     /   
	  1	    6     
		The maximum path sum is 6 + 11 + 14 = 31.

     * 		  -1
     * 		/	\
     * 	  -2    -6
     * 	 /  \
     * -3   -4
     * / 	/
     *-7  -5  
	 */	
		TreeNode n11 = new TreeNode(-15);
		TreeNode n12 = new TreeNode(2);
		TreeNode n13 = new TreeNode(11);
		TreeNode n14 = new TreeNode(6);
		TreeNode n15 = new TreeNode(14);
		TreeNode n16 = new TreeNode(1);
		n11.left = n12;
		n11.right = n13;
		n13.left = n14;
		n12.left = n16;
		//n13.right = n15;
		
		TreeNode n21 = new TreeNode(-1);
		TreeNode n22 = new TreeNode(-2);
		TreeNode n23 = new TreeNode(-6);
		TreeNode n24 = new TreeNode(-3);
		TreeNode n25 = new TreeNode(-4);
		TreeNode n26 = new TreeNode(-7);
		TreeNode n27 = new TreeNode(-5);
		n21.left = n22;
		n21.right = n23;
		n22.left = n24;
		n22.right = n25;
		n24.left = n26;
		n25.left = n27;
		System.out.println(maxDiffNode(n11).value);
		System.out.println(maxDiffNode(n21).value);

	}
}
