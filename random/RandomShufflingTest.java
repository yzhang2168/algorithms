package algorithms.random;

public class RandomShufflingTest {
		public static void main(String[] args) {
			RandomSampling r = new RandomSampling();
			int[] input = {1, 2, 3, 4, 5, 6, 7};
			for (int i : input) {
				r.read(i);
				System.out.println(r.sample());
			}
		}
	
}
