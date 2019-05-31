package numbers;

public class Fibonacci {
	/**
	 * fib increases exponentially
	 * use long to avoid int overflow (2 billion)
	 * */ 	

	/**
	 * time:  O(2^n)
			  approximation - a complete binary tree of n levels has 2^n nodes
			  last level n: 2^n
			  sigma(levels 1...n-1): < level n 2^n
			  sigma(levels 1...n-1) + 1 == level n 2^n
			  total < 2 * 2^n
			  so we can only look at the level n - 2^n 
	 * space: heap: 0
			  stack: height of recursion tree, n 
			  total: O(n)
	 * */ 
    public static long fib_recursion(int n) {
    	// input validation
		if (n < 0) { 
        	return -1;
        }
		
		// base case
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fib_recursion(n - 1) + fib_recursion(n - 2);
		}
	}
    
    /**
     * time: O(n)
     * space: result: n + 1
     * */
    public static long fib_dp(int n) {
    	long[] result = new long[n + 1];
    	result[0] = 0;
    	result[1] = 1;
    	
    	for (int i = 2; i <= n; i++) {
    		result[i] = result[i - 1] + result[i - 2];
    	}
    	
    	return result[n];
    }
    
	public static long fib_dp2(int n) {
		/**
		 * time:  O(n)
		 * space: O(1)
		 * */
        // input validation
		if (n < 0) {
        	return -1;
        }
		
        long first = 0;
        long second = 1; 
        if (n == 0 || n == 1) {
			return n;
		} 
        
        for (int i = 2; i <= n; i++) {
	        long temp = first + second;
	        first = second;
	        second = temp;
	    }
	    return second;
	}


	public static void main(String[] args) {
        System.out.println("memoization, recursion");
        System.out.println(fib_dp2(0) + ", " + fib_recursion(0));
        System.out.println(fib_dp2(1) + ", " + fib_recursion(1));
        System.out.println(fib_dp2(2) + ", " + fib_recursion(2));
        System.out.println(fib_dp2(3) + ", " + fib_recursion(3));
        System.out.println(fib_dp2(4) + ", " + fib_recursion(4));
        System.out.println(fib_dp2(5) + ", " + fib_recursion(5));
        System.out.println(fib_dp2(45));// + ", " + fib_recursion(45));
        System.out.println(fib_dp2(46));// + ", " + fib_recursion(46));
        // integer overflow
        System.out.println(fib_dp2(47));// + ", " + fib_recursion(47));
        System.out.println(fib_dp2(50));// + ", " + fib_recursion(50));
	}
}