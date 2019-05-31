package recursion.math;

public class Power {
	/**
	corner cases
	a = 0, b <= 0: invalid
	a = 0, b > 0: 0
	a != 0, b < 0: 1.0 / (a^(-b))
	 */
	public static double power(int a, int b) {
		if (a == 0) {
			if (b <= 0) {
				//throw new Exception("can only be positive");
			}
			return 0.0;
		}

		if (b >= 0) {
			return (double) powerhelper(a, b);
		} else {
			return 1.0 / powerhelper(a, -b);
		}
	}

	// assumption: b >= 0
	private static long powerhelper(int a, int b) {
		if (b == 0) {
			return 1;
		}

		long half = powerhelper(a, b / 2);
		if (b % 2 == 0) {
			return a > 0 ? (half * half) : -(half * half);
		} else {
			return a * half * half;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(power(0, 0));
		System.out.println(power(0, -1));
		System.out.println(power(0, 1));
		System.out.println(power(0, 2));
		System.out.println(power(-10, 0));
		System.out.println(power(-10, 1));
		System.out.println(power(-10, 2));
		System.out.println(power(-10, 3));
		System.out.println(power(-10, -1));
		System.out.println(power(-10, -2));
	}

}
