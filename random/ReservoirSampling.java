package algorithms.random;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider an unlimited flow of data elements. 
 * How do you uniformly and randomly choose k elements from this flow, 
 * such that at any point during the processing of the flow, 
 * you can return k random elements from the n elements read so far.
 * 
 * data structure:
 * 		count
 * 		sample: stores k elements
 * initialize: counter <= k, put everything in the sample
 * prove by induction: probability of any element in the sample is k/counter
 * base case: assume k = 100, counter = 100, correct
 * induction rule: 
 * 		counter = 101
 * 		P(new element in the sample) = 100/101
 * 		P(an old element still in the sample) = P(this element in the sample when counter = 100) * (1 - P(this element gets replaced in step 101))
 * 											  = 100/100 * (1- 100/101 * 1/100) = 100/101
 * 		counter = 103
 * 		P(an old element still in the sample) = P(this element in the sample when counter = 102) * (1 - P(this element gets replaced in step 103))
 * 											  = 100/102 * (1- 1/103) = 100/103
 * 		counter = n
 * 		P = k/n
 * */
public class ReservoirSampling {
	private final int k;
	private int count;
	private List<Integer> sample;
	
	public ReservoirSampling(int k) {
		this.k = k;
		this.count = 0;
		this.sample = new ArrayList<Integer>();
	}
	
	public void read(int value) {
	    count++;
	    if (count <= k) {
	    	sample.add(value);
	    } else {
	    	// for the new element, it should have the probability of k/count to be in the sample reservoir
	    	int random = (int) (Math.random() * count);
	    	// kick out the element at index random, replace it with the new element 
	    	if (random < k) {
	    		/* correct too:
	    		int random2 = (int) (Math.random() * k);
	    		sample.set(random2, value);
	    		*/
	    		sample.set(random, value);
	    	}
	    }
	}
	  
	public List<Integer> sample() {
	    return sample;
	}
}
