package algorithms.linked;

import data_structures.ListNode;

public class DeleteAllTargets {
    /**
     * Remove all nodes with target value. 
     * Input : 2 -> 3 -> 4 -> 2 -> null, target = 2
     * Output: 3 -> 4 -> null (not 4 -> 2 -> null)
     * head may change - use a dummyhead
     * DO NOT FORGET tail.next = null
     * */
	public static ListNode deleteAllTargets(ListNode head, int target) {		
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		
		while (head != null) {
			if (head.value != target) {
				curr.next = head;
				curr = curr.next;
			}
			head = head.next;
		}
		
		// DO NOT forget!!
		curr.next = null;
		return dummy.next;
	}
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(4);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(2);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		
		util.Util.printLinkedList(deleteAllTargets(node1, 2));
	}
}
