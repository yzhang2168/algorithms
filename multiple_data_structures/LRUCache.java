package algorithms.multiple_data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.ws.Response;

import sun.misc.Request;

/**
 * Least Recently Used (LRU) cache
cache: to avoid high cost of accessing something, pre-compute result and store it in some storage (memory, register, disk).
what to save in cache: 
For advertising group, user behavior, behavior at time = T often associated with T - 1, T- 2… Therefore we save most recent query.
key: request, value: result
capacity: 5000 items
delete an item: least recently accessed vs circular array: least recently added
5001st request: delete the least recent item.
duplicate requests
LRU: stores most recently accessed capacity (5000) items

use case: 
return the corresponding response to a request
request -> list node
hashmap <key=request, value=list node>
list node: answer, prev, next, question
duplicate query - adjust the priority: 
doubly linked list: head, tail
insert a new entry: 
create a new node
add to head and to hashmap
delete the oldest entry if cache is full: 
delete tail node
delete entry in hashmap (this requires that we know list node -> request so that we can find entry based on request in hashmap, therefore we also save request in list node)
design problem
1. list all use cases
2. for each use case, choose appropriate data structure
3. take all use cases together, reconcile all 
 * */
public class LRUCache<K, V> {

	// by making the nested class `static` it loses access to all members of the outer class.
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
	}
	
	
	private static final int CAPACITY = 500;
	private ListNode<K, V> head;
	private ListNode<K, V> tail;	
	private HashMap<K, ListNode<K, V>> hashmap;
	
	public LRUCache(int capacity) {
		head = null;
		tail = null;
		hashmap = new HashMap<K, ListNode<K, V>>();
	}
	
	// write operation:
	// case 1: key already in cache, get response, move page to head
	// case 2: key not in cache and cache is full, kick out the oldest one, add new one
	// case 3: key not in cache and cache is not full, add new one
	public void set(K request, V response) {
		ListNode<K, V> node = null;
		
		if (hashmap.containsKey(request)) {
			node = hashmap.get(request);
			node.update(request, response);
			remove(node); // -------------------------will the node be Garbage collected?
			// move it to head in the next stmt
		} else if (isFull()) {
			// reuse tail node to hold new <k, v>
			node = tail;
			remove(node);
			node.update(request, response);
		} else {
			node = new ListNode<K, V>(request, response);
		}
		addFirst(node);
	}
	
	// read operation: still need to adjust priority of the item
	public V get(K request) {
		ListNode<K, V> node = hashmap.get(request);
		
		if (node == null) {
			return null;
		} else {
			remove(node);
			addFirst(node);
			return node.response;			
		}
	}
	
	
	private boolean isFull() {
		return hashmap.size() == CAPACITY;
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
	
}
