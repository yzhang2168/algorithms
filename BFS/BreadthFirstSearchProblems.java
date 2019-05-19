package algorithms.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import algorithms.GraphNode;
import data_structures.TreeNode;

public class BreadthFirstSearchProblems {
	/**
	 * Get the list of list of keys in a given binary tree layer by layer. 
	 * Each layer is represented by a list of keys and the keys are traversed from left to right.
	 * Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

the result is [ [5], [3, 8], [1, 4, 11] ]

Corner Cases
What if the binary tree is null? Return an empty list of list in this case.
	 * */

	public static List<List<Integer>> layerByLayer(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null) {
			return result;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while(!q.isEmpty()) {
			int size = q.size();
			List<Integer> currLayer = new ArrayList<Integer>(); 
			for (int i = 0; i < size; i++) {
				TreeNode polledNode = q.poll();
				currLayer.add(polledNode.value);
				if (polledNode.left != null) {
					q.offer(polledNode.left);
				}
				if (polledNode.right != null) {
					q.offer(polledNode.right);	
				}							
			}
			result.add(currLayer);
		}
		return result;
	}

	/*
	 * Check if a given binary tree is completed. 
	 * A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. 
	 * Furthermore, all nodes are as far left as possible.
	 * Corner Cases
	 * What if the binary tree is null? Return true in this case.
	 * */
	public static boolean isCompleteBinaryTree(TreeNode root) {
		if (root == null) {
			return true;
		}

		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		boolean nullFound = false; 

		while (!q.isEmpty()) {
			TreeNode polledNode = q.poll();
			if (nullFound && polledNode.left != null) {
				return false;
			} else if (polledNode.left != null) {
				q.offer(polledNode.left);					
			} else {
				nullFound = true;
			}

			if (nullFound && polledNode.right != null) {
				return false;
			} else if (polledNode.right != null) {
				q.offer(polledNode.right);	
			} else {	
				nullFound = true;
			}
		}
		return true;
	}
}
