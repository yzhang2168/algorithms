package hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKWords {

	public String[] topKFrequent(String[] combo, int k) {
	    if (combo == null || k <= 0) {
	        return new String[0];
	    }

	    HashMap<String, Integer> wordCounts = createFreqMap(combo);
	    PriorityQueue<HashMap.Entry<String, Integer>> minheap = new PriorityQueue<HashMap.Entry<String, Integer>>(new Comparator<HashMap.Entry<String, Integer>>() {
	        @Override
	        public int compare(HashMap.Entry<String, Integer> o1, HashMap.Entry<String, Integer> o2) {
	            return o1.getValue().compareTo(o2.getValue());
	        }
	    });

	    for (HashMap.Entry<String, Integer> entry : wordCounts.entrySet()) {
	        if (minheap.size() < k) {
	            minheap.offer(entry);
	        } else if (minheap.peek().getValue() < entry.getValue()) {
	            minheap.poll();
	            minheap.offer(entry);
	        }
	    }
	    
	    // =======================================================
	    // k > minheap.size(): do NOT use k for final array length
	    // output by order
	    return heapToArray(minheap);
	  }

	  private HashMap<String, Integer> createFreqMap(String[] combo) {
	      HashMap<String, Integer> map = new HashMap<String, Integer>();
	        for (String word : combo) {
	            map.put(word, map.getOrDefault(word, 0) + 1);
	        }
	      return map;
	  }
	  
	  private String[] heapToArray(PriorityQueue<HashMap.Entry<String, Integer>> heap) {
		  String[] result = new String[heap.size()];
		  for (int i = heap.size() - 1; i >= 0; i--) {
			  result[i] = heap.poll().getKey();
		  }
		  return result;
	  }
	
	public static void main(String[] args) {
		String[] composition = {"d","a","c","b","d","a","b","b","a","d","d","a","d"};
		int k = 5;
		TopKWords test = new TopKWords();
		System.out.println(Arrays.deepToString(test.topKFrequent(new String[0], k)));
		System.out.println(Arrays.deepToString(test.topKFrequent(composition, k)));
	}
}
