package algorithms.linked;

import java.security.InvalidParameterException;

import data_structures.ListNode;

public class DeleteNthToTheLast {

	/**
	 * nth is 0-based or 1-based? 1-based
	 * n > size?
	 * 1 -> 2 -> 3 -> 4 -> null 
	 *           s    f
	 * delete 4, n = 1
	 * s stops at prev position
	 * 
	 *           f
	 *      s         f
	 * delete 3, n = 2
	 * 
	 * delete 1, n = 4
	 *                      f
	 * s
	 * */
	public static ListNode deleteNth(ListNode head, int n) {
		if (head == null) {
			return head;
		}
		
		if (n < 0) {
			throw new InvalidParameterException("n cannot be negative");
		}
		
		ListNode s = head;
		ListNode f = head;
		while (n > 0 && f != null) {
			f = f.next;
			n--;
		}
		
		while (f != null && f.next != null) {
			f = f.next;
			s = s.next;
		}
		
		if (s == head) {
			return s.next;
		} else {
			// s is at prev of target
			s.next = s.next.next;
			return head;			
		}
		
	}
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		
		util.Util.printLinkedList(node1);
		util.Util.printLinkedList(deleteNth(node1, 2));
		util.Util.printLinkedList(deleteNth(node1, 3));
		util.Util.printLinkedList(deleteNth(deleteNth(node1, 3), 2));
	}
}
