package algorithms.string;

import java.util.HashSet;

public class LongestSubstringUniqueChars {
	/**
	 * data structure:
	 * 		set contains all distinct chars in the sliding window of [slow...fast-1]
	 * init: s = 0, f = 0, set = {}
	 * for each step
	 * 		a[f] not in set, f++
	 * 		a[f] in set, remove a[s] from set, s++
	 * */
	public static int longestSubstring(String input) {

		HashSet<Character> distinct = new HashSet<Character>();
		int slow = 0;
		int fast = 0;
		int longest = 0;
		while (fast < input.length()) {
			if (distinct.contains(input.charAt(fast))) {
				// if duplicate, slide the slow pointer
				distinct.remove(input.charAt(slow++));
			} else {
				// if not duplicate, slide the fast pointer
				// and have a new window of slow...fast containing distinct chars
				distinct.add(input.charAt(fast++));
				longest = Math.max(longest, fast - slow);
			}
		}
		return longest;
	}

	/**
	 * data structure:
		s: left border of window
		f: right border of window
		set: chars in window
		
		init:
		s = 0;
		f = 0;
		set = {array[s]}
		
		for each step:
		if array[f+1] not in set, f++, add array[f], update global max
		else, remove array[s], s++
		
		termination:
		f == array.length() - 1 
	 * */
	public static int longestSubstringI(String input) {
		if (input == null || input.length() == 0) {
			return 0;
		}
		int s = 0;
		int f = 0;
		HashSet<Character> set = new HashSet<Character>();
		set.add(input.charAt(s));
		int longest = 1;

		while (f < input.length() - 1) {
			if (f + 1 < input.length() && !set.contains(input.charAt(f + 1))) {
				set.add(input.charAt(++f));
				longest = Math.max(longest, f - s + 1);
			} else {	
				set.remove(input.charAt(s++));
			}
		}
		return longest;
	}
	
	public static void main(String[] args) {
		System.out.println(longestSubstringI("abcabcbbc"));
		System.out.println(longestSubstring("abcabcbbc"));
	}
}
