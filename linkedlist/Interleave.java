package algorithms.linked;

import data_structures.ListNode;

public class Interleave {

	public static ListNode interleave(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		
		while (one != null && two != null) {
			/* -1 > 1 > 2 > 3
			 *  c       o
			 *  
			 * -1 > 1 > 5 > 4
			 *          c   t
			 */
			curr.next = one;
			one = one.next;
			curr.next.next = two;
			two = two.next;
			curr = curr.next.next;
		}
		
		if (one != null) {
			curr.next = one;
		} else {
			curr.next = two;
		}
		return dummy.next;
	}
}
