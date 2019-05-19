package algorithms.linked;

import data_structures.ListNode;
import data_structures.ListNodeGenerics;

public class ReverseIterative<E> {

	
	// 		   1 -> 2 -> 3 -> 4
	// null <- 1    2 -> 3 -> 4
	//      p  c    n
	//         p    c    n
	public static ListNode reverseIterative(ListNode next2) {
		if (next2 == null || next2.next == null) {
			return next2;
		}
		ListNode prev = null;
		ListNode curr = next2;
		ListNode next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	public static void main(String[] args) {
	    ListNodeGenerics<Integer> node0 = null;
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

		ReverseIterative<Integer> test = new ReverseIterative<>();
		util.Util.printLinkedList(test.reverseIterative(node1));
	}
}
