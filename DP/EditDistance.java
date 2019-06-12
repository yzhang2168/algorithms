package DP;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;

public class EditDistance {

	/**
	 * Given two strings of alphanumeric characters, determine the minimum number of 
	 * Replace, Delete, and Insert operations needed to transform source string into target string.
	 * Assumptions
	 * Both strings are not null
	 * Examples
	 * string one: “sigh”, string two : “asith”
	 * the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).
	 * 
	 * recursion solution:
	 * # recursion calls: seems 4, but case 0 is exclusive of cases 1-3 
	 * branching factor 1-3
	 * cases 1-3: return min
	 * 
	 * time: O(3^(m + n)), where m == one.length(), n == two.length()
	 * # levels: m + n, worst case: delete(), insert(), 1 char at a time 
	 * # branches at each level: 1-3
	 * */
	public static int editDistanceRecursion(String one, String two) {
		// base case		
		if (one.length() == 0) {
			return two.length();
		}
		if (two.length() == 0) {
			return one.length();
		}
		
		// recursion rule
		// case 0
		if (one.charAt(0) == two.charAt(0)) {
			return editDistanceRecursion(one.substring(1), two.substring(1));
		} else {
			// case 1
			int replace = 1 + editDistanceRecursion(one.substring(1), two.substring(1));
			// case 2
			int delete = 1 + editDistanceRecursion(one, two.substring(1));
			// case 3
			int insert = 1 + editDistanceRecursion(one.substring(1), two);
			
			return Math.min(replace, Math.min(delete, insert));
		}	
	}
	
	/**
	 * base case: m[0][0] = 0, m[i][0] = i, m[0][j] = j
	 * m[i][j]: edit distance for one.substring[0...i) to two.substring[0...j)
	 * m[i][j] = m[i - 1][j - 1] if one.charAt(i - 1) == two.charAt(j - 1)
	 * 			1 + min(replace m[i - 1][j - 1], delete m[i - 1][j], insert m[i][j - 1])
	 * time : O(m * n)
	 * space: O(m * n)
	 * */
	public static int editDistanceDP(String one, String two) {
		int m = one.length();
		int n = two.length();
		// add an extra i = 0 row and j= 0 col
		int[][] distance = new int[m + 1][n + 1];
		
		for (int i = 0; i <= m; i++) {
			distance[i][0] = i;
		}
		
		for (int j = 0; j <= n; j++) {
			distance[0][j] = j;
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// case 0: Note curr letter index is i - 1/j - 1, b/c we added an extra row 0 and col 0
				if (one.charAt(i - 1) == two.charAt(j - 1)) {
					distance[i][j] = distance[i - 1][j - 1];
				} else {
					int replace = distance[i - 1][j - 1];
					int delete = distance[i - 1][j];
					int insert = distance[i][j - 1];
					distance[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
				}
			}
		}
		return distance[m][n];
	}
	
	public static int editDistanceDPOptimal(String one, String two) {
		int m = one.length();
		int n = two.length();

		// add an extra i = 0 row and j= 0 col
		int[] prevRow = new int[n + 1];
		int[] currRow = new int[n + 1];
		
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0) {
					prevRow[j] = j;
				} else if (j == 0) {
					currRow[j] = i;
				} else if (one.charAt(i - 1) == two.charAt(j - 1)) {
					currRow[j] = prevRow[j - 1];
				} else {
					int replace = 1 + prevRow[j - 1];
					int insert = 1 + currRow[j - 1];
					int delete = 1 + prevRow[j];
					currRow[j] = Math.min(replace, Math.min(insert, delete));
				}
			}
			
			// if m == 0, currRow is not used
			if (i > 0) {
	              prevRow = Arrays.copyOf(currRow, n + 1);
	              // prevRow = currRow is copying reference!!!
	        }
		}
		return prevRow[n];		
	}

	
	public static void main(String[] args) {
		System.out.println(editDistanceDPOptimal("", ""));
		System.out.println(editDistanceDPOptimal("abc", "abc"));
		System.out.println(editDistanceRecursion("", ""));

		Instant start1 = Instant.now();
		System.out.println(editDistanceRecursion("Shakespeare", "shake spear"));
		Instant end1 = Instant.now();
		Duration timeElapsed1 = Duration.between(start1, end1);
		System.out.println(timeElapsed1.toMillis());
		
		System.out.println(editDistanceDP("", ""));
		Instant start2 = Instant.now();
		System.out.println(editDistanceDP("Shakespeare", "shake spear"));
		Instant end2 = Instant.now();
		Duration timeElapsed2 = Duration.between(start2, end2);
		System.out.println(timeElapsed2.toMillis());
	}

}
