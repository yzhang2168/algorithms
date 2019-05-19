package algorithms.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Combination {
	/**
	 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), 
	 * get all the possible ways to pay a target number of cents.
	 * Arguments
	 * coins - an array of positive integers representing the different denominations of coins, 
	 * there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
	 * target - a non-negative integer representing the target number of cents, eg. 99
	 * Assumptions
	 * coins is not null and is not empty, all the numbers in coins are positive
	 * target >= 0
	 * You have infinite number of coins for each of the denominations, you can pick any number of the coins.
	 * Return
	 * a list of ways of combinations of coins to sum up to be target.
	 * each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.
	 * 
	 * 									 99
	 * 					/			|			|			\
	 * 25c		      25*0(r=99)							25*3(r=24)
	 * 			   /||||||\		  						/		|		\
	 * 10c		 10*0(r=99)  10*9(r=9)			10*0(r=24) 10*1(r=14) 10*2(r=4)
	 * 			/|||\		 /		\									   \	
	 * 5c	 5*0(r=99)	  5*0(r=9)	5*1(r=4)							5*0(r=4)
	 * 
	 * 1c - base case
	 * */
	public static void findCombinations(int[] coins, int moneyLeft) {
		int[] result = new int[coins.length];
		if (moneyLeft <= 0) {
			return;
		}
		
		findCombinations(coins, 99, 0, result);
	}
	
	private static void findCombinations(int[] coins, int moneyLeft, int level, int[] result) {
		// base case: last coin
		if (level == coins.length - 1) {
			if (moneyLeft % coins[coins.length - 1] == 0) {
				result[level] = moneyLeft / coins[coins.length - 1]; 
				util.Util.printArray(result);
			}
			return;
		}
		
		// # levels: # coins; each level corresponds to a coin type
		// at each level, enumerate all options of using that coin: 0...moneyLeft / coin
		// therefore, # branches at each level is dynamic 
		for (int i = 0; i <= moneyLeft / coins[level]; i++) {
			result[level] = i; // overwrite previous value
			findCombinations(coins, moneyLeft - coins[level] * i, level + 1, result);
			// no need to wipe out previous value, will be overwritten next time
		}
	}
	
	public static List<List<Integer>> findCombinations1(int[] coins, int money) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (money <= 0) {
			return result;
		}
		
		int[] resulttemp = new int[coins.length];
		findCombinations1(coins, 99, 0, result, resulttemp);
		return result;
	}
	
	private static void findCombinations1(int[] coins, int money, int level, List<List<Integer>> result, int[] resulttemp) {
		// base case
		if (level == coins.length - 1) {
			if (money % coins[coins.length - 1] == 0) {
				resulttemp[level] = money / coins[coins.length - 1]; 
				// Java 8: int[] to List<Integer>
				result.add(Arrays.stream(resulttemp).boxed().collect(Collectors.toList())); 
			}
			return;
		}
		
		// # branches: 0...coins
		for (int i = 0; i <= money / coins[level]; i++) {
			resulttemp[level] = i;
			findCombinations1(coins, money - i * coins[level], level + 1, result, resulttemp);
			// no need to reset resulttemp[level], will be overwritten in parent call
		}
	}
	
	public static List<List<Integer>> findCombinations2(int[] coins, int money) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (money <= 0 || coins == null || coins.length == 0) {
			return result;
		}
		
		List<Integer> currComb = new ArrayList<Integer>();
		findCombinations2(coins, 99, 0, result, currComb);
		return result;
	}
	
	private static void findCombinations2(int[] coins, int money, int level, List<List<Integer>> result, List<Integer> currComb) {
		/*
		if (level == coins.length - 1) {
			if (money % coins[coins.length - 1] == 0) {
				currComb.add(money / coins[coins.length - 1]);
				//result.add(currComb); // <-------incorrect: adding a ref, not creating a new object
				result.add(new ArrayList<Integer>(currComb)); // <-------- creates a new object identical to the curr state
				//deep copy constructor: new ArrayList<E>(collection) 
				currComb.remove(currComb.size() - 1);
			}
			return;
		}
		*/
		if (level == coins.length) {
			if (money == 0) {
				//result.add(currComb); // <-------incorrect: adding a ref, not creating a new object
				result.add(new ArrayList<Integer>(currComb));
			}
			return;
		}
		
		// # branches: 0...coins
		//int maxCoins = money / coins[level];
		for (int i = 0; i <= money / coins[level]; i++) {
			currComb.add(i);
			findCombinations2(coins, money - i * coins[level], level + 1, result, currComb);
			currComb.remove(currComb.size() - 1);
		}
	}
	
	public static void main(String[] args) {
		int[] coins = {25, 10, 5, 2};
		//findCombinations(coins, 99);
		System.out.println(findCombinations1(coins, 99));
		System.out.println(findCombinations2(coins, 99));
	}
}
