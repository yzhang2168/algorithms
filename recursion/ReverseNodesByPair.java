package algorithms.recursion;

import data_structures.ListNode;

/**
 * input:  1 -> 2 -> || 3 -> 4 -> 5 -> null
 * output: 2 -> 1 -> || 4 -> 4 -> 5 -> null
 * */
public class ReverseNodesByPair {

	public static ListNode reverseNodesByPair(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode next = head.next;
		head.next = reverseNodesByPair(head.next.next);
		next.next = head;
		return next; 
	}
	
	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		ListNode head = n1;
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();

		head = reverseNodesByPair(n1);
		while (head != null) {
			System.out.print(head.value + " ");
			head = head.next;
		}
		
	}
}
