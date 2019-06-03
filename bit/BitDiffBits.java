package bit;

public class BitDiffBits {
	
	// O(32)
	public static int countDifferentBits(int a, int b) {
		int c = a ^ b;
		return countOnes(c);
	}
	
	public static int countOnes(int n) {
		int count = 0;
		// termination: n == 0
		while (n != 0) {
			count += n & 1;
			n = n >>> 1; // pad with 0s
		}
		return count;
	}
	
	public static void main(String[] args) {
		int x = 0b101;
		int y = 0b110;
		int z = 0b010;
		int a = -1;
		int b = 2147483647;
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE ^ (-1)));
		System.out.println(countDifferentBits(x, y));
		System.out.println(countDifferentBits(x, z));
		System.out.println(countDifferentBits(y, z));
		System.out.println(countDifferentBits(a, b));
	}
}
