package binarysearch;

public class KthInTwoSortedArrays {

	public static int kth(int[] a, int[] b, int k) {
		if (a == null || a.length == 0) {
			return b[k - 1];
		} else if (b == null || b.length == 0) {
			return a[k - 1];
		} else if (k <= 0 || k > a.length + b.length) {
			return -1;
		} else {
			return kth(a, 0, b, 0, k);
		}
	}

	private static int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
		// exhausted a, aLeft in the last iteration is a.length - 1
		// base case 1: aLeft == a.length
		if (aLeft >= a.length) {
			return b[bLeft + k - 1];
		} else if (bLeft >= b.length) {
			// base case 2: bLeft == b.length
			return a[aLeft + k - 1];
		} else if (k == 1) {
			// base case 3: 1 left
			return Math.min(a[aLeft], b[bLeft]);
		}

		int aMid = aLeft + k / 2 - 1;
		int bMid = bLeft + k / 2 - 1;

		// if aMid is out of range, delete b's k/2 section
		// by setting bLeft = bMid + 1, aLeft remains the same
		int aVal = aMid < a.length ? a[aMid] : Integer.MAX_VALUE;
		int bVal = bMid < b.length ? b[bMid] : Integer.MAX_VALUE;

		if (aVal <= bVal) {
			return kth(a, aMid + 1, b, bLeft, k - k / 2);
		} else {
			return kth(a, aLeft, b, bMid + 1, k - k / 2);
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 5, 5, 8, 12, 12, 12, 14 };
		int[] b = { 2, 2, 3, 7, 9, 9, 9 };
		System.out.println(kth(null, b, 1));
		System.out.println(kth(a, null, 1));
		System.out.println(kth(a, b, 0));
		System.out.println(kth(a, b, 16));
		System.out.println(kth(a, b, 10));
		System.out.println(kth(a, b, 14));
	}

}
