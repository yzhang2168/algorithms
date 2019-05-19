package algorithms.linked;

import data_structures.ListNode;

/**
 * delete the nth (0-based) element counting from the last one 1 -> 2 -> 3 -> 4
 * -> null s f
 * 
 * n = 3, delete 1/head n = 0, delete 4/tail s f
 */
public class DeleteNth {

	public static ListNode deleteNth(ListNode head, int n) {
		if (head == null) {
			return head;
		}

		// use dummy to handle cases when head needs to be deleted
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode slow = dummy;
		ListNode fast = dummy;

		// move fast n + 1 steps
		while (n >= 0) {
			fast = fast.next;
			n--;
		}

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		slow.next = slow.next.next;
		return dummy.next;
	}

	public static ListNode deleteNthII(ListNode head, int n) {
		if (head == null) {
			return head;
		}

		ListNode slow = head;
		ListNode fast = head;
		// move n + 1 steps
		while (n >= 0) {
			fast = fast.next;
			n--;
		}

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}

		if (slow == head) {
			return head.next;
		} else {
			slow.next = slow.next.next;
			return head;
		}
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

		util.Util.printLinkedList(deleteNth(node1, 0));
		util.Util.printLinkedList(deleteNth(node1, 5));
	}
}
