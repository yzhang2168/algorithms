package bit;

public class BitRepresentation {
	public void printBinary(int value) {
		System.out.println(value + ":");
		StringBuilder sb = new StringBuilder();
		for (int i = 31; i >= 0; i--) {
			sb.append((value >>> i) & 1);
			// >>>: unsigned shift, left bits padded with 0s
			// x & 1: x itself 
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		BitRepresentation test = new BitRepresentation();
		test.printBinary(5);
		test.printBinary(-5);
	}

}
