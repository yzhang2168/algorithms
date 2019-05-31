package recursion.straightpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import recursion.TreeNode;

/**
 * any node to any node
 * the two nodes can be the same node
 * they can only be on the same path from root to one of the leaf nodes 
 * the sum of the numbers on the path == target number
 * 
 * Examples
	    5

	  /    \

	2      11

	     /    \

	    6     14

	  /

	 3

	If target = 17, There exists a path 11 + 6, the sum of the path is target.	
	If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
	If target = 10, There does not exist any paths sum of which is target.
	If target = 11, There exists a path only containing the node 11.
 * =========================================
 * solution 0:
 * traverse all nodes O(n)
 * for each node, linear scan and look back at all nodes till root and calculate all sums O(height)
 * 	for (node x...root) {
 * 		pathPrefix += node 
 *  }
 * total O(n ^2)
 * =========================================
 * solution 1:
 * optimize the inner for()
 * preorder + DP
 * prefixSum [-5, 6, 12, 22]
 * prefixSum[i]: sum(root ... i)
 * target 17
 * 
 * preorder + carry a prefix
 * 
 * 1. max sum path from a leaf to root
 * base case: leaf node
 * recursion rule: prefixSum += root.value
 * 
 * 3. max sum path from any node to any node on the same straight path
 * base case: null
 * recursion rule: DP from curr to root 
 * 	lastSum is largest sum from curr to any node on the straight path 
 * 	if (lastSum <= 0), lastSum = root.value
 * 	else, lastSum += root.value
 * 
 * 2. if there exists a path from any node to any node on the same straight path
 * that has a sum == target, then current prefixSum - target is one of the saved prefixSums
 * for each node, instead of using a for (), maintain a path prefixSum [root...curr]
 * data structure:
 * 		HashSet<Integer> prefixSums: all path prefix sum from root node to current node
 * 		
 * 					   total prefixSum = 12
 * 										 |
 * 										 v			   
 * 	root__________________________________ curr
 * 		xxxxxxxxxxxxxx xxxxxxxxxxxxxxxxxxx
 * 		prefixSum1 = x	target section = 17
 * 		
 * 		x + 17 = 12	
 * 
 * time : O(n)
 * space: O(height)							
 * */
public class PathSumEqualsToTargetII {

	public static boolean sumEqualsToTarget(TreeNode root, int target) {
		if (root == null) {
			return false;
		}

		int currSum = 0;
		HashSet<Integer> prefixSum = new HashSet<Integer>();
		prefixSum.add(0); // to check if currSum == target -> currSum - target == 0
		return sumEqualsToTarget(root, target, prefixSum, currSum);
	}

	private static boolean sumEqualsToTarget(TreeNode root, int target, HashSet<Integer> prefixSum, int currSum) {
		if (root == null) {
			return false;
		}

		currSum += root.value;
		boolean needRemoval = prefixSum.add(currSum);

		// if (currSum == target || prefixSum.contains(currSum - target)) // if no 0 in prefixSum set 
		if (prefixSum.contains(currSum - target)) {
			return true;
		} else if (sumEqualsToTarget(root.left, target, prefixSum, currSum) ||
				sumEqualsToTarget(root.right, target, prefixSum, currSum)) {
			return true;
		} else {
			if (needRemoval) {
				prefixSum.remove(currSum);				
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		/*
		TreeNode n0 = new TreeNode(-5);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(11);
		TreeNode n3 = new TreeNode(6);
		TreeNode n4 = new TreeNode(14);
		TreeNode n5 = new TreeNode(8);
		TreeNode n6 = new TreeNode(10);
		n0.left = n1;
		n0.right = n2;
		n2.left = n3;
		n2.right = n4;
		n3.left = n5;
		n3.right = n6;
		
		The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
    1
  /   \
 2     3
      /
    4
    
    
     * 		  3
     * 		/	\
     * 	  -8	9
     * 	 /\		/\
     *  4 10   2 -5
     *  /\
     * 1 -2  
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
		
		System.out.println(sumEqualsToTarget(n0, 5)); // true
		System.out.println(sumEqualsToTarget(n0, 7)); // true
		System.out.println(sumEqualsToTarget(n0, 12)); // true
		System.out.println(sumEqualsToTarget(n0, 10)); // true
		System.out.println(sumEqualsToTarget(n0, 27)); // false

	}

}
