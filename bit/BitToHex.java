package algorithms.bit;

public class BitToHex {
	
	/**
	 * assumption: non-negative
	 * more general
	 * num / base; num % base
	 * */
	public static String toHex1(int num) {
		// corner
		if (num == 0) {
			return "0x0";
		}
		
		char[] base16 = {'0', '1', '2', '3', '4', '5', '6', '7', 
						 '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuilder result = new StringBuilder();
		
		while (num > 0) {
			int digit = num % 16;
			result.append(base16[digit]);
			num = num / 16;
		}
		result.append("x0");
		
		return result.reverse().toString();
	}
	
	/**
	 * 0b b31 b30 b29 b28 ... b3  b2  b1  b0	num
	 * 
	 * 0b b31 b31 b31 b31 ... b31 b30 b29 b28	(num >> 28)
	 * 0b   0   0   0   0 ...   1   1   1   1	15 (0xF)
	 * 0b   0   0   0   0 ... b31 b30 b29 b28	(num >> 28) & 15
     */
	public static String toHex2(int num) {
		
		char[] base16 = {'0', '1', '2', '3', '4', '5', '6', '7', 
						 '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuilder result = new StringBuilder("0x");
		boolean seenNonzero = false;

		for (int i = 28; i >= 0; i = i - 4) {
			int digit = (num >> i) & 15;
			
			if (digit != 0) {
				seenNonzero = true;
				result.append(base16[digit]);
			} else if (digit == 0 && seenNonzero) {
					result.append(base16[digit]);
			}				
		}
		
		if (!seenNonzero) {
			result.append('0');
		}
		
		return result.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(toHex1(0b0000));// 0
		System.out.println(toHex1(0b0100));// 4
		System.out.println(toHex1(0b1111));// 15
		System.out.println(toHex1(0b01000000));// 64
		System.out.println(toHex1(0b11111111));//255
		System.out.println();

		System.out.println(toHex2(0b0000));// 0
		System.out.println(toHex2(0b0100));// 4
		System.out.println(toHex2(0b1111));// 15
		System.out.println(toHex2(0b01000000));// 64
		System.out.println(toHex2(0b11111111));//255
		System.out.println();
	}	

}
