package comparison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * assumptions: array is not null and length >= 2
 * 
 * a1    a2    a3    a4    a5    a6    a7    a8
 *   \  /       \    /      \    /      \    /
 *    a1          a4          a6          a7
 *        \    /                  \     /
 *          a4                      a6
 *                \           /
 *          		   a6
 * */
public class LargestAndSecondLargest {
	/**
	 * stores the original value
	 * all elements beaten by this element
	 * */	
	class Element {
		int value;
		List<Integer> beaten;
		
		Element(int value) {
			this.value = value;
			this.beaten = new ArrayList<Integer>();
		}		
	}
	
	public int[] largestAndSecondLargest(int[] array) {
		Element[] helper = convert(array);
		
		/**
		 * largerLength: left half partition's length
		 * containing the larger values after each round of comparisons
		 * 
		 * each round of comparison:
		 * compares i with largerLength - 1 - i
		 * swap the larger to i and smaller to largerLength - 1 - i
		 * put the smaller value into the larger value's beaten list
		 * 
		 * termination:
		 * 1 element left
		 * */
		int largerLength = array.length;
		
		while (largerLength > 1) {
			compareAndSwap(helper, largerLength);
			
			// + 1 to include mid element for odd number of elements 
			largerLength = (largerLength + 1) / 2;
		}
		
		return new int[] {helper[0].value, largest(helper[0].beaten)};	
	}
	
	private Element[] convert(int[] array) {
		Element[] helper = new Element[array.length];
		for (int i = 0; i < array.length; i++) {
			helper[i] = new Element(array[i]);
		}
		return helper;
	}
	
	private void compareAndSwap(Element[] helper, int largerLength) {
		// odd number: mid element is not compared
		for (int i = 0; i < largerLength / 2; i++) {
			if (helper[i].value < helper[largerLength - 1 - i].value) {
				swap(helper, i, largerLength - 1 - i);
			}
			helper[i].beaten.add(helper[largerLength - 1 - i].value);
		}
	}
	
	private int largest(List<Integer> list) {
		int max = list.get(0);
		for (Integer i : list) {
			max = Math.max(max,  i);
		}
		return max;
	}
	
	private void swap(Element[] helper, int i, int j) {
		Element tmp = helper[i];
		helper[i] = helper[j];
		helper[j] = tmp;
	}
	
	public static void main(String[] args) {
		LargestAndSecondLargest test = new LargestAndSecondLargest();
		int[] array = {5,4,2,1,3,6};
		int[] output = test.largestAndSecondLargest(array);
		System.out.println(Arrays.toString(output));
	}

}
