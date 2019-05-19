package algorithms.multiple_data_structures;

import java.util.HashMap;

/**
 * in a data stream of chars, keep track of 1st non-repeating char
 * requires time O(1) 
 * */
public class FirstNonRepeating {

	static class Node {
		private Character value;
		private Node prev;
		private Node next;
		
		public Node(Character value) {
			this.value = value;
			prev = null;
			next = null;
		}		
		
		@Override
		public String toString() {
			return value == null ? "" : String.valueOf(value);
		}
		
	}
	
	private Node head;
	private Node tail;
	private HashMap<Character, Node> map;
	
	public FirstNonRepeating() {
		head = null;
		tail = null;
		map = new HashMap<Character, Node>();
	}

	public void read(Character c) {
		Node node = null;
		
		if (map.containsKey(c)) {
			node = map.get(c); 
			//System.out.println("found node: " + node);
			if (node != null) {
				remove(node);
				map.put(c, null); // set value to null such that we know the char appeared before
				// if keep <c, removed node> NPE ??
			}			
		} else {
			node = new Node(c);
			System.out.println("adding node: " + node);
			map.put(node.value, node);
			append(node);
		}
	}
	
	private void append(Node node) {		
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
	}
	
	public Character getFirstNonrepeating() {
		return head == null ? null : head.value;
	}
	
	public Node remove(Node node) {
		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		if (node == head) {
			head = head.next;
		}
		if (node == tail) {
			tail = tail.prev;
		}
		node.prev = null;
		node.next = null;
		return node;
	}
	
	public static void main(String[] args) {
		Node dummy = new FirstNonRepeating.Node(null);
		System.out.println(dummy);
		
		FirstNonRepeating stream = new FirstNonRepeating();
		stream.read('a');
		System.out.println(stream.getFirstNonrepeating());
		stream.read('a');
		System.out.println(stream.getFirstNonrepeating());
		stream.read('a');
		System.out.println(stream.getFirstNonrepeating());
		stream.read('b');
		System.out.println(stream.getFirstNonrepeating());
	}
}
