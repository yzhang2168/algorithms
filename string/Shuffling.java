package algorithms.string;

/**
 * given a char[]
 * [c1, c2,...,c2k] -> [c1, ck+1, c2, ck+2,...ck, c2k]
 * [c1, c2,...,c2k+1] -> [c1, ck+1, c2, ck+2,...ck, c2k, c2k+1]
 * */
public class Shuffling {

	public static char[] reorder(char[] input) {
		if (input.length % 2 == 0) {
			reorder(input, 0, input.length - 1);
		} else { // for odd number, ignore the last one during reordering
			reorder(input, 0, input.length - 2);
		}
		return input;
	}
	
	public static void reorder(char[] input, int left, int right) {
		int length = right - left + 1;
		// base case: 0 or 2 elements, just return
		if (length <= 2) {
			return;
		}	
		/**
		 * calculate mid points - important
		 * 0 1 2 3 4 5 6 7
		 *     lm  m   rm
		 * lm: 2, m: 4, rm: 6
		 * 
		 * 0 1 2 3 4 5 6 7 8 9
		 *     lm    m   rm
		 * lm: 2, m: 5, rm: 7
		 * 
		 * A B C D E F G 1 2 3 4 5 6 7
		 * l     lm      m     rm
		 * 
		 * A B C 1 2 3 D E F G 4 5 6 7
		 * l lm  m rmr|l             r
		 * 
		 * A 1 B C 2 3
		 * l r|l     r 
		 * 
		 *     B 2 C 3
		 * */
		 int mid = left + length / 2;
		 int lmid = left + length / 4;
		 int rmid = left + length * 3 / 4;
		 reverse(input, lmid, mid - 1); // I love yahoo -> yahoo love I
		 reverse(input, mid, rmid - 1);
		 reverse(input, lmid, rmid - 1); // DE123 -> 123DE
		 
		 // half of the left partition's size = lmid - left
		 reorder(input, left, left + (lmid - left) * 2 - 1);
		 reorder(input, left + (lmid - left) * 2, right);
	}
	
	private static void reverse(char[] array, int left, int right) {
		while (left < right) {
			char temp = array[left];
			array[left] = array[right];
			array[right] = temp;
			left++;
			right--;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(reorder("ABCDE12345".toCharArray()));
	}
}
