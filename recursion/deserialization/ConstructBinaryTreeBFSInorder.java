package algorithms.recursion.deserialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data_structures.TreeNode;

public class ConstructBinaryTreeBFSInorder {
	/**
	 * Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
	 * Assumptions
	 * The given sequences are not null and they have the same length
	 * There are no duplicate keys in the binary tree
	 * Examples
	 * levelorder traversal = {5, 3, 8, 1, 4, 11}
	 * inorder traversal = {1, 3, 4, 5, 8, 11}
	 * the corresponding binary tree is
	
		        5
		
		      /    \
		
		    3        8
		
		  /   \        \
		
		1      4        11
	 * */
	public static TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
		Map<Integer, Integer> indexMap = toIndexMap(inOrder);
		List<Integer> level = toList(levelOrder);
		return reconstruct(level, indexMap);
	}
	
	private static TreeNode reconstruct(List<Integer> level, Map<Integer, Integer> indexMap) {
		if (level.isEmpty()) {
			return null;
		}
		
		TreeNode root = new TreeNode(level.remove(0));
		
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();		
		for (Integer x : level) {
			if (indexMap.get(x) < indexMap.get(root.value)) {
				left.add(x);
			} else {
				right.add(x);
			}
		}
		
		root.left = reconstruct(left, indexMap);
		root.right = reconstruct(right, indexMap);
		
		return root;
	}
	
	private static List<Integer> toList(int[] input) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i : input) {
			list.add(i);
		}
		return list;
	}
	
	private static Map<Integer, Integer> toIndexMap(int[] inOrder) {
		Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < inOrder.length; i++) {
			indexMap.put(inOrder[i], i);
		}
		return indexMap;
	}
}
