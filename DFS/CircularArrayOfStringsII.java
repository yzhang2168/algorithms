package algorithms.DFS;

// not optimal solution
// question about return statements????
public class CircularArrayOfStringsII {
	
	public static boolean canCircle(String[] input) {
		if (input == null || input.length == 0) {
			return true;
		}		
		return canCircle(input, 0);
	}

	private static boolean canCircle(String[] input, int level) {
		if (level == input.length) {
			return isCircle(input);
		}
		
		for (int i = level; i < input.length; i++) {
			swap(input, i, level);
			if (canCircle(input, level + 1)) {
				return true;
			}			
			swap(input, i, level);
		}
		return false;
	}
	
	private static void swap(String[] input, int a, int b) {
		String temp = input[a];
		input[a] = input[b];
		input[b] = temp;		
	}
	
	// check if s1 -> s2 -> s3...-> sn can form a circle in order,
	//			^--------------------|			
	private static boolean isCircle(String[] input) {
		if (input == null || input.length <= 1) {
			return true;
		}
		for (int i = 0; i < input.length; i++) {
			String first = input[i];
			String second = input[(i + 1) % input.length];
			if (first.charAt(first.length() - 1) != second.charAt(0)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String[] input1 = {"aaa", "bbb", "baa", "aab"}; 
		System.out.println(canCircle(input1)); // true
		System.out.println(); 

		String[] input2 = {"abc", "cde", "efg", "g"}; 
		System.out.println(canCircle(input2)); // false
		System.out.println(); 

		String[] input3 = {"abc", "cde", "efg", "ga"}; 
		System.out.println(canCircle(input3)); // true
		System.out.println(); 

		String[] input4 = {"aaa", "bbb", "ccc", "ddd"};
		System.out.println(canCircle(input4)); // false
		System.out.println(); 
	}
}
