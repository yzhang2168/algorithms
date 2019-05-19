package algorithms.linked;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 				10
 * 			/		\
 * 		  6			 15
 * 		/	\	   /	\
 * 	   2     7	 12		18
 * 				/
 * 			  11
 * 
 * */
public class LCAWithParentPointer {

	static class TreeNode {
		private int value;
		private TreeNode parent;
		private TreeNode left;
		private TreeNode right;
		
		public TreeNode(int val) {
			value = val;
			parent = null;
			left = null;
			right = null;
		}
		
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	public LCAWithParentPointer() {
		
	}
	
	public static List<Integer> inOrder(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		inOrder(root, result);
		return result;
	}
	
	private static void inOrder(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		
		inOrder(root.left, result);
		result.add(root.value);
		inOrder(root.right, result);
	}
	
	// assumption: both nodes exist in tree
	public static TreeNode LCA(TreeNode root, TreeNode one, TreeNode two) {
		int oneLevel = getLevel(root, one);
		int twoLevel = getLevel(root, two);
		if (oneLevel > twoLevel) {
			TreeNode curr = shift(one, oneLevel - twoLevel);
			return findIntersection(curr, two);
		} else {
			TreeNode curr = shift(two, twoLevel - oneLevel);
			return findIntersection(one, curr);
		}
	}
	
	private static int getLevel(TreeNode root, TreeNode node) {
		int level = 0;
		while (node != root) {
			node = node.parent;
			level++;
		}
		
		return level;
	}
	
	private static TreeNode shift(TreeNode node, int n) {
		while (n > 0) {
			node = node.parent;
			n--;
		}
		return node;
	}
	
	private static TreeNode findIntersection(TreeNode one, TreeNode two) {
		while (one != null && one != two) {
			one = one.parent;
			two = two.parent;
		}
		return one; 
	}
	
	private static List<List<Integer>> BFS(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);
		List<Integer> currLevel = new ArrayList<Integer>();
		
		while (!queue.isEmpty()) {
			currLevel.clear();
			int size = queue.size();
			while (size > 0) {				
				TreeNode expanded = queue.poll();
				currLevel.add(expanded.value);
				if (expanded.left != null) {
					queue.offer(expanded.left);				
				}
				if (expanded.right != null) {
					queue.offer(expanded.right);			
				}
				size--;
			}
			result.add(new ArrayList<Integer>(currLevel));
		}
		return result;
	}
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(6);
		TreeNode n3 = new TreeNode(15);
		TreeNode n4 = new TreeNode(2);
		TreeNode n5 = new TreeNode(7);
		TreeNode n6 = new TreeNode(12);
		TreeNode n7 = new TreeNode(18);
		TreeNode n8 = new TreeNode(11);
		n1.left = n2;
		n1.right = n3;
		n2.parent = n1;
		n3.parent = n1;
		
		n2.left = n4;
		n2.right = n5;
		n4.parent = n2;
		n5.parent = n2;
		
		n3.left = n6;
		n3.right = n7;
		n6.parent = n3;
		n7.parent = n3;
		
		n6.left = n8;
		n8.parent = n6;
		
		System.out.println("in-order:");
		System.out.println(inOrder(n1));

		System.out.println("level-order:");
		System.out.println(BFS(n1));
		
		System.out.println(LCA(n1, n7, n8));
		System.out.println(LCA(n1, n5, n8));
	}
}
