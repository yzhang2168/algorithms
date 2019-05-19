package algorithms.linked;

import data_structures.ListNode;

public class MergeTwoSortedLists {

	public static ListNode merge(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(-1);
		ListNode curr = dummy;
		while (one != null && two != null) {
			if (one.value <= two.value) {
				curr.next = one;
				one = one.next;
			} else {
				curr.next = two;
				two = two.next;
			}
			curr = curr.next;
		}

		// covers corner case of either one is null
		if (one != null) {
			curr.next = one;
		} else {
			curr.next = two;
		}
		return dummy.next;
	}

}
