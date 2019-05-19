package algorithms.linked;

import data_structures.ListNode;
import data_structures.ListNodeGenerics;

/**
 * Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null to be 
 * N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
 * Examples
 * L = null, is reordered to null
 * L = 1 -> null, is reordered to 1 -> null
 * L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
 * L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
 * */
public class Reorder {

	public static ListNode reorder(ListNode n1) {
		if (n1 == null || n1.next == null) {
			return n1;
		}
		
		ListNode leftMid = FindMid.findMid(n1);
		ListNode list2 = ReverseIterative.reverseIterative(leftMid.next);
		// !!! de-link the 2nd half
		leftMid.next = null;
		
		return Interleave.interleave(n1, list2);
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		n1.next = new ListNode(2);
		n1.next.next = new ListNode(3);
		n1.next.next.next = new ListNode(4);
		n1.next.next.next.next = new ListNode(5);
		util.Util.printLinkedList(n1);

		util.Util.printLinkedList(reorder(n1));
	}
	
		
}
