package numbers;

public class GoldbachConjecture {
	/**
	 * Every even integer > 2 can be expressed as the sum of 2 primes.
	 * @param n
	 */
	public static void goldbachConjecture(int n) {
		for (int i = 4; i <= n; i = i + 2) {
			if (isCounterExample(i)) {
				System.out.println("You found a counter example!");
				break;
			} else {
				System.out.println(i + " is okay");
     		}
		}
	}
	
	/**
	 * only need to check up to sqrt(n)
	 * n = a * b > a^2 --> a < sqrt(n)
	 * */
	public static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check i and n - i at the same time
	 * only need to check up to n/2
	 * */
	public static boolean isCounterExample(int n) {
		for (int i = 2; i <= n / 2; i++) {
			if (isPrime(i) && isPrime(n - i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int n = 100000;
		goldbachConjecture(n);
	}

}