package algorithms.random;


public class RandomSampling {

	/**
	 * Consider an unlimited flow of data elements. 
	 * How do you sample one element from this flow, such that at any point during the processing of the flow, 
	 * you can return a random element from the n elements read so far.
	 * You will implement two methods for a sampling class:
	 * read(int value) - read one number from the stream
	 * sample() - return at any time the sample, if n values have been read, 
	 * 		the probability of returning any one of the n values is 1/n, 
	 * 		return null(Java)/INT_MIN(C++) if there is no value read so far
	 * You may need to add more fields for the class.
	 * 
	 * space limit: O(1)
	 * stream = [a, b, c...]
	 * data structure
	 * 		count: # elements read so far
	 * 		sample: uniformly randomly picked sample from the elements read so far
	 * way of thinking: induction
	 * step 1: counter = 1
	 * 			sample = a
	 * 			sample must be a, since there is only one element read so far
	 * 
	 * step 2: counter = 2
	 * 			p(sample = b) = 1/2: replace sample a with b with 1/2 probability
	 * 			p(sample = a) = 1 - 1/2 = 1/2
	 * 
	 * step 3: counter = 3
	 * 			p(sample = c) = 1/3: replace curr sample with c with 1/3 probability
	 * 			p(sample = a): p(after step 2, sample = a) * p(at step 3, a is not replaced) = 1/2 * (1 - 1/3) = 1/3
	 * 			p(sample = b): p(after step 2, sample = b) * p(at step 3, b is not replaced) = 1/2 * (1 - 1/3) = 1/3
	 * 
	 * step n: counter = n
	 * 			replace sample with the new element with 1/n probability
	 * 
	 * 1/n probability: random(counter) returns 0...counter - 1
	 * */
	private int count;
	private Integer sample;

	public RandomSampling() {
		this.count = 0;
		this.sample = null;
	}

	public void read(int value) {
		count++;
		//Random rand = new Random();
		//int randomNumber = rand.nextInt(count); 
		// Math.random() returns a double in [0.0...1.0)
		int randomNumber = (int) (Math.random() * count);
		// 1 / n probability
		if (randomNumber == 0) { 
			sample = value;
		}
	}

	public Integer sample() {
		return sample;
	}


	public static void main(String[] args) {
		RandomSampling r = new RandomSampling();
		int[] input = {1, 2, 3, 4, 5, 6, 7};
		for (int i : input) {
			r.read(i);
			System.out.println(r.sample());
		}
	}
}
