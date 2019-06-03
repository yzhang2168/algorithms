package bit;

public class BitReverseBits {

	/**
	 * 	      j				 i
	 * 0b b7 b6 b5 b4 b3 b2 b1 b0
	 * 	  b7  1 b5 b4 b3 b2  0 b0
	 * 	   0  1  0  0  0  0  0  0  1 << j
	 * 	  b7  0 b5 b4 b3 b2  0 b0  x ^ (1<<j) -> ~b6 only
	 * 
	 * 	   0  0  0  0  0  0  1  0  1 << i
	 * 	  b7  0 b5 b4 b3 b2  1 b0  x ^ (1<<i) -> ~b1 only
	 * 
	 * case1: b1 == b6, do nothing
	 * case2: b1 != b6, ~b1, ~b6, all other bits remain the same
	 * how to NOT b6 only (~b6), keep all other bits?
	 * bi ^ 1 -> ~bi, bi ^ 0 -> bi
	 * use XOR: x = x ^ (1 << 6)
	 * */
	public static int reverseBits(int x) {
		int i = 0;
		int j = 31;
		while (i < j) {
			x = reverseBitPair(x, i, j);
			i++;
			j--;
		}
		// x is primitive, needs to return
		return x;
	}
	
	private static int reverseBitPair(int x, int i, int j) {
		int leftBit = (x >> j) & 1;
		int rightBit = (x >> i) & 1;
		if (leftBit != rightBit) {
			x = x ^ (1 << i | 1 << j);
			//x = x ^ (1 << j);
			//x = x ^ (1 << i);
		}
		return x;
	}
	
	public static int reverseBitsII(int x) {
		int temp = 0;
		for (int i = 0; i < 32; i++) {
			temp = (temp << 1) + ((x >> i) & 1); 
		}
		return temp;
	}
	
	public static void main(String[] args) {
		int x = 0b1010;
		int y = 0b0001;
		int z = 0b0100;
		System.out.println(Integer.toBinaryString(x ^ y));
		System.out.println(Integer.toBinaryString(x ^ z));
		
		System.out.println(Integer.toBinaryString(x | y));
		System.out.println(Integer.toBinaryString(x | z));

		System.out.println(Integer.toBinaryString(x + y));
		System.out.println(Integer.toBinaryString(x + z));

		System.out.println(Integer.toBinaryString(reverseBits(x)));
		System.out.println(Integer.toBinaryString(reverseBitsII(x)));
		System.out.println(Integer.toBinaryString(reverseBits(y)));
		System.out.println(Integer.toBinaryString(reverseBitsII(y)));
		System.out.println(Integer.toBinaryString(reverseBits(z)));
		System.out.println(Integer.toBinaryString(reverseBitsII(z)));
	}	

}
