package algorithms.random;

import java.util.List;

/**
 * Given a list of integers representing the lengths of urls, 
 * find the 95 percentile of all lengths (95% of the urls have lengths <= returned length).
 * Assumptions
 * The maximum length of valid url is 4096
 * The list is not null and is not empty and does not contain null
 * Examples
 * [1, 2, 3, ..., 95, 96, 97, 98, 99, 100], 95 percentile of all lengths is 95.
 * 
 * key insight: max length of URLs <= 4k/4096
 * data structure:
 * 	similar to bucket sort
 * 	bucket[i]: # urls for length i
 * 	bucket[0]...bucket[4096]
 *	when keys are in a limited range, can use an array rather than a hash table
 * 
 * algorithm
 * 	step 1: for each url, insert it into a bucket O(n)
 * 	step 2: find the min l that that bucket[1] + bucket[2] +... + bucket[l] >= 0.95 * n O(n)
 * 
 * space O(n + m)
 * n: size of the array
 * m: range
 * 
 * time O(n)
 * 
 * bucket sort
 * limitation: to be sorted elements in a small range
 * each bucket: stores a linked list of all URLs at this length
 * bucket[0]: url1 -> url3 -> url5 -> url8
 * bucket[1]: url9
 * ...
 * bucket[4096]: 
 * */
public class Percentile95 {
	public int percentile95(List<Integer> urlLengths) {
		// url length: 1-4096 (4k)
		// step 1: for each url, insert it into a bucket
		int[] counts = new int[4097];
		for (int urlLen : urlLengths) {
			counts[urlLen]++;
		}
		// step 2: find the min l
		/*
		int sum = 0;
		int result = 0;
		for (int i = 0; i < 4097; i++) {
			sum = sum + counts[i];
			if (sum >= 0.95 * urlLengths.size()) {
				result = i;
				break;
			}
		}
		return result;
		*/
		int sum = 0;
		int urlLen = 4097;
		while (sum <= 0.05 * urlLengths.size()) {
			sum = sum + counts[--urlLen]; // this is to return curr urlLen
			// if urlLen--; off by 1 error after while()
		}
		return urlLen;
	}
}
