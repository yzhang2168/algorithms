package char_int;

public class IntegerComparison {
	/** pitfalls */
	public static void main(String[] args) {
		Integer i1 = null;
		Integer i2 = 0;
		System.out.println(i1 == i2); // false, address comparison, null 
		System.out.println(i1.equals(i2)); // NPE
		System.out.println(i1 == 2); // Integer == int is always numeric comparison, NPE from unboxing null
	}
}
