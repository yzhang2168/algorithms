package algorithms.binarysearch;

public class MedianInTwoSortedArrays {

	public static double median(int[] a, int[] b) {
		if (a == null || a.length == 0) {
			double median = b.length % 2 == 0 ? (b[b.length / 2 - 1] + b[b.length / 2]) / 2.0 : b[b.length / 2] * 1.0;
			return median;
		}

		if (b == null || b.length == 0) {
			double median = a.length % 2 == 0 ? (a[a.length / 2 - 1] + a[a.length / 2]) / 2.0 : a[a.length / 2] * 1.0;
			return median;
		}

		if ((a.length + b.length) % 2 == 0) {
			int leftMedian = kth(a, 0, b, 0, (a.length + b.length) / 2);
			int rightMedian = kth(a, 0, b, 0, (a.length + b.length) / 2 + 1);
			return (leftMedian + rightMedian) / 2.0;
		} else {
			return (double) kth(a, 0, b, 0, (a.length + b.length) / 2 + 1);
		}
	}

	private static int kth(int[] a, int aleft, int[] b, int bleft, int k) {
		if (aleft > a.length - 1) {
			return b[bleft + k - 1];
		} else if (bleft > b.length - 1) {
			return a[aleft + k - 1];
		} else if (k == 1) {
			return Math.min(a[aleft], b[bleft]);
		}

		int amid = aleft + k / 2 - 1;
		int bmid = bleft + k / 2 - 1;
		int aval = amid < a.length ? a[amid] : Integer.MAX_VALUE;
		int bval = bmid < b.length ? b[bmid] : Integer.MAX_VALUE;
		if (aval <= bval) {
			return kth(a, amid + 1, b, bleft, k - k / 2);
		} else {
			return kth(a, aleft, b, bmid + 1, k - k / 2);
		}
	}

	public static void main(String[] args) {
		int[] a = { 1 };
		int[] b = { 2, 3, 4 };
		int[] c = { 0, 1 };
		System.out.println(median(a, b));
		System.out.println(median(b, c));
	}
}
