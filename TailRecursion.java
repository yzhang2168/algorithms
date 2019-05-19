package algorithms;

public class TailRecursion {
    /**
     * returns n!
     * assumption: n >= 1
     * */
	
	// method1: standard recursion
	public static long factorial1(int n) {
	    // input validation
		if (n < 1) {
	    	return -1;
	    }
		if (n == 1) {
	    	return 1;
	    }
		return factorial1(n-1) * n;		
	}

	// method2: tail recursion
	// does not use stack to memorize the previous values
    // passes an intermediate result along with the next value of n // n - 1
	public static long factorial_tail(int n) {
	    // input validation
		if (n < 1) {
	    	return -1;
	    }
		return factorial_tail(n, 1);
	}
	
	private static long factorial_tail(int n, long temp) {
	    if (n == 1) {
	        return temp;
	    }
	    return factorial_tail(n - 1, temp * n); 
	}

    // method3: iterative
	public static long factorial3(int n) {
	    // input validation
		if (n < 1) {
	    	return -1;
	    }
		long result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;		
	}
	
}
