package algorithms.linked;


import data_structures.ListNode;

/**
 *   1 -> 2 -> 3 -> 4 -> 5
p    c    n    t
     p    c    n    t
 * 	 2 -> 1 -> 4 -> 3 -> 5
 * 
 * 
 * */
public class ReverseInPairs {

	public ListNode reverseInPairsRecursion(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode next = head.next;
		ListNode newHead = reverseInPairsRecursion(next.next);
		next.next = head;
		head.next = newHead;
		return next;
	}

	public static ListNode reverseInPairsIterative(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		ListNode curr = head;
		prev.next = curr;	
		ListNode next = null;
		ListNode temp = null;
		// switch curr <-> next, curr and next are not null 

		while (curr != null && curr.next != null) {
			next = curr.next;
			temp = next.next; // could be null
			prev.next = next;
			next.next = curr;
			curr.next = temp;

			prev = curr;
			curr = temp;
		}

		/*
		while (curr != null && curr.next != null) {
			prev.next = curr.next;
			curr.next = curr.next.next;
			prev.next.next = curr;

			prev = curr;
			curr = curr.next;
		}
		 */

		return dummy.next;
	}

	public ListNode reverseInPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode newHead = head.next;
		ListNode prev = null;
		ListNode curr = head;
		ListNode next = null;
		ListNode temp = null;    

		while (curr != null && curr.next != null) {
			next = curr.next;
			temp = next.next;
			if (prev != null) {
				prev.next = next;
			}
			curr.next = temp;
			next.next = curr;
			prev = curr;
			curr = temp;
		}
		return newHead;
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

		//util.Util.printLinkedList(reverseInPairsRecursion(node1));
		util.Util.printLinkedList(reverseInPairsIterative(node1));
	}
}
