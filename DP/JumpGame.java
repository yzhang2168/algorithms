package DP;

public class JumpGame {

	/**
	 * Given an array A of non-negative integers, you are initially positioned at index 0 of the array. 
	 * A[i] means the maximum jump distance from that position 
	 * (you can only jump towards the end of the array). 
	 * Determine if you are able to reach the last index.
	 * index0, 1, 2, 3, 4
	 * a = {2, 3, 1, 1, 4} true
	 * b = {3, 2, 1, 0, 4} false
	 * m[]={F, F, F, F, T}
	 * 					<--
	 * 
	 * linear scan right to left, look back to all possible jump positions
	 * base case: m[4] = true, because m[4] is target itself
	 * induction rule:
	 * 		m[i] represents whether I can jump from input[i] to the end
	 * 		m[i] = true, if i > 0 and there exists m[i + 1] ... m[i + input[i]] == true
	 * 		m[i] = false, otherwise
	 * Time: O(n ^ 2)
	 * */
	public static boolean canJump(int[] input) {
		if (input == null || input.length == 1) {
			return true;
		}

		int n = input.length;
		boolean[] m = new boolean[n];
		m[n - 1] = true;

		for (int i = n - 2; i >= 0; i--) {
			for (int j = i + 1; j <= i + input[i]; j++) {
				if (j >= n || m[j]) { // out of bound
					m[i] = true;
					break;
				}
			}
		}

		return m[0];
	}

	// min number of jumps
	// return -1 if not able to reach the last index 
	// linear scan right to left
	public static int minJumps(int[] input) {
		if (input == null || input.length == 1) {
			return 0;
		}

		int n = input.length;
		int[] m = new int[n];

		// base case
		m[n - 1] = 0;
		// induction rule:
		//	m[i]: min jumps from i to target/last index
		//  m[i] = 1 + min(m[i+1]...m[i + input[i]])
		//  m[i] = -1 if cannot jump from i to last index
		for (int i = n - 2; i >= 0; i--) {
			// init: -1 for unreachable
			int currMin = -1;
			for (int j = i + 1; j <= i + input[i]; j++) {
				if (j >= n) { // index out of bound
					currMin = 1;
					break;
				} else if (m[j] != - 1) {
					int temp = 1 + m[j];
					if (currMin == -1 || temp < currMin) {
						currMin = temp;
					}
				}
			}
			// if i == i + input[i], unreachable m[i] = -1
			m[i] = currMin;			
		}

		return m[0];	
	}	

	// linear scan left to right
	public static int minJumpsI(int[] input) {
		if (input == null || input.length == 1) {
			return 0;
		}

		int n = input.length;
		int[] m = new int[n];

		// base case: m[0] = 0
		m[0] = 0;

		// induction rule:
		// m[i]: min jumps from 0 to i 
		// m[i] = 1 + min( m[0] ... m[i - 1] if )
		for (int i = 1; i < n; i++) {
			// init: -1 for unreachable
			m[i] = -1;
			// look back at 0...i - 1
			for (int j = 0; j <= i - 1; j++) {
				// reachable from j to i, take min of all possible # jumps
				if (m[j] != -1 && j + input[j] >= i) {
					if (m[i] == -1 || m[i] > m[j] + 1) {
						m[i] = m[j] + 1;
					}
				}
			}
		}
		return m[n - 1];
	}	

	public static void main(String[] args) {
		int[] a = {0, 0, 6, 0, 0};
		int[] b = {3, 2, 1, 0, 4};
		int[] c = {2, 3, 1, 1, 4};
		int[] d = {1, 3, 0, 0, 0};
		int[] e = {4, 2, 1, 2, 0, 0};
		System.out.println(canJump(a));
		System.out.println(canJump(b));
		System.out.println(canJump(c));
		System.out.println(canJump(d));
		System.out.println(canJump(e));
		System.out.println(minJumps(a));
		System.out.println(minJumps(b));
		System.out.println(minJumps(c));

		System.out.println(minJumpsI(a));
		System.out.println(minJumpsI(b));
		System.out.println(minJumpsI(c));
	}
}
