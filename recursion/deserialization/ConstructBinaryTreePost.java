package algorithms.recursion.deserialization;

import java.util.HashMap;
import java.util.Map;

import data_structures.BinaryTreeTraversal;
import data_structures.TreeNode;

public class ConstructBinaryTreePost {
	/**
	 * Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
	 * Assumptions
	 * The given sequences are not null and they have the same length
	 * There are no duplicate keys in the binary tree
	 * Examples
	 * postorder traversal = {1, 4, 3, 11, 8, 5}
	 * inorder traversal = {1, 3, 4, 5, 8, 11}
	 * the corresponding binary tree is

	        5
	      /    \
	    3        8
	  /   \        \
	1      4        11
	 * 
	 * 
	 * */
	public static TreeNode reconstruct(int[] inOrder, int[] postOrder) {
		Map<Integer, Integer> indexMap = toIndexMap(inOrder);
		return reconstruct(inOrder, 0, inOrder.length - 1,
						 postOrder, 0, postOrder.length - 1, indexMap);
	}
	
	private static TreeNode reconstruct(int[] inOrder, int inLeft, int inRight,
										int[] postOrder, int postLeft, int postRight,
										Map<Integer, Integer> indexMap) {
		if (inLeft > inRight) {
			return null;
		}
		
		TreeNode root = new TreeNode(postOrder[postRight]);
		int leftSize = indexMap.get(root.value) - inLeft;
		
		root.left = reconstruct(inOrder, inLeft, inLeft + leftSize - 1,
								postOrder, postLeft, postLeft + leftSize - 1,
								indexMap);
		root.right = reconstruct(inOrder, inLeft + leftSize + 1, inRight,
								postOrder, postLeft + leftSize, postRight - 1,
								indexMap);
		
		return root;
	}
	
	private static Map<Integer, Integer> toIndexMap(int[] inOrder) {
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inOrder.length; i++) {
			indexMap.put(inOrder[i], i);
		}
		return indexMap;
	}
	
	
	public static void main(String[] args) {
		TreeNode root = reconstruct(new int[]{1,6,5,7,4,10,9}, new int[]{6,7,5,1,9,10,4});
		System.out.println("root = " + root.value);
		BinaryTreeTraversal.inOrder(root);
	}
}
