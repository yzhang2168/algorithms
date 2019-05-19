package algorithms.random;

public class Random7 {

	/**
	 * Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. 
	 * Use random5() to implement random7().
	 * */
	public int random7() {
		/**
		 * Imagine what happens if `random25` is larger than or equal to 21 (without having the `while (true)`-loop)... 
		 * In this case the `return random25 % 7` statement would not be reached and the method would be 
		 * exited through the implicit `return` statement at the end... 
		 * but the implicit `return` statement does not return a integer, and so is in conflict with the signature of the method.
		 * So, the only way to make this code work both from algorithm point of view and compiler, 
		 * is to put it into an infinite loop, which will be exited with a return statement (which returns a integer).
		 * I.e. if `random25` is larger than or equal to 21, the code will simple enter another round of the while-loop, 
		 * until `random25` is smaller than 21, in which case the while loop is aborted by returning a integer.
		 * */
		while (true) {
			// to generate a uniformly distributed number in 0-24
			int random25 = RandomFive.random5() * 5 + RandomFive.random5();
			if (random25 < 21) { // a uniformly distributed number in 0-21
				return random25 % 7;
				//return random25 / 3; // 0-2: 1, 3-5: 1...
			}
		}  
	}
	
	/**
	 * random5() * random5() ... till reaching 1000
	 * 5 * 5 * 5 * 5 * 5 = 3125 > 1000
	 * discard 3000-3125 because they will cause the probabilities of taking 0...124 higher than the rest of 125...999
	 * we can optimize by taking [0-3000), 3 in a group
	 * */
	public int random1000() {
		while (true) {
			int random3125 = 0;
			for (int i = 0; i < 5; i++) {
				random3125 = random3125 * 5 + RandomFive.random5();
			}
			if (random3125 < 3000) {
				return random3125 % 1000;
			}
		}
	}
}
