package algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllAnagramsInLongString {

	/**
	 * find all anagrams of String short in String long, return all start indices
	 * */
	public List<Integer> allAnagrams(String small, String large) {
		List<Integer> result = new ArrayList<Integer>();
		if (large.length() == 0 || small.length() > large.length()) {
			return result;
		}
		
		// this map record the # chars needed for each distinct char in small
		// s = "abbc", map = {'a':1, 'b':2, 'c':1}
		// when we get an 'a' in large, a's count-- 
		// only when count is 1 -> 0, 'a' is totally matched, match++
		HashMap<Character, Integer> map = countMap(small);
		
		// record how many distanct chars in s have been matched
		// only when all distinct chars are matched, aka, match = map.size(), we found an anagram
		int match = 0;
		
		// a fixed-sized sliding window of size s.length()
		// only need to record the end index of the window
		// for each step from left to right, only need to change:
		//	1. remove the leftmost char in the prev window
		// 	2. add the rightmost char in the curr window
		for (int i = 0; i < large.length(); i++) {
			// handle the newly added/rightmost char in the curr window
			char temp = large.charAt(i);
			Integer count = map.get(temp);
			if (count != null) {
				map.put(temp,  count - 1);
				if (count == 1) {
					match++;
				}
			}
			
			// hand the leftmost char in the prev window
			if (i >= small.length()) {
				temp = large.charAt(i - small.length());
				count = map.get(temp);
				if (count != null) {
					// the needed count should ++
					// only when the count is from 0 -> 1, we are short for 1 match
					map.put(temp, count + 1);
					if (count == 0) {
						match--;
					}
				}
 			}
			
			// for the curr window, if all distinct chars are matched - counts are 0s
			if (match == map.size()) {
				result.add(i - small.length() + 1);
			}
			
		}
		return result;
	}
	
	private HashMap<Character, Integer> countMap(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
		}
		return map;
	}
}
