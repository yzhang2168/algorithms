package algorithms.DFS;


public class CircularArrayOfStrings {
	// optimal solution
	public static boolean canCircle(String[] input) {
		if (input == null || input.length == 0) {
			return true;
		}		
		return canCircle(input, 1);
	}

	private static boolean canCircle(String[] input, int level) {
		if (level == input.length) {
			return chainable(input[level - 1], input[0]);
		}

		for (int i = level; i < input.length; i++) {
			if (chainable(input[level - 1], input[i])) { 
				swap(input, i, level);
				if (canCircle(input, level + 1)) {
					return true;
				}
				swap(input, i, level);
			}
		}
		return false;
	}
	
	public static boolean dfs(String[] input, int level) {				
		if (level == input.length) {
			return chainable(input[level - 1], input[0]);
		}
					
		for (int i = level; i < input.length; i++) {
			boolean chain = true;
			if (level > 0) {
				chain = chainable(input[level - 1], input[i]);
			}
			
			if (chain) {			
				swap(input, i, level);
				boolean found = dfs(input, level + 1);
				if (found) {
					return true;
				} else {
					//discard and continue DFS -> try next input[i]
				}
				swap(input, i, level);
			}			
		}
		return false;
	}


	private static void swap(String[] input, int a, int b) {
		String temp = input[a];
		input[a] = input[b];
		input[b] = temp;		
	}

	private static boolean chainable(String a, String b) {
		return a.charAt(a.length() - 1) == b.charAt(0);		
	}

	public static void main(String[] args) {
		String[] input1 = {"aaa", "bbb", "baa", "aab"}; 
		System.out.println(canCircle(input1)); // true
		System.out.println(dfs(input1, 0)); 
		System.out.println(); 

		String[] input2 = {"abc", "cde", "efg", "g"}; 
		System.out.println(canCircle(input2)); // false
		System.out.println(dfs(input2, 0)); 
		System.out.println(); 

		String[] input3 = {"abc", "cde", "efg", "ga"}; 
		System.out.println(canCircle(input3)); // true
		System.out.println(dfs(input3, 0)); 
		System.out.println(); 

		String[] input4 = {"aaa", "bbb", "ccc", "ddd"};
		System.out.println(canCircle(input4)); // false
		System.out.println(dfs(input4, 0)); 
		System.out.println(); 
		
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 1) {
				System.out.print(i + ",");
			} else {
				//discard and continue DFS -> try next input[i]
			}
		}			
	}
}
