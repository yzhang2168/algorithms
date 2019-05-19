package algorithms.recursion.deserialization;

import data_structures.TreeNode;

public class ConstructBSTPost {

	public static TreeNode construct(int[] postOrder) {
		int[] index = new int[] {postOrder.length - 1};
		return helper(postOrder, index, Integer.MIN_VALUE);
	}
	
	public static TreeNode helper(int[] post, int[] index, int min) {
		if (index[0] < 0 || post[index[0]] <= min) {
			return null;
		}
		
		TreeNode root = new TreeNode(post[index[0]--]);
		root.right = helper(post, index, root.value);
		root.left = helper(post, index, min);
		
		return root;
	}
	
	public static void main(String[] args) {
		int[] post = {1, 4, 3, 11, 8, 7};
		/*
		int[] index = new int[] {post.length - 1};
		TreeNode root1 = new TreeNode(post[index[0]]--);
		System.out.println(root1.value);
		System.out.println(post[index[0]]);

		TreeNode root2 = new TreeNode(post[index[0]]--);
		System.out.println(root2.value);
		System.out.println(post[index[0]]);
		*/
		construct(post);
		System.out.println((int) '#');
		System.out.println((char) 32);
	}
}
