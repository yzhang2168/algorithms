package algorithms.binarysearch.array_unknown_size;

import java.util.Arrays;

public class DictionaryUnknownSize implements Dictionary {
	private int[] array;
	// we do not make length() public

	public DictionaryUnknownSize(int[] array) {
		this.array = array;
	}

	@Override
	public Integer get(int index) {
		if (array == null || index >= array.length) {
			return null;
		} else {
			return array[index];
		}
	}

	@Override
	public String toString() {
		if (array == null) {
			//return String.valueOf(null); // .valueOf() does not take null as parameter  
			return "null";
		}

		if (array.length <= 10) {
			return Arrays.toString(array);
			// for longer array, print start and end elements
		} else {
			StringBuilder sb = new StringBuilder("[");
			for (int i = 0; i < 3; i++) {
				sb.append(array[i]).append(", ");
			}

			sb.append("..., ");
			for (int i = array.length - 3; i < array.length; i++) {
				sb.append(array[i]);
				if (i != array.length - 1) {
					sb.append(", ");
				}
			}
			sb.append("]");
			return sb.toString();			
		}		 
	}

	public int search(int target) {
		if (array == null) {
			return -1;
		}

		int left = 0;
		int right = 1;
		// to avoid exception, use this.get() instead of this[index]
		while (this.get(right) != null && get(right) < target) {
			left = right;
			right = right * 2;
		}

		// this.get(right) == null || this.get(right) >= target
		return binarySearch(target, left, right);
	}

	private int binarySearch(int target, int left, int right) {		
		while (left <= right) {
			int mid = left + (right - left) / 2;
			// deal with null
			if (get(mid) == null || get(mid) > target) {
				right = mid - 1;
			} else if (get(mid) < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;	
	}


	public static void main(String[] args) {
		int[] arr = new int[0]; // empty, cannot use arr[0] index out of bound

		Dictionary n = new DictionaryUnknownSize(null);
		System.out.println(n);

		DictionaryUnknownSize d = new DictionaryUnknownSize(new int[] {1,2,3,4,5,6,7,8,9,10,11});
		System.out.println(d);
		System.out.println(d.search(0));		
		System.out.println(d.search(11));		
		System.out.println(d.search(1111));		
	}
}
