package DFS;

import java.util.ArrayList;
import java.util.List;

public class SubsetsII {

	/**
	 * wrong definition, could call it subsequences
	 * given a sorted string of chars with DUPLICATES, return all subsequences
	 * example input: "abb"
	 * output: a, b, ab, bb, abb
	 * 
	 * exponential time, if input is not sorted, sort first nlogn 
	 * */
	
	public static List<String> findSubsetsII(String input) {
		List<String> result = new ArrayList<String>();
		if (input == null) {
			return result;
		}
		
		char[] array = input.toCharArray();
		StringBuilder resultPrefix = new StringBuilder();
		findSubsetsII(array, 0, resultPrefix, result);
		return result;
	}
	
	private static void findSubsetsII(char[] array, int level, StringBuilder resultPrefix, List<String> result) {
		// base case
		if (level == array.length) {
			result.add(resultPrefix.toString());
			return;
		}
		
		// case 1/left branch: + input
		resultPrefix.append(array[level]);
		findSubsetsII(array, level + 1, resultPrefix, result);
		resultPrefix.deleteCharAt(resultPrefix.length() - 1);

		// case 2/right branch: + ""
		// skip all chars identical to array[level] and jump to the last level identical to curr
		while (level < array.length - 1 && array[level + 1] == array[level]) {
			level++;
		}
		// this will go to the level with a different value
		findSubsetsII(array, level + 1, resultPrefix, result);
	}
	
	public static void main(String[] args) {
		StringBuilder empty = new StringBuilder();
		System.out.println(empty.toString());
		System.out.println(findSubsetsII(null));
		System.out.println(findSubsetsII(""));
		System.out.println(findSubsetsII("abbc"));
	}	
	
}
