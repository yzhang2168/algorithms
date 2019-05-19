package algorithms.linked;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_structures.ListNode;

class AddTwoNumbersTest {
	
	AddTwoNumbers test;
	
	@BeforeEach
	public void setUp() {
		test = new AddTwoNumbers();
	}
	
	public String printList(ListNode head) {
		StringBuilder sb = new StringBuilder();
		while (head != null) {
			sb.append(head.value);
			if (head.next != null) {
				sb.append(',');
			}
			head = head.next;
		}
		return sb.toString();
	}
	
	/** both are null */
	@Test
	void test1() {
		ListNode one = null;    	
    	ListNode two = null;    	
    	ListNode expected = null;
    	assertEquals(expected, test.addTwoNumbers(one, two));
	}

	/** one of them is null */
	@Test
	void test2() {
		ListNode one = null;
    	
    	ListNode two = new ListNode(5);
    	two.next = new ListNode(6);
    	two.next.next = new ListNode(4);
    	
    	ListNode expected = two;
    	assertEquals(printList(expected), printList(test.addTwoNumbers(one, two)));
	}

	/** both are not null, unequal length */
	@Test
	void test3() {
		ListNode one = new ListNode(5);
    	one.next = new ListNode(4);
    	one.next.next = new ListNode(3);
    	one.next.next.next = new ListNode(1);
    	one.next.next.next.next = new ListNode(2);
    	
    	ListNode two = new ListNode(5);
    	two.next = new ListNode(6);
    	two.next.next = new ListNode(4);
    	
    	ListNode expected = new ListNode(0);
    	expected.next = new ListNode(1);
    	expected.next.next = new ListNode(8);
    	expected.next.next.next = new ListNode(1);
    	expected.next.next.next.next = new ListNode(2);
    	assertEquals(printList(expected), printList(test.addTwoNumbers(one, two)));
	}

	/** both are not null, equal length, and carry over the highest digit */
	@Test
	void test4() {
		ListNode one = new ListNode(5);
    	one.next = new ListNode(4);
    	one.next.next = new ListNode(3);
    	
    	ListNode two = new ListNode(5);
    	two.next = new ListNode(5);
    	two.next.next = new ListNode(6);
    	
    	ListNode expected = new ListNode(0);
    	expected.next = new ListNode(0);
    	expected.next.next = new ListNode(0);
    	expected.next.next.next = new ListNode(1);
    	
    	assertEquals(printList(expected), printList(test.addTwoNumbers(one, two)));
	}
	
	/** both are not null, equal length, and carry over twice */
	@Test
	void test5() {
		ListNode one = new ListNode(5);
    	one.next = new ListNode(4);
    	one.next.next = new ListNode(3);
    	
    	ListNode two = new ListNode(5);
    	two.next = new ListNode(5);
    	two.next.next = new ListNode(6);
    	two.next.next.next = new ListNode(9);
    	
    	ListNode expected = new ListNode(0);
    	expected.next = new ListNode(0);
    	expected.next.next = new ListNode(0);
    	expected.next.next.next = new ListNode(0);
    	expected.next.next.next.next = new ListNode(1);
    	
    	assertEquals(printList(expected), printList(test.addTwoNumbers(one, two)));
	}

	
	/** both are not null, equal length, and carry over twice */
	@Test
	void test6() {
		ListNode one = new ListNode(5);
    	one.next = new ListNode(4);
    	one.next.next = new ListNode(3);
    	
    	ListNode two = new ListNode(5);
    	two.next = new ListNode(5);
    	two.next.next = new ListNode(6);
    	two.next.next.next = new ListNode(9);
    	two.next.next.next.next = new ListNode(1);
    	
    	ListNode expected = new ListNode(0);
    	expected.next = new ListNode(0);
    	expected.next.next = new ListNode(0);
    	expected.next.next.next = new ListNode(0);
    	expected.next.next.next.next = new ListNode(2);
    	
    	assertEquals(printList(expected), printList(test.addTwoNumbers(one, two)));
	}

}
