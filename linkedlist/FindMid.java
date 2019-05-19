package algorithms.linked;

import data_structures.ListNode;
import data_structures.ListNodeGenerics;

public class FindMid<E> {
	/* left mid
	 * 
	 * 1 -> 2 -> 3 -> 4
	 *      s
	 *           f
	 * 
	 */
	public static ListNode findMid(ListNode n1) {
		if (n1 == null || n1.next == null) {
			return n1;
		}
		
		ListNode slow = n1;
		ListNode fast = n1;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}

}
