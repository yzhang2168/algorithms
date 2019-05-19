package algorithms;

import java.util.Random;

public class Numbers {
	// a random number within the range of [a, b] with equal probabilities.
	public int random(int a, int b) {
		Random rand = new Random();
		return a + rand.nextInt(b - a + 1);
	}

	public static boolean areEqual(double x, double y) {
		boolean b = (x - y < 0.0001) && (y - x < 0.0001);
		return b;
	}

	// division w/o losing precision
	public static double division(int a, int b) {
		//double c = 1.0 * a / b;
		// or use casting
		double c = (double) a / b;
		return c;
	}
	
	public static boolean isPrime(int n) {
	    // assume n >= 2
		if (n < 2) {
			return false;
		}
		
		// be sure to include =
		// 3*3 = 9, if '=' is left out, 9 returns true 
	    for (int i = 2; i * i <= n; i++) {
	        if (n % i == 0) {
	        	return false;
	        }	        
	    }  
	    return true;
	}

	// assumption: n >= 1
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

	public static long factorial2(int n) {
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

	public static long factorial3(int n) {
		// input validation
		if (n < 1) {
			return -1;
		}
		return factorial_tail(n, 1);
	}

	// tail recursion: does not use stack to memorize the previous values
	// passes an intermediate result along with the next value of N.
	private static long factorial_tail(int n, long temp) {
		if (n == 1) {
			return temp;
		}
		return factorial_tail(n - 1, temp * n); 
	}
}
