package string;

public class DedupAdjacentIVOptimal {

	/**
	 * "zabbbazw" -> "w"
	 * conceptually build a stack: [0...slow-1] all processed, to keep 
	 * space O(1)
	 * 
	 * */
	public static String dedupAdjacentRepeatedlyOptimal(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		char[] array = input.toCharArray();
		int slow = 1;
		for (int fast = 1; fast < array.length; fast++) {
			if (slow == 0 || array[fast] != array[slow - 1]) {
				array[slow++] = array[fast];
			} else { //if (array[fast] == array[slow - 1]) {
				while (fast + 1 < array.length && array[fast + 1] == array[slow - 1]) {
					fast++;
				}
				slow--;
			}
		}
		return new String(array, 0, slow);
	}
	
	/**
	 * stack: [0, s]
	 * empty: s = -1
	 * init: s = 0, f = 1
	 * for each step:
	 * 		while a[f] == a[s], stack top, f++
	 * 		a[f] != a[s]: a[++s] = a[f++]
	 * */
	public String deDup(String input) {
	    if (input == null || input.length() <= 1) {
	        return input;
	    }

	    char[] array = input.toCharArray();
	    int end = 0; // stack: a[0]
	    int f = 1;
	    while (f < array.length) {
	        if (end == -1 || array[f] != array[end]) {
	            array[++end] = array[f++];
	        } else {
	            while (f < array.length && array[f] == array[end]) {
	                f++;
	            }
	            // pop stack top
	            end--;	            
	            // f == array.length - already copied the 1st occurrence
	        }
	    }
	    return new String(array, 0, end + 1);
	}

	
	public static void main(String[] args) {
		System.out.println(new String());
		System.out.println(dedupAdjacentRepeatedlyOptimal("zabbbazw"));
	}
}
