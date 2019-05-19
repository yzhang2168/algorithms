package algorithms.sorting;

import java.util.Arrays;

/* move zeros to the right and no need retain the order of non-Os 
 * 2 pointers, 3 regions
 * [0...l-1]: non-zeros
 * [l...r]: to explore
 * [r+1...n-1]: zeros
*/
public class MoveZeros {

	public static void moveZerosToRight (int[] input) {
		if (input == null || input.length <= 1) {
			return;
		}
		
		int l = 0;
		int r = input.length - 1;
		while (l <= r) {
			if (input[l] != 0) {
				l++;
			} else if (input[r] == 0) {
				r--;
			} else {
				swap(input, l++, r--);
			}
		}
	}
	
	public static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	public static void main(String[] args) {
		int[] a = {-4, 0, 1, 0, 2, 1};
		moveZerosToRight(a);
		System.out.println(Arrays.toString(a));
	}
}
