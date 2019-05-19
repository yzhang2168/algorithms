package algorithms.histogram;

import java.util.Arrays;

public class waterTrapped {

	/**
	 * histogram: find total water trapped in between cols
	 * 看每个格子能积水多少
	 * 中心开花 
	 * for each col, find the max left wall and max right wall, including the col itself
	 * min(left wall, right wall) and calculate vol = height - input[i]
	 * total O(n ^ 2)
	 * */
	public static int maxTrapped(int[] input) {
		if (input == null || input.length == 0) {
			return 0;
		}

		int total = 0;
		for (int i = 0; i < input.length; i++) {
			int left = i;
			int right = i;
			int leftHeight = input[i];
			int rightHeight = input[i];

			while (left >= 0) {
				if (input[left] > leftHeight) {
					leftHeight = input[left];
				}
				left--;
			}
			/*
			if (left != i) {
				left++;
			}
			 */
			while (right <= input.length - 1) {
				if (input[right] > rightHeight) {
					rightHeight = input[right];
				}
				right++;
			}			
			/*
			if (right != i) {
				right--;
			}
			 */

			int height = Math.min(leftHeight, rightHeight);
			//System.out.println("i = " + i + ", height = " + height);

			total += height - input[i];					
		}

		return total;
	}

	public static int waterTrappedI(int[] input) {
		int result = 0;
		if (input == null || input.length == 0) {
			return result;
		}

		for (int i = 0; i < input.length; i++) {
			int leftHeight = max(input, 0, i);
			int rightHeight = max(input, i, input.length - 1);
			result += Math.min(leftHeight, rightHeight) - input[i];
		}

		return result;
	}

	private static int max(int[] input, int i, int j) {
		int max = Integer.MIN_VALUE;
		for (int k = i; k <= j; k++) {
			max = Math.max(max, input[k]);
		} 
		return max;
	}


	/**
	 * calculate leftwall and rightwall using DP O(n) + O(n)
	 * linear scan each bar's water on top: O(n)
	 * total O(n)
	 * space O(n)
	 * */
	public static int waterTrappedDP(int[] input) {
		int result = 0;
		if (input == null || input.length == 0) {
			return result;
		}

		int[] leftHeights = leftMaxes(input);
		int[] rightHeights = rightMaxes(input);
		//System.out.println(Arrays.toString(leftHeights));
		//System.out.println(Arrays.toString(rightHeights));

		for (int i = 0; i < input.length; i++) {
			result += Math.min(leftHeights[i], rightHeights[i]) - input[i];
		}

		return result;
	}

	private static int[] leftMaxes(int[] input) {
		if (input == null || input.length == 0) {
			return new int[0];
		}

		int[] result = new int[input.length];
		result[0] = input[0];
		for (int i = 1; i < input.length; i++) {
			result[i] = Math.max(result[i - 1], input[i]);
		}
		return result;
	}

	private static int[] rightMaxes(int[] input) {
		if (input == null || input.length == 0) {
			return new int[0];
		}

		int[] result = new int[input.length];
		result[input.length - 1] = input[input.length - 1];
		for (int i = input.length - 2; i >= 0; i--) {
			result[i] = Math.max(result[i + 1], input[i]);
		}
		return result;
	}

	public static int waterTrappedOptimal(int[] input) {
		int result = 0;
		
		if (input == null || input.length == 0) {
			return result;
		}
		
		int left = 0;
		int right = input.length - 1;
		int leftmax = input[left];
		int rightmax = input[right];
		
		while (left <= right) {
			leftmax = Math.max(leftmax, input[left]);
			rightmax = Math.max(rightmax, input[right]);
			if (leftmax <= rightmax) {
				result += leftmax - input[left];
				left++;
			} else {
				result += rightmax - input[right];
				right--;
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		System.out.println(maxTrapped(new int[0]));
		System.out.println(waterTrappedI(new int[0]));
		System.out.println(waterTrappedDP(new int[0]));
		System.out.println(waterTrappedOptimal(new int[0]));
		System.out.println();

		System.out.println(maxTrapped(new int[] {3}));
		System.out.println(waterTrappedI(new int[] {3}));
		System.out.println(waterTrappedDP(new int[] {3}));
		System.out.println(waterTrappedOptimal(new int[] {3}));
		System.out.println();

		System.out.println(maxTrapped(new int[] {3, 2, 5}));
		System.out.println(waterTrappedI(new int[] {3, 2, 5}));
		System.out.println(waterTrappedDP(new int[] {3, 2, 5}));
		System.out.println(waterTrappedOptimal(new int[] {3, 2, 5}));
		System.out.println();

		System.out.println(maxTrapped(new int[] {2,1,3,4,5,2,6}));
		System.out.println(waterTrappedI(new int[] {2,1,3,4,5,2,6}));
		System.out.println(waterTrappedDP(new int[] {2,1,3,4,5,2,6}));
		System.out.println(waterTrappedOptimal(new int[] {2,1,3,4,5,2,6}));
		System.out.println();

		System.out.println(maxTrapped(new int[] {4,1,3,4,5,2,6}));
		System.out.println(waterTrappedI(new int[] {4,1,3,4,5,2,6}));
		System.out.println(waterTrappedDP(new int[] {4,1,3,4,5,2,6}));
		System.out.println(waterTrappedOptimal(new int[] {4,1,3,4,5,2,6}));
	}
}
