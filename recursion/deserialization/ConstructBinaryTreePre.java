package algorithms.recursion.deserialization;

import java.util.HashMap;
import java.util.Map;

import data_structures.TreeNode;

/**
 * inorder --x--> tree
		2			
	1		3		
	
	1
		2	
			3
 * preorder --x--> tree
 * preorder + postorder --x--> tree
 * preorder + inorder / inorder + postorder: YES!
 * 
 * Given the preorder and inorder traversal sequence of a binary tree, 
 * reconstruct the original tree.
 * Assumptions
 * The given sequences are not null and they have the same length
 * There are no duplicate keys in the binary tree
 * Examples
 * preorder traversal = {5, 3, 1, 4, 8, 11}
 * inorder traversal = {1, 3, 4, 5, 8, 11}
 * the corresponding binary tree is
	        5
	
	      /    \
	
	    3        8
	
	  /   \        \
	
	1      4        11
 * 
 * general approach: divide-and-conquer
 * tree + recursion: know root, 
 * 1. get root first
 * 2. divide the problem into 2:
 *    construct(root.left) -> root.left root, construct(root.right) --> root.right root
 * 3. combine:
 * 
 * preorder: root | left subtree | right subtree
 * 
 * in-order: left subtree | root | right subtree 
 * 			
 * 			 leftsize = 
 * */
public class ConstructBinaryTreePre {

	public static TreeNode construct(int[] inorder, int[] preorder) {
		Map<Integer, Integer> indexMap = toIndexMap(inorder);
		return construct(inorder, 0, inorder.length - 1, 
						 preorder, 0, preorder.length - 1, indexMap);
	}
	
	public static TreeNode construct(int[] inorder, int inLeft, int inRight,
							int[] preorder, int preLeft, int preRight,
							Map<Integer, Integer> indexMap) {
		
		if (inLeft > inRight) {
			return null;
		}
		
		TreeNode root = new TreeNode(preorder[preLeft]);
		int leftSize = indexMap.get(root.value) - inLeft;
		
		root.left = construct(inorder, inLeft, inLeft + leftSize - 1, 
							  preorder, preLeft + 1, preLeft + leftSize, indexMap);
		root.right = construct(inorder, inLeft + leftSize + 1, inRight, 
							  preorder, preLeft + leftSize + 1, preRight, indexMap);
		return root;
	}
	
	private static Map<Integer, Integer> toIndexMap(int[] inorder) {
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inorder.length; i++) {
			indexMap.put(inorder[i], i);
		}
		return indexMap;
	}
	
}
