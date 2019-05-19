package algorithms.random;

/**
 * Consider an unlimited flow of data elements. 
 * How do you uniformly and randomly return the max value
 * example: 1, 2, 5a, 3, 4, 5b, 3
 * 	 index: 0  1  2   3  4  5   6
 * return 5a or 5b randomly
 * 
 * data structure:
 * 		max: current max
 * 		count: occurrences of global max read so far
 * 		sample: stores k elements
 * 
 * initialize: max = Integer.MIN_VALUE
 * 			   count = 0
 * 			   sample = null
 * 
 * For each step:
 * 		case 1: new element < max, ignore
 * 		case 2: new element == max, reservoir sampling - count++, replace sample with new element with p = 1/counter
 * 		case 3: new element > max, max = new element, count = 1
 */

public class RandomLargestNumber {
	private Integer sample;
	private int index;
	private int counter; // counter for global max
	private int max;
	
	public RandomLargestNumber() {
		this.sample = null;
		this.index = -1;
		this.counter = 0;
		this.max = Integer.MIN_VALUE;
	}
	
	public void read(int value) {
		index++;
		
		if (value < max) {
			return;
		}
		
		if (value == max) {
			// reservoir sampling
			counter++;
			int random = (int) (Math.random() * counter);
			if (random == 0) {
				sample = index;
			}
		}
		
		if (value > max) {
			max = value;
			counter = 1;
			sample = index;
		}
	}
	
	public int sampleIndex() {
		return sample;
	}
}
