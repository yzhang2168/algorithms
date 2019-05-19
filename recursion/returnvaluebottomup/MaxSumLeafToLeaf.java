package algorithms.recursion.returnvaluebottomup;

import data_structures.TreeNode;

/**
 * Given a binary tree in which each node contains an integer number. 
 * Find the maximum possible sum from one leaf node to another leaf node. 
 * If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
 * Examples
  -15
  /    \
2      11
     /    \
    6     14
The maximum path sum is 6 + 11 + 14 = 31.
 * 
 * */
public class MaxSumLeafToLeaf {

	public static int maxSumLeafToLeaf(TreeNode root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}

		int[] maxSum = new int[]{Integer.MIN_VALUE};
		maxSumLeafToLeafWrong(root, maxSum);
		return maxSum[0];
	}

	private static int maxSumLeafToLeaf(TreeNode root, int[] maxSum) {
		// base
		if (root == null) {
			return 0;
		}

		// step 1: want from left and right subtrees
		// max single path sum - straight path / and \
		int leftSum = maxSumLeafToLeaf(root.left, maxSum);
		int rightSum = maxSumLeafToLeaf(root.right, maxSum);

		// step 2: logic at curr level
		// calculate max sum with curr node at the top /\
		// need to include both left / and right \ such that the path is from leaf to leaf
		if (root.left != null && root.right != null) {
			int currSum = root.value + leftSum + rightSum;
			maxSum[0] = Math.max(maxSum[0], currSum);
			
			// step 3: what to return to parent call
			// the larger sum [leaf...curr node]
			return Math.max(leftSum, rightSum) + root.value;
		} else {
			return root.left != null ? root.value + leftSum : root.value + rightSum; 
		}
	}


	private static int maxSumLeafToLeafWrong(TreeNode root, int[] maxSum) {
		// base
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return root.value;
		} 

		// step 1: want from left and right subtrees
		// max single path sum - straight path / and \
		int leftSum = maxSumLeafToLeaf(root.left, maxSum);
		int rightSum = maxSumLeafToLeaf(root.right, maxSum);

		// step 2: logic at curr level
		// calculate max sum with curr node at the top /\
		// need to include both left / and right \ such that the path is from leaf to leaf
		if (root.left != null && root.right != null) {
			int currSum = root.value + leftSum + rightSum;
			maxSum[0] = Math.max(maxSum[0], currSum);

			// step 3: what to return to parent call
			// the larger sum [leaf...curr node]
			return Math.max(leftSum, rightSum) + root.value;
		} else if (root.left == null) {
			return root.value + maxSumLeafToLeaf(root.right, maxSum);
		} else {//if (root.right == null) {
			return root.value + maxSumLeafToLeaf(root.left, maxSum);
		}
	}


	public static void main(String[] args) {
		/*
		  -15
		  /    \
		2      11
		     /    \
		    6     14
		The maximum path sum is 6 + 11 + 14 = 31.

		 * 		  -1
		 * 		/	\
		 * 	  -2    -6
		 * 	 /  \
		 * -3   -4
		 * / 	/
		 *-7  -5  
		 *The maximum path sum is 6 + 11 + 14 = -18.
		 */	
		TreeNode n0 = new TreeNode(3);
		TreeNode n1 = new TreeNode(-8);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(4);
		TreeNode n4 = new TreeNode(10);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(-5);
		TreeNode n7 = new TreeNode(1);
		TreeNode n8 = new TreeNode(-2);
		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		n2.right = n6;
		n3.left = n7;
		n3.right = n8;


		TreeNode n11 = new TreeNode(-15);
		TreeNode n12 = new TreeNode(2);
		TreeNode n13 = new TreeNode(11);
		TreeNode n14 = new TreeNode(6);
		TreeNode n15 = new TreeNode(14);
		n11.left = n12;
		n11.right = n13;
		n13.left = n14;
		n13.right = n15;

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
		System.out.println(maxSumLeafToLeaf(n0));
		System.out.println(maxSumLeafToLeaf(n1));
		System.out.println(maxSumLeafToLeaf(n11));
		System.out.println(maxSumLeafToLeaf(n21));

	}
}
