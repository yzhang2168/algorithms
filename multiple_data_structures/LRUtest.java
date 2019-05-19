package algorithms.multiple_data_structures;

import java.util.HashMap;

import algorithms.multiple_data_structures.LRUCache.ListNode;

public class LRUtest<K, V> {

	static class ListNode<Ki, Vi> { // it's better to use different names
		Ki request;
		Vi response;
		ListNode<Ki, Vi> prev;
		ListNode<Ki, Vi> next;
		
		public ListNode(Ki request, Vi response) {
			this.request = request;
			this.response = response;
			prev = null;
			next = null;
		}
		
		public void update(Ki request, Vi response) {
			this.request = request;
			this.response = response;
		}
		
		@Override
		public String toString() {
			return String.valueOf(response);
		}
	}
	
	
	private static final int CAPACITY = 500;
	private ListNode<K, V> head;
	private ListNode<K, V> tail;	
	private HashMap<K, ListNode<K, V>> hashmap;
	
	public LRUtest() {
		head = null;
		tail = null;
		hashmap = new HashMap<K, ListNode<K, V>>();
	}
	
	public void set(K request, V response) {
		ListNode<K, V> node = null;
		
		if (hashmap.containsKey(request)) {
			node = hashmap.get(request);
			node.update(request, response);
			remove(node); // -------------------------will the node be Garbage collected?
			
			System.gc();
			System.gc();
			System.gc();
			System.out.println(node);
			// move it to head in the next stmt
		} else {
			node = new ListNode<K, V>(request, response);
		}
		//addFirst(node);
	}
	
	public ListNode<K, V> add(K request, V response) {
		ListNode<K, V> node = new ListNode<K, V>(request, response);
		hashmap.put(node.request, node);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}
	
	private ListNode<K, V> addFirst(ListNode<K, V> node) {
		hashmap.put(node.request, node);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}
	
	private ListNode<K, V> remove(ListNode<K, V> node) {
		hashmap.remove(node.request);
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
		node.next = null;
		node.prev = null;
		return node;
	}
	
	public static void main(String[] args) {
		LRUtest<String, String> obj = new LRUtest<String, String>();
		obj.add("k1", "v1");
		obj.set("k1", "v2");
		obj.set("k1", "v3");
		
		
		/*
		HashMap <String, String> m = new HashMap<>();
        String x = "b";
        
        m.put( "a", x );

        System.out.println( m );

        x = null;
        
        System.gc();
        System.gc();
        System.gc(); // call garbage collector as much as you like, map entry x
                     // will never be removed.
        
        System.out.println( m );
        
        m.put("a", null); // now map entry can be garbage collected as all references to it are gone...
        
        System.out.println( m );
	*/
	}
}
