package algorithms.recursion.straightpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import data_structures.TreeNode;

public class MaxSumStraightPathProblems {

	/**
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
	 * */

	public static int maxSumAnyPath(TreeNode root) {
		// base case
		if (root == null){
			return 0;
		}

		int[] maxSum = {Integer.MIN_VALUE};
		TreeNode[] top = new TreeNode[1];
		maxSumAnyPath(root, maxSum, top);
		System.out.println("maxSum[0] = " + maxSum[0]);
		//return maxSum[0];
		System.out.println("top[0].value = " + top[0].value);
		return top[0].value;
	}

	public static int maxSumAnyPath(TreeNode root, int[] maxSum, TreeNode[] top) {
		// base case
		if (root == null){
			return 0;
		}

		// step 1: want from left and right subtrees
		//maxSum from a single path / \
		int leftMaxSum = maxSumAnyPath(root.left, maxSum, top);
		int rightMaxSum = maxSumAnyPath(root.right, maxSum, top);

		// step 2: what to do on the curr level
		//maxSum for curr path topped by root /\
		leftMaxSum = leftMaxSum > 0 ? leftMaxSum : 0;
		rightMaxSum = rightMaxSum > 0 ? rightMaxSum : 0;
		int currSum = root.value + leftMaxSum + rightMaxSum;
		if (currSum > maxSum[0]) {
			maxSum[0] = currSum;
			top[0] = root;
		}

		// step 3: what to return to parent call
		//maxSum from a single path + root.value / or \
		return root.value + Math.max(leftMaxSum, rightMaxSum);
	}

	
	public static int maxSumLeafToRoot(TreeNode root){
		int[] maxSum = {Integer.MIN_VALUE};
		int sum = 0;
		helper(root, sum, maxSum);
		return maxSum[0];

	}

	private static void helper(TreeNode root, int sum, int[] maxSum){
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null){
			sum += root.value;
			maxSum[0] = Math.max(maxSum[0], sum);
			return;
		}

		sum += root.value;
		helper(root.left, sum, maxSum);
		helper(root.right, sum, maxSum);
	}


	public static List<TreeNode> maxSumLeafToRoot0(TreeNode root){
	//public static int maxSumLeafToRoot0(TreeNode root){
		int[] maxSum = {Integer.MIN_VALUE};
		List<TreeNode> prefixPath = new ArrayList<TreeNode>();
		List<List<TreeNode>> maxPathNodes = new ArrayList<List<TreeNode>>();		
		maxSumLeafToRoot0(root, maxSum, maxPathNodes, prefixPath);

		//return maxSum[0];
		return maxPathNodes.get(0);
	}

	public static void maxSumLeafToRoot0(TreeNode root, int[] maxSum, List<List<TreeNode>> maxPathNodes, List<TreeNode> prefixPath){
		// base
		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null){
			prefixPath.add(root);
			int currPathSum = sum(prefixPath);

			if (currPathSum > maxSum[0]){
				maxSum[0] = currPathSum;
				maxPathNodes.clear();
				maxPathNodes.add(new ArrayList<TreeNode>(prefixPath)); // <----------------------
				// deep copy, a new object. If using a List<TreeNode> = new... Then the object is lost after this call returns to parent
			}

			prefixPath.remove(prefixPath.size() - 1);
			return;
		}

		prefixPath.add(root);
		maxSumLeafToRoot0(root.left, maxSum, maxPathNodes, prefixPath);
		maxSumLeafToRoot0(root.right, maxSum, maxPathNodes, prefixPath);
		prefixPath.remove(prefixPath.size() - 1);
	}

	private static int sum(List<TreeNode> nodes) {
		int sum = 0;
		for (TreeNode node : nodes) {
			sum += node.value;
		}
		return sum;
	}

	public static int maxSumStraightPath(TreeNode root) {
		int[] maxSum = {Integer.MIN_VALUE};
		if (root == null){
			return maxSum[0];
		}

		maxSumStraightPath(root, maxSum);
		return maxSum[0];
	}

	public static int maxSumStraightPath(TreeNode root, int[] maxSum) {
		// base case
		if (root == null){
			return 0;
		}

		// step 1: want from left and right subtrees
		int leftMaxSum = maxSumStraightPath(root.left, maxSum);
		int rightMaxSum = maxSumStraightPath(root.right, maxSum);

		// step 2: what to do on the curr level
		int curr = Math.max(Math.max(leftMaxSum, rightMaxSum), 0) + root.value;
		maxSum[0] = Math.max(maxSum[0], curr);

		// step 3: what to return to parent call
		return curr;
	}


	public void maxSumLeafToRoot(TreeNode root, int prefixSum, int[] globalMax) {
		if (root == null) {
			return;
		}

		// base case: leaf
		if (root.left == null || root.right == null) {
			prefixSum += root.value;
			globalMax[0] = Math.max(globalMax[0], prefixSum);
			return;
		}

		prefixSum += root.value;
		maxSumLeafToRoot(root.left, prefixSum, globalMax);
		maxSumLeafToRoot(root.left, prefixSum, globalMax);
	}


	public void maxSumStraightPath(TreeNode root, int prefixSum, int[] globalMax) {
		if (root == null) {
			return;
		}

		if (prefixSum <= 0) {
			prefixSum = root.value;
		} else {
			prefixSum += root.value;
		}
		globalMax[0] = Math.max(globalMax[0], prefixSum);

		maxSumStraightPath(root.left, prefixSum, globalMax);
		maxSumStraightPath(root.right, prefixSum, globalMax);		
	}


	public static boolean sumEqualsToTarget(TreeNode root, int target) {
		if (root == null) {
			return false;
		}

		int currSum = 0;
		HashSet<Integer> prefixSums = new HashSet<Integer>();
		// add 0 to cover prefix == target (prefix - target == 0)
		prefixSums.add(0);
		return sumEqualsToTarget(root, target, currSum, prefixSums);
	}

	public static boolean sumEqualsToTarget(TreeNode root, int target, int currSum, HashSet<Integer> prefixSums) {

		if (root == null) {
			return false;
		}

		currSum += root.value;
		if (prefixSums.contains(currSum - target)) {
			return true;
		} else {
			boolean needRemoval = prefixSums.add(currSum);

			if (sumEqualsToTarget(root.left, target, currSum, prefixSums)) {
				return true;
			} else if (sumEqualsToTarget(root.right, target, currSum, prefixSums)) {
				return true;
			} else {
				if (needRemoval) {
					prefixSums.remove(currSum);
				}
				return false;		
			}
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

		 * 		  3
		 * 		/  \
		 * 	  -8	9
		 * 	 /	   / \
		 *  8     2  -5
		 * / \
		 *1  -2
		 */	
		TreeNode n0 = new TreeNode(3);
		TreeNode n1 = new TreeNode(-8);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(8);
		TreeNode n4 = new TreeNode(10);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(-5);
		TreeNode n7 = new TreeNode(1);
		TreeNode n8 = new TreeNode(-2);
		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		//n1.right = n4;
		n2.left = n5;
		n2.right = n6;
		n3.left = n7;
		n3.right = n8;

		/*
		 * 		  -5
		 * 		/	\
		 * 	  -2	-11
		 * 	 /   	/ \
		 * -4      -1 -3
		 * 
		 */
		TreeNode n11 = new TreeNode(-5);
		TreeNode n12 = new TreeNode(-2);
		TreeNode n13 = new TreeNode(-11);
		TreeNode n14 = new TreeNode(-4);
		TreeNode n15 = new TreeNode(-1);
		TreeNode n16 = new TreeNode(-3);
		n11.left = n12;
		n11.right = n13;
		n12.left = n14;
		n13.left = n15;
		n13.right = n16;

		/*
		 * 		  -1
		 * 		  / \
		 * 		-2 	-6
		 * 		/ \
		 *    -3  -4
		 *    /   /
		 *   -7  -5
		 */
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
		
		List<TreeNode> maxPathNodes = maxSumLeafToRoot0(n0);
		for (TreeNode node : maxPathNodes) {
			System.out.print(node.value + ", ");			
		}
		System.out.println();

		List<TreeNode> maxPathNodes1 = maxSumLeafToRoot0(n11);
		for (TreeNode node : maxPathNodes1) {
			System.out.print(node.value + ", ");			
		}
		System.out.println();

		List<TreeNode> maxPathNodes2 = maxSumLeafToRoot0(n21);
		for (TreeNode node : maxPathNodes2) {
			System.out.print(node.value + ", ");			
		}
		//System.out.println(maxSumLeafToRoot0(n0));  // 14
		//System.out.println(maxSumLeafToRoot0(n11)); // -11
		//System.out.println(maxSumLeafToRoot0(n21)); // -7
		System.out.println();

		System.out.println(maxSumLeafToRoot(n0));  // 14
		System.out.println(maxSumLeafToRoot(n11)); // -11
		System.out.println(maxSumLeafToRoot(n21)); // -7
		System.out.println();

		System.out.println(maxSumStraightPath(n0));  // 14
		System.out.println(maxSumStraightPath(n11)); // -1
		System.out.println(maxSumStraightPath(n21)); // -1
		System.out.println();

		System.out.println(maxSumAnyPath(n0));  // 15
		System.out.println(maxSumAnyPath(n11)); // -1
		System.out.println(maxSumAnyPath(n21)); // -1
		System.out.println();

		System.out.println(sumEqualsToTarget(n0, 27));
		System.out.println(sumEqualsToTarget(n0, 7));

	}
}
