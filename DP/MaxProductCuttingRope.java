package algorithms.DP;

public class MaxProductCuttingRope {

	/**
	 * given a rope of integer length n, how to cut the rope into m integer-length parts
	 * in order to get the max product of p[0] * p[1] *...* p[m - 1]
	 * m is determined by you and must be > 0 - at least 1 cut must be made 
	 * 
	 * 
	 * solution 0: DFS recursion
	 * maxProduct definition: at least 1 cut
	 * maxProduct(4): does not cover 4 meters no cut
	 * therefore, we take max(4, maxProduct(4)) to cover 4 meters case
	 * 
	 * max(3, maxProduct(3))*2	rather than max(3, maxProduct(3)) * max(2, maxProduct(2)): also correct
	 * but no need to cut the rightside
	 * 
	 *													maxProduct(5)
	 *						/					/							\				\
	 * max(4, maxProduct(4))*1		max(3, maxProduct(3))*2		max(2, maxProduct(2))*3		max(1, maxProduct(1))*4
	 * 
	 * # levels: n
	 * each level: 1 cut only, in case of multiple cuts in the result, the order of cuts does not matter 
	 * each branch: the rightmost cut
	 * leftside: recursion calls to find max, rightside: no cut anymore
	 * 
	 * time: O(2 ^ n)
	 * */
	
	public static int getMaxProductRecursion(int n) {
		// base case: n == 1 undefined, can return anything
		// in the parent call, max(0, x) -> x
		if (n <= 1) {
			return 0;
		}
		
		// local var, overwritten by calculated maxProduct
		int maxProduct = 0;
		for (int i = 1; i < n; i++) {
			// left: n - i meters; right: i meters
			int leftMax = Math.max(n - i, getMaxProductRecursion(n - i));
			// rightMax = i;
			if (leftMax * i > maxProduct) {
				maxProduct = leftMax * i;
			}			
		}
		
		// pass value to parent call 
		return maxProduct;
	}
	
	/**
	 * subproblem of size n:
	 * problem of size n + 1: 1 new cut in n + 1, n cases 
	 * time: O(n ^ 2)
	 * 
	 * 左大段 + 右大段(have cuts, so look up table)
	 * */
	public static int getMaxProductDP(int n) {
		int[] m = new int[n + 1];
		// base case: n == 1 undefined, can set it to anything
		m[0] = 0;
		m[1] = 0;
		
		// solve problems of length from 2 to n
		for (int i = 2; i <= n; i++) { 
			int currMax = 0;
			// j: position for the only new cut
			for (int j = 1; j <= i / 2; j++) { // mirror, only calculate half
				int currCase = Math.max(j, m[j]) * Math.max((i - j), m[i - j]);
				currMax = Math.max(currMax, currCase);
			}
			m[i] = currMax;
		}
		
		return m[n];
	}	
	
	
	//左大段(cuts, look up table) + 右小段(no cuts)
	public static int getMaxProductDPII(int n) {
		int[] m = new int[n + 1];
		// base case: n == 1 undefined, can set it to anything
		m[0] = 0;
		m[1] = 0;
		
		// solve problems of length from 2 to n
		for (int i = 2; i <= n; i++) { 
			int currMax = 0;
			// j: position for the only new cut
			for (int j = 1; j < i; j++) {
				int currCase = Math.max(j, m[j]) * (i - j);
				currMax = Math.max(currMax, currCase);
			}
			m[i] = currMax;
		}
		
		return m[n];
	}	
	
	
	public static void main(String[] args) {
		System.out.println(getMaxProductRecursion(3));
		System.out.println(getMaxProductDP(3));
		System.out.println(getMaxProductDP(25));
		System.out.println(getMaxProductDPII(25));	
	}
}
