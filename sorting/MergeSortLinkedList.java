package algorithms.sorting;

import data_structures.ListNode;
import data_structures.LinkedList;

public class MergeSortLinkedList {

	public static ListNode mergeSort(ListNode head) {
		// base
		if (head == null || head.next == null) {
			return head;
		}
		// divide - split into 2 lists: head...mid, right...tail
		ListNode mid = LinkedList.findMiddle(head);
		ListNode leftHead = head;
		ListNode rightHead = mid.next;
		mid.next = null;

		// recursive - returns a sorted list
		ListNode leftList = mergeSort(leftHead);
		ListNode rightList = mergeSort(rightHead);

		// conquer
		ListNode newHead = LinkedList.mergeSortedLists(leftList, rightList);
		return newHead;
	}

	public static void main(String[] args) {
		ListNode node0 = null;
		ListNode node1 = new ListNode(4);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(6);
		ListNode node4 = new ListNode(-3);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		util.Util.printLinkedList(node0);
		util.Util.printLinkedList(mergeSort(node0));
		util.Util.printLinkedList(node1);
		util.Util.printLinkedList(mergeSort(node1));
	}
}
