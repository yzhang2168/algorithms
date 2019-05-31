package recursion.returnvaluebottomup;

import recursion.TreeNode;

public class MaxSumLeafToLeaf {
	public int maxPathSumLeafToLeaf(TreeNode root) {
	      int[] max = new int[]{Integer.MIN_VALUE};
	      helper(root, max);
	      return max[0];
	  }

	  private int helper(TreeNode root, int[] max) {
	      if (root == null) {
	          return Integer.MIN_VALUE;
	      }

	      // root of a subtree
	      int left = helper(root.left, max);
	      int right = helper(root.right, max);

	      // leaf node
	      if (left == Integer.MIN_VALUE && right == Integer.MIN_VALUE) {
	          return root.value;
	      } else if (left == Integer.MIN_VALUE) {
	      // root has 1 child only
	          return root.value + right;
	      } else if (right == Integer.MIN_VALUE) {
	      // root has 1 child only
	    	  return root.value + left;
	      } else {
	      // root has two children
	    	  max[0] = Math.max(max[0], root.value + left + right);
	          return root.value + Math.max(left, right);
	      }
	  }

}
