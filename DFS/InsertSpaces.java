package algorithms.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string, we can insert at most one empty space between each pair of adjacent letters. 
Print all permutations of strings after insertions of empty spaces. 

Input: str = "ABC" 

Output: 
ABC
AB_C
A_BC
A_B_C
*/
public class InsertSpaces {
   /**
	*
	*level 0					"A"
	*						/			\	
	*level 1			"A B" 			"AB"
	*				   /	 \			 /	\	
	*level n - 1	"A B C"	"A BC"	“AB C”	"ABC"
	*
	*level n: base case, return
	*
	*at each level, insert (1) space and letter, or (2) letter only 
	*start from level 1 rather than level 0
	*/

	public static List<String> insertSpacesII(String input) {
		List<String> result = new ArrayList<String>();
		
		if (input == null || input.length() == 0) {
			return result;
		}
		
		StringBuilder prefix = new StringBuilder();
		prefix.append(input.charAt(0));
		insertSpaceHelper(input, 1, prefix, result);
		return result;
	}
	
	private static void insertSpaceHelper(String input, int level, StringBuilder prefix, List<String> result) {
		if (level == input.length()) {
			result.add(prefix.toString());
			return;
		}
		
		prefix.append(' ');
		prefix.append(input.charAt(level));
		insertSpaceHelper(input, level + 1, prefix, result);
		prefix.deleteCharAt(prefix.length() - 1);
		prefix.deleteCharAt(prefix.length() - 1);
		
		prefix.append(input.charAt(level));
		insertSpaceHelper(input, level + 1, prefix, result);
		prefix.deleteCharAt(prefix.length() - 1);
	}


	/**
	 * 								""
	*						/				\
	*level 0			"A "				"A"
	*					/	\				/	\
	*level 1		"A B " "A B"  		"AB "  "AB"
	*				   |	 |			  |		|	
	*level n - 1	"A B C" "A BC"	  “AB C”   "ABC"
	*
	*level n: base case, return
	*
	*at each level, insert (1) letter and space, or (2) letter only 
	*n-1 level: add letter only
	*/

	public static List<String> insertSpacesI(String input) {
		List<String> result = new ArrayList<String>();
		
		if (input == null || input.length() == 0) {
			return result;
		}
		
		StringBuilder prefix = new StringBuilder();
		insertSpacesI(input, 0, prefix, result);
		return result;
	}
	
	private static void insertSpacesI(String input, int level, StringBuilder prefix, List<String> result) {
		if (level == input.length()) {
			result.add(new String(prefix));
			return;
		}
		
		// this avoids adding " " after the last letter - trimming the left branch
		if (level != input.length() - 1) {		
			prefix.append(input.charAt(level));
			prefix.append(" ");
			insertSpacesI(input, level + 1, prefix, result);
			prefix.deleteCharAt(prefix.length() - 1);
			prefix.deleteCharAt(prefix.length() - 1);
		}

		prefix.append(input.charAt(level));
		insertSpacesI(input, level + 1, prefix, result);
		prefix.deleteCharAt(prefix.length() - 1);
	}	
	
	public static void main(String[] args) {
		System.out.println(insertSpacesI("ABC"));
		System.out.println(insertSpacesII("ABC"));
	}
}
