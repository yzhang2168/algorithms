package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).

Assumptions

In each of the two sorted arrays, there could be duplicate numbers.
Both two arrays are not null.
Examples

A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]
 * */
public class CommonNumbers {

	/** with duplicates
	 * two pointers 
	 * time: O(n + m)
	 * space: O(1)
	 * */
	public List<Integer> commonNumbersI(List<Integer> A, List<Integer> B) {
		List<Integer> result = new ArrayList<Integer>();
		if (A == null || A.size() == 0 || B == null || B.size() == 0) {
			return result;
		}
		
		int a = 0;
		int b = 0;
		while (a < A.size() && b < B.size()) {
			if (A.get(a).equals(B.get(b))) {
				result.add(A.get(a));
				a++;
				b++;
			} else if (A.get(a).compareTo(B.get(b)) < 0) {
				a++;
			} else {
				b++;
			}
		}
		return result;
	}
	
	
	/**
	 * duplicates
	 * time: construct hashmaps O(n + m) on average O(n^2 + m^2) worst case
	 * + find common elements O(n) on average, O(n^2) worst case
	 * space: O(n + m)
	 * */
	public List<Integer> commonNumbersII(List<Integer> A, List<Integer> B) {
		List<Integer> result = new ArrayList<Integer>();
		if (A == null || A.size() == 0 || B == null || B.size() == 0) {
			return result;
		}
		
		/*
		 * if use a hashset, then hash the smaller list
		if (A.size() >= B.size()) {
			List<Integer> temp = A;
			A = B;
			B = temp;
		}
		*/
		// <value, count>
		Map<Integer, Integer> aCounts = buildMap(A);		
		Map<Integer, Integer> bCounts = buildMap(B);
		for (HashMap.Entry<Integer, Integer> entry : aCounts.entrySet()) {
			Integer key = entry.getKey();			
			Integer count = bCounts.get(key); // try to call APIs as less as possible
			if (count != null) {
				count = Math.min(entry.getValue(), count);
				for (int i = 0; i < count; i++) {
					result.add(key);
				}
			}
		}
		return result;
	}
	
	private Map<Integer, Integer> buildMap(List<Integer> list) {
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		for (Integer i : list) {
			result.put(i,  result.getOrDefault(i, 0) + 1);
		}
		return result;
	}
	/**
	 * if n <<<<< m
	 * binary search
	 * nlogm
	 * */
}
