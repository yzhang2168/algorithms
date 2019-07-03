package DP;

import java.util.Arrays;

public class LongestCommonSubstring {

	public static String longestCommonSubstring(String a, String b) {
		if (a == null || a.length() == 0 || b == null || b.length() == 0) {
			return "";
		}
		int m = a.length();
		int n = b.length();
		int[][] common = new int[m + 1][n + 1];
		common[0][0] = 0;
		
		for (int i = 0; i <= m; i++) {
			common[i][0] = i;
		}
		
		for (int i = 0; i <= n; i++) {
			common[0][i] = i;
		}
		return "result";
	}
	
	public static void main(String[] args) {
		longestCommonSubstring("student", "sweden");
		
	}
}
/**
 * assumptions:
 * 
 * data structure:
 * algorithm:
 * 	init:
 * 	for each step:
 * 	termination:
 * 
 * time:
 * space:
 */
 
