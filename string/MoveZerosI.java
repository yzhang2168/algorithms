package string;

/**
 * unsorted array
 * move 0s to the end
 * the order of non-zero elements can be changed
 * */
public class MoveZerosI {

	public int[] moveZeros(int[] array) {
	    if (array == null) {
	      return array;
	    }
	    
	    // [0,s-1] non-zeros
	    // f: to examine
	    int s = 0;
	    int f = array.length - 1;
	    while (s < f) {
	    	if (array[s] != 0) {
	    		s++;
	    	} else if (array[f] == 0) {
	    		f--;
	    	} else {
	    		swap(array, s++, f--);
	    	}
	    }
	    
	    return array;
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}