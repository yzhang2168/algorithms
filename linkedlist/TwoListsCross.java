package algorithms.linked;

import java.security.InvalidParameterException;

import data_structures.ListNode;

public class TwoListsCross {
	// if two lists cross, they share the same tail node. compare tail nodes
	public static boolean areCrossed(ListNode one, ListNode two) {
		if (one == null || two == null) {
			return false;
		}
		
		one = getTail(one);
		two = getTail(two);	
		return one == two;
	}
	
	public static ListNode getTail(ListNode head) {
		if (head == null) {
			return head;
		}
		while (head.next != null) {
			head = head.next;
		}
		return head;
	}
	
	// flush tail nodes, and then compare l1 and l2 to find intersection
	public static ListNode crossingNode(ListNode one, ListNode two) {
		if (one == null || two == null) {
			return null;
		}
		
		int oneCount = getLength(one);
		int twoCount = getLength(two);

		if (oneCount > twoCount) {
			ListNode curr = shift(one, oneCount - twoCount);
			return getIntersect(curr, two);
		} else {
			ListNode curr = shift(two, twoCount - oneCount);
			return getIntersect(one, curr);
		}	
	}	
	
	private static int getLength(ListNode head) {
		int length = 0;
		while (head != null) {
			length++;
			head = head.next;
		}
		return length;		
	}
	
	private static ListNode shift(ListNode head, int n) {
		if (n < 0) {
			throw new InvalidParameterException("n cannot be negative");
		}
		
		while (head != null && n > 0) {
			head = head.next;
			n--;
		}
		// covers head
		return head;
	}
	
	private static ListNode getIntersect(ListNode one, ListNode two) {
		while (one != null && one != two) {
			one = one.next;
			two = two.next;
		}

		// one == null || one == intersection
		return one;
	}
	
	public static void main(String[] args) {
		ListNode node0 = null;
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		
		ListNode node6 = new ListNode(1);
		ListNode node7 = new ListNode(4);
		ListNode node8 = new ListNode(7);
		node6.next = node7;
		node7.next = node8;
		//node8.next = node3;

		util.Util.printLinkedList(node1);
		util.Util.printLinkedList(node6);
		
		System.out.println(areCrossed(node1, node6));
		System.out.println(crossingNode(node1, node6));
	}
}
