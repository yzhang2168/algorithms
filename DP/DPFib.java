package algorithms.DP;

public class DPFib {

	public static long fibI(int n) {
		long[] fibFound = new long[n + 1];
		fibFound[0] = 0;
		fibFound[1] = 1;
		
		// linear scan, look back to previous two
		for (int i = 2; i <= n; i++) {
			fibFound[i] = fibFound[i - 1] + fibFound[i - 2];
		}
		return fibFound[n];
	}
	
	/**
	 * space: optimized to O(1)
	 * */ 
	public static long fibII(int n) {
		if (n == 0 || n == 1) {
			return n;
		}
		
		long first = 0;
		long second = 1;
		
		// linear scan, look back to previous two
		for (int i = 2; i <= n; i++) {
			long temp = first + second;
			first = second;
			second = temp;
		}
		return second;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(fibI(10));
		System.out.println(fibII(10));
		System.out.println();
		
		System.out.println(fibI(100));
		System.out.println(fibII(100));
		System.out.println();
	}
}
