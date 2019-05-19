package algorithms;

import util.Util;

public class Solution {
	public int minIndex(int[] array, int i) {
	    // corner case
		if (array == null || array.length == 0) {
			return - 1;
		}
		
		// input validation
	    if (i < 0 || i > array.length - 1) {
	        return -1;
	    }  
	    int minIndex = i;
	    for (int j = i + 1; j < array.length; j++) {
	        if (array[j] < array[minIndex]) {
	            minIndex = j;
	        }
	    }
	    return minIndex;
	  }
	  
	  
	  public void swap(int[] array, int i, int j) {
		    int temp = array[i];
		    array[i] = array[j];
		    array[j] = temp;
		  }

	  public void reverseInPlace(int[] array) {
		    int i = 0;
		    int j = array.length - 1;
		    for (i = 0; i <= j; i++) {
		      int temp = array[i];  
		      array[i] = array[j];
		      array[j] = temp;
		      j--;
		    }
		  }
	  
	  public int[] reverse(int[] array) {
		  int[] temp = new int[array.length];
		  for (int i = 0; i < array.length; i++) {
			  temp[i] = array[array.length - 1 - i];			  
		  }
		  //array = temp; // ---> array here is a local var copied from parameter
		  // this does not change the originial array
		  return temp;
	  }
	  
	  public static void main(String[] args) {
		  Solution s = new Solution();
		  int[] arr0 = null;
		  System.out.println(s.minIndex(arr0, 0));
		  
		  int[] arr = {123,1,3123,344,31};
		  System.out.println(s.minIndex(arr, 2));
		  //s.reverseInPlace(arr);
		  //Util.printArray(arr);
		  
		  int[] arr2 = {123,1,3123,1023,344,31};
		  Util.printArray(s.reverse(arr2));  
		  
	  }
	}
