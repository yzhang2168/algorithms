package no_home_yet;


import java.util.ArrayList;
import java.util.List;
public class ChangeProblem {

	/**
	 * recursion: DFS
	 * # levels: # coin denominations
	 * # branches at level i: [0...money/coin[i]]
	 * */
	public static List<List<Integer>> findChangeOptions(int[] coins, int money) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> changeOption = new ArrayList<Integer>();
		if (money <= 0) {
			return result;
		}
		findChangeOptions(coins, money, 0, result, changeOption);
		return result;
	}

	private static void findChangeOptions(int[] coins, int money, int level, List<List<Integer>> result, List<Integer> changeOption) {
		// base case
		if (level == coins.length - 1) {
			if (money % coins[level] == 0) {
				changeOption.add(money / coins[level]);
				//result.add(changeOption);// x adds a ref
				result.add(new ArrayList<Integer>(changeOption));
				changeOption.remove(changeOption.size() - 1);
			}
			return;
		}

		for (int i = 0; i <= money / coins[level]; i++) {
			int moneyLeft = money - i * coins[level];
			changeOption.add(i);
			findChangeOptions(coins, moneyLeft, level + 1, result, changeOption);
			changeOption.remove(changeOption.size() - 1);
		}		
	}


	public static int minCountCoinsRecursion(int[] coins, int money) {
		if (money <= 0) {
			return 0;
		}

		int level = 0;
		int[] result = {0, Integer.MAX_VALUE}; // currNumCoins, globalMinNumCoins
		minCountCoins(coins, money, level, result);
		return result[1];
	}

	private static void minCountCoins(int[] coins, int money, int level, int[] result) {
		// base case: having gone through all coin types
		if (level == coins.length - 1) {
			if (money % coins[level] == 0) {
				result[0] = result[0] + money / coins[level];
				if (result[0] < result[1]) {
					result[1] = result[0];
				}
				result[0] = result[0] - money / coins[level];
			}
			return;
		} 
		
		// recursion: 
		// subproblem: money - current coin * num
		for (int i = 0; i <= money / coins[level]; i++) {
			int moneyLeft = money - i * coins[level];
			result[0] = result[0] + i;
			minCountCoins(coins, moneyLeft, level + 1, result);
			result[0] = result[0] - i;
		}
	}

	
	public static int minCountCoinsDP(int[] coins, int money) {
		if (money <= 0) {
			return 0;
		}
		
		// m[i]: minCountCoins for changing i
		int[] m = new int[money + 1];
		// base case
		m[0] = 0;
		// induction rule: m[i] = 1 + min(m[1]...m[i - 1]) if there's a coin for m[i] - m[j]
		for (int i = 1; i <= money; i++) {
			// init to invalid, no change
			m[i] = -1;
			// for money i, look back at all possible change options: all coins <= i && m[i - coins] is valid
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					int currOption = m[i - coins[j]];
					if (currOption != -1) {
						if (m[i] == -1 || 1 + currOption < m[i]) {
							m[i] = 1 + currOption;
						}
					}
				}	
			}
		}
		return m[money];
	}	

	
	public static void main(String[] args) {
		int[] coins1 = {34, 31,29,16, 2};
		int[] coins2 = {25, 10, 5, 2, 1};
		System.out.println(findChangeOptions(coins1, 10));
		System.out.println(findChangeOptions(coins2, 10));
		System.out.println();

		System.out.println(minCountCoinsRecursion(coins1, 10));
		System.out.println(minCountCoinsRecursion(coins2, 10));
		System.out.println(minCountCoinsDP(coins1, 10));
		System.out.println(minCountCoinsDP(coins2, 10));
		System.out.println();

		System.out.println(findChangeOptions(coins1, 30));
		System.out.println(findChangeOptions(coins2, 30));
		System.out.println();

		System.out.println(minCountCoinsRecursion(coins1, 30));
		System.out.println(minCountCoinsRecursion(coins2, 30));
		System.out.println(minCountCoinsDP(coins1, 30));
		System.out.println(minCountCoinsDP(coins2, 30));
	}

}
