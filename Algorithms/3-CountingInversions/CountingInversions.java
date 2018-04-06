import java.util.Arrays;

public class CountingInversions {
  private static long inversions = 0;

  public static long countInversions(int[] array) {
    inversions = 0;
    mergeSort(array, new int[array.length], 0, array.length - 1);

    return inversions;
  }

  public static void mergeSort(int[] array, int[] temp, int leftStart, int rightEnd) {
    if (leftStart >= rightEnd) {
      return;
    }

    int middle = (leftStart + rightEnd) / 2;
    mergeSort(array, temp, leftStart, middle);
    mergeSort(array, temp, middle + 1, rightEnd);
    mergeHalves(array, temp, leftStart, rightEnd);
  }

  public static void mergeHalves(int[] array, int[] temp, int leftStart, int rightEnd) {
    int leftEnd = (rightEnd + leftStart) / 2;
    int rightStart = leftEnd + 1;
    int size = rightEnd - leftStart + 1;

    int left = leftStart;
    int right = rightStart;
    int index = leftStart;

    while (left <= leftEnd && right <= rightEnd) {
      if (array[left] <= array[right]) {
        temp[index] = array[left];
        left++;
      } else {
        temp[index] = array[right];
        right++;
        inversions++;
      }
      index++;
    }

    System.arraycopy(array, left, temp, index, leftEnd - left + 1);
    System.arraycopy(array, right, temp, index, rightEnd - right + 1);
    System.arraycopy(temp, leftStart, array, leftStart, size);
  }

  public static void main(String args[]) {
    final StringBuilder printer = new StringBuilder();
    int[] testArray = { 2, 1, 3, 1, 2 };
    long inversionsResult = countInversions(testArray);

    Arrays.stream(testArray).forEach(num -> printer.append(num).append(" "));
    System.out.println(printer.toString());
    System.out.println("Inversions -> " + inversionsResult);

    int[] testArray2 = { 1, 1, 1, 2, 2 };
    inversionsResult = countInversions(testArray2);

    final StringBuilder printer2 = new StringBuilder();
    Arrays.stream(testArray2).forEach(num -> printer2.append(num).append(" "));
    System.out.println(printer2.toString());
    System.out.println("Inversions -> " + inversionsResult);

    final StringBuilder printer3 = new StringBuilder();
    int[] testArray3 = { 14, 7, 23, 72, 1, 11, 9, 2 };
    inversionsResult = countInversions(testArray3);

    Arrays.stream(testArray3).forEach(num -> printer3.append(num).append(" "));
    System.out.println(printer3.toString());
    System.out.println("Inversions -> " + inversionsResult);
  }
}
