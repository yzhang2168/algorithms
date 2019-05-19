package algorithms.BFS;


import data_structures.BinaryTree;
import data_structures.TreeNode;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {
	/**
	 * Data structure: queue, FIFO, to keep nodes that have not been visited yet
	 * expand a node: visit
	 * generate a node: reach out to its neighbors
	 * snapshot each level's size to print by level 
	 * */
	public static String BreadthFirstSearchPrint(TreeNode root) {
		StringBuilder result = new StringBuilder();
		
		if (root == null) {
			return result.toString();
		}
		
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				TreeNode expanded = q.poll();
				result.append(expanded.value);
				result.append(" ");
				System.out.print(expanded.value + " ");
				if (expanded.left != null) {
					q.offer(expanded.left);
				}
				if (expanded.right != null) {
					q.offer(expanded.right);
				}
			}
			result.append("\n");
			System.out.println();						
		}
		return result.toString();
	}
	
	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(5);
		TreeNode n3 = new TreeNode(15);
		TreeNode n4 = new TreeNode(2);
		TreeNode n5 = new TreeNode(7);
		TreeNode n6 = new TreeNode(12);
		TreeNode n7 = new TreeNode(20);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		System.out.println(BinaryTree.preOrder(n1));
		System.out.println(BreadthFirstSearchPrint(n1));
	}
	
}
