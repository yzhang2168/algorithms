package algorithms.linked;

import data_structures.ListNode;

public class Palindrome {
	/**
	 * mirrored
	 * findmid: O(n)
	 * reverse: O(n) time O(1) iterative; O(n) time O(n) recursive
	 * */
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		
		ListNode leftMid = findMid(head);
		ListNode list2 = leftMid.next;
		leftMid.next = null;
		list2 = reverse(list2);
		while(head != null && list2 != null) {
			if (head.value != list2.value) {
				return false;
			}
			head = head.next;
			list2 = list2.next;
		}
		return true;
	}
	
	public static ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	// 		   1 -> 2 -> 3 -> 4
	// null <- 1    2 -> 3 -> 4
	//      p  c    n
	//         p    c    n
	public static ListNode reverseIterative(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode curr = head;
		ListNode next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	// 1 -> 2 -> 3 -> 4
	//      s
	//           f
	// 1 -> 2 
	// s
	// f
	public static ListNode findMid(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode s = head;
		ListNode f = head;
		//while (f != null && f.next != null) { // right mid
		while (f.next != null && f.next.next != null) { // left mid
			f = f.next;
			f = f.next;
			s = s.next;
		}
		return s;
	}
	
	public static void main(String[] args) {
		ListNode node0 = null;
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(5);
		ListNode node5 = new ListNode(6);
		ListNode node6 = new ListNode(1);
		ListNode node7 = new ListNode(9);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		util.Util.printLinkedList(node1);

		System.out.println(findMid(node1).value);
		util.Util.printLinkedList(reverseIterative(node1));
		System.out.println(isPalindrome(node6));
	}
}
