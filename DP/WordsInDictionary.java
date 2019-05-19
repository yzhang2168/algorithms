package algorithms.DP;

import java.util.HashSet;
import java.util.Set;

public class WordsInDictionary {

	/**
	 * Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.
	 * Assumptions
	 * The given word is not null and is not empty
	 * The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
	 * Examples
	 * Dictionary: {“bob”, “cat”, “rob”}
	 * Word: “robob” return false
	 * Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
	 * 
	 * linear scan and look back
	 * base case: size = 1, m[1] is for substring 0...0, loop up in dictionary
	 * induction rule: m[i] is for substring 0...i - 1, whether it can be composed by dictionary words 
	 * 						= no cut - look up in dictionary 
	 * 						  OR (m[j] AND substring[j...i] in dictionary)
	 * int i = 1...n
	 * 		i = n, last char
	 *	 int j = 1...i/1...n
	 *		m[n] && substring(n...n) -> false
				if (m[j] && dictSet.contains(word.substring(j, i))) {
	 * 左大段 + 右小段
	 * left portion: look up in table
	 * right portion: manual check, b/c smallest element not identical as the smallest in left, cannot look up in table
	 * 
	 * s.substring(): O(n)
	 * total: O(n ^ 3)
	 * */	
	
	public static boolean canBreak(String word, String[] dict) {
		Set<String> dictSet = toHashSet(dict);

		int n = word.length();
		boolean[] m = new boolean[n];
		// m[i]: corresponds to word.substring[0...i], if it meets requirement then m[i] = true
		
		for (int i = 0; i < n; i++) {
			// case 0: no cut, take [0...i] 
			if (dictSet.contains(word.substring(0, i + 1))) { //substring[0...i]
				m[i] = true;
			} else {
				// otherwise, linear scan and look back at all possible single splits
				// case 1 || case 2 ||...
				for (int j = 0; j < i; j++) {
					// 左大段 + 右小段
					if (m[j] && dictSet.contains(word.substring(j + 1, i + 1))) {// split [0...j] || [j + 1...i]
						m[i] = true;
						break;
					}
				}
			}
		}
		return m[n - 1];
	}
	
	public static boolean wordsInDictionary(String[] dict, String word) {
		Set<String> dictSet = toHashSet(dict);
		
		int n = word.length();
		// problem size: 1...n, match index with problem size
		// m[1]...m[n] for input.substring[0]...substring[0,n)
		boolean[] m = new boolean[n + 1];
		m[0] = true; // not corresponding to input string		
		
		// filling out m[1]...m[n]
		// base case: size = 1, m[1] is for substring 0...0, look up in dict
		for (int i = 1; i <= n; i++) {
			/* case 0: no cut, take substring[0...i] and look it up in dict
			// if substring[0...i - 1] is in dict, done for this i
			// substring(start,end): end excluding
			if (dictSet.contains(word.substring(0, i))) {
				m[i] = true;
				continue; // out of this i iteration, do not check case 1...i
			}
			
			// to fill out m[i], look back at all possible single splits - m[1]...m[i - 1]
			// m[i] = case 1 || case 2 ||...
			for (int j = 1; j < i; j++) {
				// 左大段查历史 + 右小段没历史可查 manual check
				if (m[j] && dictSet.contains(word.substring(j, i))) {
					m[i] = true;
					break;
				}
			}
			*/		
			// consolidate case 0 with case 1...n-1
			for (int j = 0; j < i; j++) {
				if (m[j] && dictSet.contains(word.substring(j, i))) {
					m[i] = true;
					break;
				}
			}
		}
		
		return m[n];
	}
	
	private static Set<String> toHashSet(String[] dict) {
		Set<String> set = new HashSet<String>();
		for (String s : dict) {
			set.add(s);
		}
		return set;
	}
	
	public static void main(String[] args) {
		String[] dict = {"bob", "cat", "rob"};
		String a = "robob";
		String b = "bcoabt";
		String c = "robbobcat";
		String d = "bobbob";

		System.out.println(a.substring(0, 0)); // ""
		System.out.println(wordsInDictionary(dict, a));
		System.out.println(wordsInDictionary(dict, b));
		System.out.println(wordsInDictionary(dict, c));
		System.out.println(wordsInDictionary(dict, d));
	}
}
