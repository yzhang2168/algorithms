package algorithms.bit;

public class BitPowerOf2 {
	/**
	 * if n is a power of 2, its binary has only one 1
	 * exception: min_value 1000000000 negative
	 * O(32)
	 * */
	public static boolean isPowerOfTwo0(int n) {
		if (n <= 0) {
			return false;
		}
		return countOnes(n) == 1;
	}
	
	/**
	 * a better solution
	 * x & (x - 1) removes the rightmost 1
	 * x is a power of 2: 0b0010 0000
	 * x - 1: 			  0b0001 1111
	 * x & (x - 1): 	  0b0000 0000
	 * 
	 * x is not a power of 2: 0b0010 1000
	 * x - 1:   			  0b0010 0111
	 * x & (x - 1): 		  0b0010 0000
	 * 
	 * exception: 0
	 */
	public static boolean isPowerOfTwo1(int n) {
		return (n > 0) && ((n & (n - 1)) == 0);
	}
	
	public static int countOnes(int n) {
		int result = 0;
		while (n != 0) {
			result += n & 1;
			n = n >>> 1;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int x = 0b1010;
		int y = 0b1000;
		System.out.println(countOnes(x));
		System.out.println(countOnes(y));
		System.out.println();

		System.out.println(isPowerOfTwo0(0));
		System.out.println(isPowerOfTwo0(-1));
		System.out.println(isPowerOfTwo0(x));
		System.out.println(isPowerOfTwo0(y));
		System.out.println();

		System.out.println(isPowerOfTwo1(0));
		System.out.println(isPowerOfTwo1(-1));
		System.out.println(isPowerOfTwo1(x));
		System.out.println(isPowerOfTwo1(y));
	}
}
