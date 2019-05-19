package algorithms.histogram;

public class WaterTrappedOptimal {
	public static int waterTrappedOptimal(int[] input) {
		int result = 0;
		
		if (input == null || input.length == 0) {
			return result;
		}
		
		int left = 0;
		int right = input.length - 1;
		int leftmax = input[left];
		int rightmax = input[right];
		
		while (left <= right) {
			leftmax = Math.max(leftmax, input[left]);
			rightmax = Math.max(rightmax, input[right]);
			if (leftmax <= rightmax) {
				result += leftmax - input[left];
				left++;
			} else {
				result += rightmax - input[right];
				right--;
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		System.out.println(waterTrapped.maxTrapped(new int[0]));
		System.out.println(waterTrapped.waterTrappedI(new int[0]));
		System.out.println(waterTrapped.waterTrappedDP(new int[0]));
		System.out.println(waterTrappedOptimal(new int[0]));
		System.out.println();

		System.out.println(waterTrapped.maxTrapped(new int[] {3}));
		System.out.println(waterTrapped.waterTrappedI(new int[] {3}));
		System.out.println(waterTrapped.waterTrappedDP(new int[] {3}));
		System.out.println(waterTrappedOptimal(new int[] {3}));
		System.out.println();

		System.out.println(waterTrapped.maxTrapped(new int[] {3, 2, 5}));
		System.out.println(waterTrapped.waterTrappedI(new int[] {3, 2, 5}));
		System.out.println(waterTrapped.waterTrappedDP(new int[] {3, 2, 5}));
		System.out.println(waterTrappedOptimal(new int[] {3, 2, 5}));
		System.out.println();

		System.out.println(waterTrapped.maxTrapped(new int[] {2,1,3,4,5,2,6}));
		System.out.println(waterTrapped.waterTrappedI(new int[] {2,1,3,4,5,2,6}));
		System.out.println(waterTrapped.waterTrappedDP(new int[] {2,1,3,4,5,2,6}));
		System.out.println(waterTrappedOptimal(new int[] {2,1,3,4,5,2,6}));
		System.out.println();

		System.out.println(waterTrapped.maxTrapped(new int[] {4,1,3,4,5,2,6}));
		System.out.println(waterTrapped.waterTrappedI(new int[] {4,1,3,4,5,2,6}));
		System.out.println(waterTrapped.waterTrappedDP(new int[] {4,1,3,4,5,2,6}));
		System.out.println(waterTrappedOptimal(new int[] {4,1,3,4,5,2,6}));
	}
}
