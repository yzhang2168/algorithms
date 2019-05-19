package algorithms.histogram;

public class largestRectangleNaive {

	/**
	 * for each col, use the curr height and get the left O(n) and right border O(n)
	 * 中心开花 total O(n ^ 2)
	 * */
	public static int maxRectangleNaive(int[] input) {
		int max = 0;
		if (input == null || input.length == 0) {
			return max;
		}
		
		for (int i = 0; i < input.length; i++) {
			int left = i;
			int right = i;
			
			while (left >= 0 && input[left] >= input[i]) {
				left--;
			}
			
			if (left < i) {
				left++;
			}
			
			/*
			for (int j = left - 1; j >= 0; j--) {
				if (input[j] < input[i]) {
					break;
				} else {	
					left--;
				}
			}
			*/
			
			for (int k = right + 1; k < input.length; k++) {
				if (input[k] < input[i]) {
					break;
				} else {
					right++;
				}
			}

			//System.out.println(left + ":" + right);
			max = Math.max(max, input[i] * (right - left + 1));
		}		
		return max;
	}
	
	public static void main(String[] args) {
		int[] input = {2,1,3,4,5,2,6};
		System.out.println(maxRectangleNaive(new int[0]));
		System.out.println(maxRectangleNaive(new int[] {3}));
		System.out.println(maxRectangleNaive(input));
	}
}
