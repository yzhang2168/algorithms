package string;

import java.util.HashMap;
import java.util.HashSet;

public class Isomorphic {

	public boolean isomorphic(String source, String target) {
		if (source.length() != target.length()) {
			return false;
		}

		HashMap<Character, Character> map = new HashMap<>();
		HashSet<Character> set = new HashSet<>();
		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			Character value = map.get(c);          
			if (value == null) {
				if (set.contains(target.charAt(i))) {
					return false;
				}
				map.put(c, target.charAt(i));
				set.add(target.charAt(i));
			} else if (value != target.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Isomorphic test = new Isomorphic();
		System.out.println(test.isomorphic("rttl", "best"));
	}
}
