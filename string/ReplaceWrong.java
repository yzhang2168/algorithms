package algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReplaceWrong {
	/**
	 * s = 0;
	 * f = 0;
	 * [0...s-1] to keep
	 * [f...] to explore
	 * if freq >= 2, encode
	 * if freq == 1, leave as is, singletons++
	 * 
	 * 2nd round, resize array
	 * from right to left, add in 1 for singletons
	 * 
	 * zaaabbddefff
	 *   s
	 *     f
	 *     f
	 * */
	public String encode(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		
		char[] array = input.toCharArray();
		int s = 0;
		int f = 0;
		int begin = 0;
		int singletons = 0;
		while (f < array.length) {
			while (f + 1 < array.length && array[f + 1] == array[f]) {
				f++;
			}
			int length = f - begin + 1;
			if (length > 0) {
				array[s++] = array[f];
				array[s++] = (char) ('0' + length);
				f++;
				begin = f;				
			} else if (length == 0){
				array[s++] = array[f++];
				singletons++;
				begin = f;
			}
		}
		if (singletons == 0) {
			return new String(array, 0, s);
		} else {
			return expandSingletons(array, s, singletons);
		}
	}
	
	public static String expandSingletons(char[] array, int length, int singletons) {
		int newLength = length + singletons;
		char[] result = new char[newLength];
		int end = newLength - 1;
		for (int i = length - 1; i >= 0; i--) {
			if (isLetter(array[i])) {
				if (i == length - 1 || isLetter(array[i + 1])) {
					result[end--] = '1';
					result[end--] = array[i];
				} 
			} else {
					result[end--] = array[i];	
			}
		}

		return new String(result);
	}
	
	private static boolean isLetter(char ch) {
		return ch < '0' || ch > '9';
	}
	
	/**
	 * 16. Given a 0/1 array, find the longest subarray of 1s after <= k flips 0 -> 1	15
	 * longest subarray with <= k 0s
	 * sliding window [s...f-1]
	 * [f.. to explore]
	 * 
	 * init
	 * zeros = 0
	 * s = 0;
	 * f = 0;
	 * longest = 0;
	 * 
	 * for each step
	 * if input[f] == 0
	 * 		if zeros < k, zeros++, f++, update longest
	 * 		if zeros == k, slow++, if input[s] == 0, zeros--
	 * */
	public int subarray(int[] input, int k) {
		if (input == null || input.length == 0 || k < 0) {
			return 0;
		}
		
		int s = 0;
		int f = 0;
		int zeros = 0;
		int longest = 0;
		while (f < input.length) {
			if (input[f] == 0) {
				if (zeros < k) {
					zeros++;
					f++;
					longest = Math.max(longest, f - s);
				} else { // zeros == k
					if (input[s] == 0) {
						zeros--;
					}
					s++;
				}
			} else {
				f++;
				longest = Math.max(longest, f - s);
			}
		}
		return longest;
	}
	
	public String decompressI(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			int digit = input.charAt(i + 1) - '0';
			for (int j = 0; j < digit; j++) {
				sb.append(input.charAt(i));
			}
			i++;
		}
		return sb.toString();
	}
	
	public String decompress(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		char[] array = input.toCharArray();
		return decompressLonger(array, decompressShorter(array));
	}
		

	public static int decompressShorter(char[] input) {
		int end = 0;
		for (int i = 0; i < input.length; i += 2) {
			int digit = getDigit(input[i + 1]);
			if (digit >= 0 && digit <= 2) {
				for (int j = 0; j < digit; j++) {
					input[end++] = input[i];
				}
			} else {
				input[end++] = input[i];
				input[end++] = input[i + 1];
			}
		}
		//System.out.println(Arrays.toString(input));
		//System.out.println(end);
		return end;
	}
	
	public static String decompressLonger(char[] input, int length) {
		int newLength = length;
		for (int i = 0; i < length; i++) {
			int digit = getDigit(input[i]);
			if (digit >= 3 && digit <= 9) {
				newLength += digit - 2;
			}
		}
		
		char[] result = new char[newLength];
		int end = result.length - 1;
		//System.out.println(end);
		for (int i = length - 1; i >= 0; i--) {
			int digit = getDigit(input[i]);
			if (digit >= 3 && digit <= 9) {
				for (int c = 0; c < digit; c++) {
					result[end--] = input[i - 1];
				}				
				i--;
			} else {
				result[end--] = input[i];
			}
		}
			
		return new String(result);
	}	
	
	private static int getDigit(char ch) {
		return ch - '0';
	}
	
	// studentden -> stuXXXXtXXXX (den -> XX)

	public static String replace(String input, String source, String sub) {
		if (input == null || input.length() == 0 || source == null || source.length() == 0) {
			return input;
		}
		
		char[] array = input.toCharArray();
		
		if (source.length() >= sub.length()) {
			return replaceShorter(array, source, sub);
		} else {
			return replaceLonger(array, source, sub);
		}
	}
	
	// [0...s-1] result
	// [f...] to explore
	private static String replaceShorter (char[] input, String source, String sub) {
		int s = 0;
		for (int f = 0; f < input.length; f++) {
			if (f <= input.length - source.length() && isSubstring(input, f, source)) {
				copySubstring(input, s, sub);
				s = s + sub.length();
				f = f + source.length() - 1;
			} else {
				input[s++] = input[f];
			}
		}
		return new String(input, 0, s);
	}
	
	private static boolean isSubstring(char[] input, int f, String source) {
		for (int i = 0; i < source.length(); i++) {
			if (input[f + i] != source.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	private static void copySubstring(char[] input, int f, String target) {
		for (int i = 0; i < target.length(); i++) {
			input[f + i] = target.charAt(i);
		}
	}
	
	private static String replaceLonger (char[] input, String source, String sub) {
		List<Integer> matches = findAllMatches(input, source);
		char[] result = new char[input.length + (sub.length() - source.length()) * matches.size()];
		int end = result.length - 1;
		int lastMatch = matches.size() - 1;
		for (int i = input.length - 1; i >= 0; i--) {
			if (lastMatch >= 0 && i == matches.get(lastMatch)) {
				copySubstring(result, end - sub.length() + 1, sub);
				lastMatch--;
				end = end - sub.length();
				i = i - source.length() + 1;
			} else {
				result[end--] = input[i];
			}
		}
		
		return new String(result);
	}
	
	private static List<Integer> findAllMatches(char[] input, String source) {
		List<Integer> matches = new ArrayList<Integer>();
		
		for (int i = 0; i <= input.length - source.length(); i++) {
			if (isSubstring(input, i, source)) {
				matches.add(i + source.length() - 1);
				i = i + source.length() - 1;
			}
		}
		return matches;
	}

	public static void main(String[] args) {
		String x = "b";
		HashMap<String, String> map = new HashMap<>();
		map.put("a", x);
		x = "c";
		map = null;
		try {
			map.get("a");
		} catch (NullPointerException e) {
			System.out.println("I caught you " + e.toString());
		}

		System.out.println(map.get("a"));
		ReplaceWrong o = new ReplaceWrong();
		System.out.println(o.decompress("a3b2c0d2e1f3")); // bbba
		System.out.println(o.encode("aaabbddfff")); // bbba
		System.out.println(o.encode("aaabbddefff")); // bbba
		System.out.println(o.subarray(new int[] {0}, 0));
		System.out.println(o.subarray(new int[] {0}, 2));
		System.out.println(o.subarray(new int[] {1}, 0));
		System.out.println(o.subarray(new int[] {0,0,1,0,1,1,1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0}, 4));

		System.out.println(replace("studentden", "", "X"));
		System.out.println(replace("studentden", "den", ""));
		System.out.println(replace("studentden", "den", "X"));
		System.out.println(replace("studentden", "den", "XXXX"));
		System.out.println(replace(" a  b c ", " ", "20%"));
		System.out.println(replace("aaa", "aa", ""));
		System.out.println(replace("aaa", "aa", "bbb")); // bbba

	}
}

