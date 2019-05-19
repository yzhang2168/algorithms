package algorithms;

public class HammingDistance {

	/**
	 * hamming distance assumes the two strings are of the same length
	 * only substitutions are allowed
	 * */
	public static Integer getHammingDistance(String one, String two) {
		String s1 = one.trim();
		String s2 = two.trim();
		if (s1.length() != s2.length()) {
			return null;
		}
		
		int distance = 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				distance++;
			}
		}
		return distance;
	}
	
	public static void main(String[] args) {
		System.out.println(getHammingDistance("Shakespeare", "shake spear"));
	}
}
