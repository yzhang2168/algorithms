package algorithms;

public class Power {
    
	public static double power(int a, int b) {
		// base case
		if (a == 0) {
			return 0.0;
		} else if (b > 0) {
			return 1.0 * powerHelper(a, b);
		} else {
			return 1.0 / powerHelper(a, -b);
		}
	}
	
	private static long powerHelper(int a, int b) {
		if (b == 0) {
			return 1;
		}
		
		long half = power4(a, b / 2); 
		if (b % 2 == 0) {
			if (a > 0) {
				return half * half;
			} else {
				return -half * half;
			}
		} else {
			return a * half * half;
		}
	}
	
	/**
     * given int a & b, b >= 0
     * returns a^b
     * exponential growth, cast result to long to avoid int overflow 
     * */
	
	// iterative
	// time:  O(b)  
	// space: O(1)
	public static long power1(int a, int b) {
		long result = 1;
    	for (int i = 0; i < b; i++) {
    		result *= a;
    	}
    	return result;
    }
	
	// recursion1 - worse than iterative
	// time:  O(b)  
	// space: O(b)	(heap: 0, stack: recursion tree height)
	public static long power2(int a, int b) {
		// base case
		if (b == 0) {
			return 1;
		}		
    	return power2(a, b-1) * a;
	}
	
	// recursion2 - worse than iterative
	// time:  O(2^logb last level) = O(b)  
	// space: O(logb) (heap: 0, stack: recursion tree height)	
	public static long power3(int a, int b) {
		// base case
		if (b == 0) {
			return 1;
		}
		
		if (b % 2 == 0) {
    	    return power3(a, b/2) * power3(a, b/2);
		} else {
			return power3(a, b/2) * power3(a, b/2) * a;
		}
	}
	
	// recursion3 - exchange space for time 
	// time:  O(logb)
	// space: O(logb) (heap: 0, stack: recursion tree height)
	// compared with iterative (time O(b), space O(1)), b --> logb is a dramatic decrease
    // e.g., log(10^3) -> 10, log(1 billion) = log(10^3)^3 = 3log(10^3) -> 30
	// logb --> 1 is a small increase
	public static long power4(int a, int b) {
		// base case
		if (b == 0) {
			return 1;
		}			
		
		long half = power4(a, b / 2); 
		return b % 2 == 0 ? half * half : a * half * half;
	}
	
	
	public static void main(String[] args) {
		int a = -2, b = 4;
		System.out.println(power(-2, 0));
		System.out.println(power(-2, 4));
		System.out.println(power(-2, -4));
        System.out.println(power(-2, 3));
        System.out.println(power(-2, -3));
		System.out.println(power1(a, 0));
		System.out.println(power1(a, 1));
		System.out.println(power1(a, b));
		System.out.println(power2(a, b));
		System.out.println(power3(a, b));
		System.out.println(power4(a, b));
        System.out.println(power4(a, -4));
	}
	
}
