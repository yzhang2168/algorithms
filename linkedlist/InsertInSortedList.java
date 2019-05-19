package algorithms.linked;

import data_structures.ListNode;

public class InsertInSortedList {
	 /**
     * insert a node into a sorted linked list
     * at head
     * at tail
     * in middle
     * */ 
    public static ListNode insertNode(ListNode head, int target) {
    	ListNode newNode = new ListNode(target);
    	
    	// insert as head - new head
    	if (head == null || head.value >= target) {
    		newNode.next = head;
    		return newNode;
    	}
    	
    	// insert in the middle or as tail
    	// since head will remain the same, set prev to head and use prev to traverse the list
    	ListNode prev = head;
    	while (prev != null && prev.next != null && prev.next.value < target) {
    		prev = prev.next;    		
    	}
    	
    	// prev is last node or prev.next >= target
        newNode.next = prev.next;
        prev.next = newNode;
        return head;
    }
    
    public static void main(String[] args) {
    	ListNode n1 = new ListNode(1);
    	n1.next = new ListNode(3);
    	n1.next.next = new ListNode(5);
    	
    	util.Util.printLinkedList(insertNode(n1, 0));
    	util.Util.printLinkedList(insertNode(n1, 6));
    	util.Util.printLinkedList(insertNode(n1, 4));
    	
    }
}
