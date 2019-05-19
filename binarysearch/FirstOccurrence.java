package algorithms.binarysearch;

public class FirstOccurrence {

    public static int firstOccurrence(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // left >= right - 1, 1-2 elements
        if (array[left] == target) {
            return left;
        } else if (array[right] == target) {
            return right;
        } else {
            return -1;
        }

    }

    public static int firstOccurrenceII(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                right = mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // left >= right, right could be left - 1
        if (array[left] == target) {
            return left;
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 2, 3, 4, 4 };
        System.out.println("out of while(): 1-2 elements");
        System.out.println(firstOccurrence(array, 0));
        System.out.println(firstOccurrence(array, 1));
        System.out.println(firstOccurrence(array, 2));
        System.out.println(firstOccurrence(array, 4));
        System.out.println(firstOccurrence(array, 5));

        System.out.println("\nout of while(): 1 element");
        System.out.println(firstOccurrenceII(array, 0));
        System.out.println(firstOccurrenceII(array, 1));
        System.out.println(firstOccurrenceII(array, 2));
        System.out.println(firstOccurrenceII(array, 4));
        System.out.println(firstOccurrenceII(array, 5));
    }
}
