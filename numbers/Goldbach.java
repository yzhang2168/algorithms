package numbers;
/**
 * every even integer > 2 can be expressed as the sum of 2 primes
 * */
public class Goldbach {
    public static void main(String[] args) {
    	int n = 10000000;
    	goldbachConjecture(n);    	
    }
    
    public static boolean isPrime(int n) {
    	for (int i = 2; i * i <= n; i++) {
    		if (n % i == 0) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public static boolean counterExample(int n) {
    	for (int i = 2; i <= n / 2; i++) {
    		if (isPrime(i) && isPrime(n - i)) {
    			return false;
    		}
    	}
    	return true;
    }

    public static void goldbachConjecture(int n) {
        for (int i = 4; i <= n; i = i + 2) {
    		if (counterExample(i)) {
    			System.out.println("You found a counter example");
    			break;
    		} else {
    			System.out.println(i + " is okay");
    		}
    	}
    }

}
