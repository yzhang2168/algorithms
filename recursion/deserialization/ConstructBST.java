package algorithms.recursion.deserialization;

import java.util.ArrayList;
import java.util.List;

import data_structures.BinaryTreeTraversal;
import data_structures.TreeNode;

/**
 * Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.
 * Assumptions
 * The given sequence is not null
 * There are no duplicate keys in the binary search tree
 * Examples
 * postorder traversal = {1, 4, 3, 11, 8, 5}
 * the corresponding binary search tree is
	
	        5
	
	      /    \
	
	    3        8
	
	  /   \        \
	
	1      4        11
 * 
 * 
 * */
public class ConstructBST {

	public static TreeNode constructBSTPost(int[] postOrder) {
		List<Integer> post = toList(postOrder);
		return constructBSTPost(post);
	}	

	private static TreeNode constructBSTPost(List<Integer> post) {
		if (post.isEmpty()) {
			return null;
		}
		
		TreeNode root = new TreeNode(post.remove(post.size() - 1));
		
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();	
		for (Integer x : post) {
			if (x < root.value) {
				left.add(x);
			} else {
				right.add(x);
			}
		}
		
		root.left = constructBSTPost(left);
		root.right = constructBSTPost(right);
		
		return root;		
	}
	
	public static TreeNode constructBSTPre(int[] preOrder) {
		List<Integer> pre = toList(preOrder);
		return constructBSTPre(pre);
	}	

	private static TreeNode constructBSTPre(List<Integer> pre) {
		if (pre.isEmpty()) {
			return null;
		}
		
		TreeNode root = new TreeNode(pre.remove(0));
		
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();	
		for (Integer x : pre) {
			if (x < root.value) {
				left.add(x);
			} else {
				right.add(x);
			}
		}
		
		root.left = constructBSTPre(left);
		root.right = constructBSTPre(right);
		
		return root;		
	}
	
	
	public static TreeNode constructBSTLevel(int[] levelOrder) {
		List<Integer> level = toList(levelOrder);
		return constructBSTLevel(level);
	}	

	private static TreeNode constructBSTLevel(List<Integer> level) {
		if (level.isEmpty()) {
			return null;
		}
		
		TreeNode root = new TreeNode(level.remove(0));
		
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();	
		for (Integer x : level) {
			if (x < root.value) {
				left.add(x);
			} else {
				right.add(x);
			}
		}
		
		root.left = constructBSTLevel(left);
		root.right = constructBSTLevel(right);
		
		return root;		
	}

	
	private static List<Integer> toList(int[] input) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i : input) {
			list.add(i);
		}
		return list;
	}
	
	
	public static void main(String[] args) {
		int[] post = {1, 4, 3, 11, 8, 5};
		int[] pre = {5, 3, 1, 4, 8, 11};
		int[] level = {5, 3, 8, 1, 4, 11};
		
		TreeNode root = constructBSTPost(post);
		System.out.println("root = " + root.value);
		BinaryTreeTraversal.inOrder(root);
		System.out.println();
		
		TreeNode root2 = constructBSTPre(pre);
		System.out.println("root = " + root2.value);		
		BinaryTreeTraversal.inOrder(root2);
		System.out.println();
		
		TreeNode root3 = constructBSTLevel(level);
		System.out.println("root = " + root3.value);		
		BinaryTreeTraversal.inOrder(root3);
	}
}
