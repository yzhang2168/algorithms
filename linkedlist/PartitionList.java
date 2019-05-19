package algorithms.linked;

import data_structures.ListNode;

public class PartitionList {

	public static ListNode partition(ListNode head, int target) {
		ListNode dummySmall = new ListNode(-1);
		ListNode smallCurr = dummySmall;
		ListNode dummyLarge = new ListNode(-1);
		ListNode largeCurr = dummyLarge;
		while (head != null) {
			if (head.value < target) {
				smallCurr.next = head;
				smallCurr = smallCurr.next;
			} else {
				largeCurr.next = head;
				largeCurr = largeCurr.next;
			}
			head = head.next;
		}
		
		smallCurr.next = dummyLarge.next;
		// additional nodes after largeCurr, cycle if not setting to null
		largeCurr.next = null;		
		return dummySmall.next;
	}
}
