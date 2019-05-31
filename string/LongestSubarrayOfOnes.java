package string;

/**
 * Given a 0/1 array, find the longest subarray of 1s after <= k flips 0 -> 1
 * 011111 0 11 0 11 011 0 111111110
 * k = 4
 * find the longest subarray that contains <= k 0s
 * data structure:
 * 	zeros in the sliding window of [slow...fast-1]
 * 	init: s = 0, f = 0, zeros = 0
 * for each step
 * 		a[f] == 1: f++, update longest
 * 		a[f] == 0
 * 			case 1. zeros < k, zeros++, f++, update longest
 * 			case 2. zeros == k, s++, update zeros if 0 is removed 
 * */
public class LongestSubarrayOfOnes {

	public static int longestSubarrayOfOnes(int[] array, int k) {
		int s = 0;
		int f = 0;
		int longest = 0;
		int zeros = 0;

		while (f < array.length) {
			if (array[f] == 1) {
				longest = Math.max(longest, f - s + 1);
				f++;
			} else if (array[f] == 0) {
				if (zeros < k) {
					longest = Math.max(longest, f - s + 1);
					zeros++;
					f++;
				} else { // zeros == k
					if (array[s] == 0) {
						zeros--;
					}
					s++; // repeat until zeros < k
				}				
			}
		}
		return longest;
	}

	public static void main(String[] args) {
		System.out.println(longestSubarrayOfOnes(new int[] {0}, 0));
		System.out.println(longestSubarrayOfOnes(new int[] {0}, 2));
		System.out.println(longestSubarrayOfOnes(new int[] {1}, 0));
		System.out.println(longestSubarrayOfOnes(new int[] {0,0,1,0,1,1,1,1,1,0,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0}, 4));
	}
}
