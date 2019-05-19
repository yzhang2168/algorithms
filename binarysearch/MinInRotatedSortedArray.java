package algorithms.binarysearch;

/**
 * [6,7,8,|3,4,5]
 * [3,4,5,6,7,8]
 * There is no target, compare m with what?
 * compare with right, we will know which side to search. compare with left, we cannot reduce the search space by half. 
 * if input[m] > input[r], solution/turning point is to the right of m, l = m + 1
 * if input[m] < input[r], solution/turning point is m or left of m, r = m
 * */
public class MinInRotatedSortedArray {

	public static int minRotatedArray(int[] input) {
		if (input == null || input.length == 0) {
			return -1;
		}
		
		int l = 0;
		int r = input.length - 1;
		while (l < r) {
			int m = l + (r - l) / 2;
			if (input[m] > input[r]) {
				l = m + 1;
			} else { // <, no duplicates
				r = m;
			}
		}
		// 1 element left
		return l;
	}
	
	/**
	 * [3333333333]		O(n)
	 * [3333345673]
	 * [4563333333]
	 *  l   m    r
	 * if input[m] == input[r], not able to tell if min is input[m], or to the right or the left of m
	 * but we can exclude r, because even if min == input[r], we still have input[m] and won't lose min
	 * therefore, we can r-- to reduce the search space
	 * */
	public static int minDuplicateRotatedArray(int[] input) {
		if (input == null || input.length == 0) {
			return -1;
		}
		
		int l = 0;
		int r = input.length - 1;
		while (l < r) {
			int m = l + (r - l) / 2;
			if (input[m] > input[r]) {
				l = m + 1;
			} else if (input[m] < input[r]) {
				r = m;
			} else { // == duplicates
				r--;
			}
		}
		// 1 element left
		return input[l];
	}
	
	public static void main(String[] args) {
		System.out.println(minRotatedArray(new int[] {6,7,8,9,1,2}));
		System.out.println(minRotatedArray(new int[] {1,2,6,7,8,9}));
		System.out.println(minDuplicateRotatedArray(new int[] {3,3,3,3,3,3}));
		System.out.println(minDuplicateRotatedArray(new int[] {3,3,3,3,4,5}));
		System.out.println(minDuplicateRotatedArray(new int[] {4,5,3,3,3,3}));
	}
}
