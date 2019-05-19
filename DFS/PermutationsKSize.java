package algorithms.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsKSize {

	public static List<String> permutationsK(char[] input, int k) {
		List<String> result = new ArrayList<String>();
		
		if (k <= 0 || k > input.length) {
			return result;
		}
		
		permutationsK(input, k, 0, result);
		return result;
	}
	
	public static void permutationsK(char[] input, int k, int pos, List<String> result) {
		if (pos == k) {
			// public String(char[] value, int offset, int count)
			result.add(new String(input, 0, k));
			return;
		}
		
		for (int i = pos; i < input.length; i++) {
			swap(input, pos, i);
			permutationsK(input, k, pos + 1, result);
			swap(input, pos, i);
		}
	}
	
	private static void swap(char[] input, int a, int b) {
		char temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
	
	
	public static List<List<Character>> permutationsKII(Character[] input, int k) {
		List<List<Character>> result = new ArrayList<List<Character>>();
		
		if (k <= 0 || k > input.length) {
			return result;
		}
		
		permutationsKII(input, k, 0, result);
		return result;
	}
	
	public static void permutationsKII(Character[] input, int k, int pos, List<List<Character>> result) {
		if (pos == k) {
			// public String(char[] value, int offset, int count)
			// List<String> namesList = Arrays.asList( Arrays.copyOfRange(names, 0, 2) ) for Object arrays
			// subList(int fromIndex, int toIndex) 
			result.add(Arrays.asList( Arrays.copyOfRange(input, 0, k)));
			return;
		}
		
		for (int i = pos; i < input.length; i++) {
			swap(input, pos, i);
			permutationsKII(input, k, pos + 1, result);
			swap(input, pos, i);
		}
	}
	
	private static void swap(Character[] input, int a, int b) {
		Character temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}
	
	public static void main(String[] args) {
		char[] input1 = {'1', '2', '3'};
		Character[] input2 = {'1', '2', '3'};
		System.out.println(permutationsK(input1, 1));
		System.out.println(permutationsK(input1, 2));
		System.out.println(permutationsK(input1, 3));
		System.out.println();
		
		System.out.println(permutationsKII(input2, 1));
		System.out.println(permutationsKII(input2, 2));
		System.out.println(permutationsKII(input2, 3));
	}
}
