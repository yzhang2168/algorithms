package algorithms.binarysearch;

/* XXXXXXXXYYYYYYYYY
 * l	    m	   r
 * 
 * while (l < r, l <= r, l < r - 1) 
 * 如果不知道用哪个，可以代入3种情况
 * l <= r: 1 element
 * 	update rule r = m --> infinite loop
 * 
 * l < r: 2 elements
 * 	X Y
 * 	l r
 * 	m
 *  update rule --> either way leaves 1 element left to be examined --> post processing
 * 
 * l < r - 1: 3 elements
 * 	update rule --> leaves either 1 or 2 elements
 * */
public class FirstBadVersion {

	public static int firstBadVersion(int n) {
		if (n <= 0) {
			return -1;
		}
		
		int l = 1;
		int r = n;
		while (l < r) {// l <= r - infinite loop, l < r - 1
			 int m = l + (r - l) / 2;
			 if (isBadVersion(m) == true) {
				 r = m; 
			 } else {
				 l = m + 1;
			 }
		}
		
		// 1 element (l == r) left that needs to be evaluated 
		if (isBadVersion(l)) {
			return l;
		} else { // not found because excluded elements are not the solution
			return -1;			
		}
	}
	
	private static boolean isBadVersion(int index) {
		return index == 0; // WRONG, just a placeholder
	}
}
