package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import tree.TreeNode;

public class ZigZagOrder {
	/**
	 * Get the list of keys in a given binary tree layer by layer in zig-zag order.

		Examples
		
		        5
		
		      /    \
		
		    3        8
		
		  /   \        \
		
		 1     4        11
		
		the result is [5, 3, 8, 11, 4, 1]
	 * */
	public List<Integer> zigZag(TreeNode<Integer> root) {
	    List<Integer> result = new ArrayList<Integer>();
	    if (root == null) {
	      return result;
	    }

	    Deque<TreeNode<Integer>> deque = new ArrayDeque<>();    
	    deque.offer(root);
	    int level = 0;
	    while (!deque.isEmpty()) {
	      int size = deque.size();
	      for (int i = 0; i < size; i++) {
	        if (level % 2 == 0) {
	          TreeNode<Integer> curr = deque.pollLast();
	          result.add((Integer) curr.value);
	          if (curr.right != null) {
	            deque.offerFirst(curr.right);
	          }

	          if (curr.left != null) {
	            deque.offerFirst(curr.left);
	          }
	        } else {
	          TreeNode<Integer> curr = deque.pollFirst();
	          result.add((Integer) curr.value);
	          if (curr.left != null) {
	            deque.offerLast(curr.left);
	          }

	          if (curr.right != null) {
	            deque.offerLast(curr.right);
	          }
	        }
	      }
	      level++;
	    }

	    return result;
	  }
}
