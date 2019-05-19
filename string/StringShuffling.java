package algorithms.string;

/*
 * input:  A1B2C3D4
 * output: ABCD1234
 * mergesort custom rule:
 * 0-9 > char '9'
 * */
public class StringShuffling {

	public static String charMergeSort(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		
		char[] array = input.toCharArray();
		char[] temp = new char[array.length];
		sort(array, temp, 0, array.length - 1);
		return new String(array);
	}
	
	private static void sort(char[] input, char[] temp, int l, int r) {
		if (l >= r) {
			return;
		}
		int m = l + (r - l) / 2;
		sort(input, temp, l, m);
		sort(input, temp, m + 1, r);
		merge(input, temp, l, m, r);
	}
	
	private static void merge(char[] input, char[] temp, int l, int m, int r) {
		for (int i = l; i <= r; i++) {
			temp[i] = input[i];
		}
		
		int lLeft = l;
		int rLeft = m + 1;
		int curr = l;
		while (lLeft <= m && rLeft <= r) {
			if (isSmaller(temp[lLeft], temp[rLeft])) {
				input[curr++] = temp[lLeft++];
			} else {
				input[curr++] = temp[rLeft++];
			}
		}
		/*
		 * if (isLetter(temp[lLeft]) && !isLetter(temp[rLeft])) {
                input[curr++] = temp[lLeft++];
            } else if (!isLetter(temp[lLeft]) && isLetter(temp[rLeft])) {
                input[curr++] = temp[rLeft++];
            } else if (temp[lLeft] <= temp[rLeft]) {
                input[curr++] = temp[lLeft++];
            } else {
                input[curr++] = temp[rLeft++];
            }
		 */
		
		while (lLeft <= m) {
			input[curr++] = temp[lLeft++];
		}
	}
	
	private static boolean isDigit(char ch) {
		return  ch >= '0' && ch <= '9';
	}
	
    private static boolean isLetter(char ch) {
        return  ch > '9';
    }

public static String charQuickSort(String input) {
		if (input == null || input.length() <= 1) {
			return input;
		}
		
		char[] array = input.toCharArray();
		quicksort(array, 0, array.length - 1);
		return new String(array);
	}
	
	private static void quicksort(char[] array, int l, int r) {
		if (l >= r) {
			return;
		}
		int index = partition(array, l, r);
		quicksort(array, l, index - 1);
		quicksort(array, index + 1, r);
	}
	
	private static int partition(char[] array, int l, int r) {
		int pivotIndex = l + (int) Math.random() * (r - l + 1);
		char pivot = array[pivotIndex];
		swap(array, pivotIndex, r);
		
		int lBound = l;
		int rBound = r - 1;
		while (lBound <= rBound) {
			if (isSmaller(array[lBound], pivot)) {
				lBound++;
			} else if (isSmaller(pivot, array[rBound])) {
				rBound--;
			} else {
				swap(array, lBound++, rBound--);
			}
		}
		
		swap(array, lBound, r);
		return lBound;
	}
	
	private static boolean isSmaller(char a, char b) {
		if (isDigit(a) && b > '9') {
			return false;
		} else if (a > '9' && isDigit(b)) {
			return true;
		} else {
			return a < b;
		}
	}
	
	private static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void main(String[] args) {		
		System.out.println(charMergeSort("A1B2C3D4"));
		System.out.println(charMergeSort("4321"));
		System.out.println(charMergeSort("DBCA"));
		System.out.println(charMergeSort("12AaBbCcDd34"));
		System.out.println();

		System.out.println(isSmaller('1', 'A'));
		System.out.println(isSmaller('1', '2'));
		System.out.println(isSmaller('A', '1'));
		System.out.println(isSmaller('a', 'b'));
		System.out.println();

		System.out.println(charQuickSort("A1B2C3D4"));
		System.out.println(charQuickSort("4321"));
		System.out.println(charQuickSort("DBCA"));
		System.out.println(charQuickSort("12AaBbCcDd34"));

	}
}
