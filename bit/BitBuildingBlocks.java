package algorithms.bit;

public class BitBuildingBlocks {
	// test if x's kth bit is 1 
	public static boolean bitTester(int x, int k) {
		x = x >> k;
		return (x & 1) == 1; // x's kth bit
	}

	// set x's kth bit to 1
	public static int bitSetter1(int x, int k) {
		int bitSetter = 1 << k;
		x = x | bitSetter;
		return x; // x's kth bit
	}

	// set x's kth bit to 0
	public static int bitSetter0(int x, int k) {
		int bitSetter = 1 << k;
		bitSetter = ~bitSetter;
		x = x & bitSetter;
		return x; // x's kth bit
	}
	
	public static void printBinary(int value) {
		System.out.println(value + ":");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 32; i++) {
			builder.append((value >>> i) & 1); // returns base 10
		}		
		System.out.println(builder.reverse().toString()); 
		System.out.println();
	}

	public static void main(String[] args) {
		
		char x = 'a';
		System.out.println("=========================");
		System.out.println("XOR operations");
		System.out.println("x ^ x: 0");
		System.out.println(x ^ x);
		
		System.out.println("x ^ x ^ x: x");
		System.out.println(x ^ x ^ x);
		
		System.out.println("x ^ 0: x");
		System.out.println(x ^ 0);
		
		System.out.println("x ^ 1: ~last bit");
		System.out.println('a' ^ 1);
		System.out.println('b' ^ 1);
		System.out.println();

		System.out.println("=========================");
		System.out.println("char and short interpretation of binary");
		System.out.println("char and short are 16 bits in Java");
		System.out.println("char: 0...65535 (0...2^16 - 1)");
		System.out.println("short: -32,768...32,767 (-2^15...2^15-1)");
		System.out.println("ASCII: 0-255");
		System.out.println();

		System.out.println("'a' binary:" + Integer.toBinaryString(x));
		System.out.println("'a' integer:" + (int) x);
		System.out.println("'a' short:" + (short) x);
		System.out.println("97 to char:" + (char) 'a');
		System.out.println();

		short a = -1;
		char b = (char) a;
		int c = (int) a;
		System.out.println("short max value:" + (int) (Math.pow(2, 16) - 1)); // 1111...1111
		System.out.println("short max value:" + Integer.toBinaryString((int) Math.pow(2, 16) - 1)); // 0000...1111
		System.out.println("-1 binary:" + Integer.toBinaryString(-1)); // 1111...1111
		System.out.println("short -1 -> int:" + c);
		System.out.println("short -1 -> int:" + Integer.toBinaryString(c));
		System.out.println("short -1 -> char: 1111...1111 is outside of ASCII unprintable:" + b);
		System.out.println("short -1 -> char -> int: " + (int) b);
		System.out.println("short -1 -> int:" + Integer.toBinaryString((int) b));
		System.out.println();
		
		System.out.println("=========================");
		
		int xx = 0b1010;
		int y = 0b1000;
		int z = 0b0100;
		System.out.println(Integer.toBinaryString(xx ^ y));
		System.out.println(Integer.toBinaryString(xx ^ z));
		System.out.println("Getting kth bit");
		System.out.println(bitTester(xx, 3));
		System.out.println(bitTester(xx, 2));
		System.out.println(bitTester(xx, 4));
		System.out.println();
		
		System.out.println("Setting kth bit to 1");
		System.out.println(Integer.toBinaryString(bitSetter1(xx, 2)));
		System.out.println(Integer.toBinaryString(bitSetter1(xx, 1)));
		System.out.println();
		
		System.out.println("Setting kth bit to 0");
		System.out.println(Integer.toBinaryString(bitSetter0(xx, 3)));
		System.out.println(Integer.toBinaryString(bitSetter0(xx, 1)));
	}
}
