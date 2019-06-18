package string;

/**
 * unsorted array
 * move 0s to the end
 * keep the order of non-zero elements
 * */
public class MoveZerosII {

	public int[] moveZeros(int[] array) {
	    if (array == null) {
	      return array;
	    }
	    // [0,s-1] non-zeros
	    // f: to examine
	    int s = 0;
	    int f = 0;
	    while (f < array.length) {
	    	if (array[f] != 0) {
	    		array[s++] = array[f];
	    	}
	    	f++;
	    }
	    
	    // pad with 0s
	    while (s < array.length) {
	    	array[s++] = 0;
	    }
	    
	    return array;
	}
}
