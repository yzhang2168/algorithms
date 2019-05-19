package algorithms.linked;

import data_structures.ListNodeGenerics;

public class ReverseRecursive<E> {

	public ListNodeGenerics<E> reverse(ListNodeGenerics<E> head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNodeGenerics<E> newHead = reverse(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
}
