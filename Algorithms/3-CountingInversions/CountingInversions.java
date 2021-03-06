import java.util.Arrays;

public class CountingInversions {
  public static long countInversions(int[] array){
    int size = array.length;

    // Base Case
    if(size <= 1) {
      return 0;
    }

    // Recursive Case
    int middle = size / 2;
    int[] left = Arrays.copyOfRange(array, 0, middle);
    int[] right = Arrays.copyOfRange(array, middle, array.length);
    long inversions = countInversions(left) + countInversions(right);

    int range = size - middle;
    int leftIndex = 0;
    int rightIndex = 0;
    for(int index = 0; index < size; index++) {
      if(
          leftIndex < middle
              && (
              rightIndex >= range || left[leftIndex] <= right[rightIndex]
          )
          ) {
        array[index] = left[leftIndex++];
        inversions += rightIndex;
      }
      else if(rightIndex < range) {
        array[index] = right[rightIndex++];
      }
    }

    return inversions;
  }

  private static void printArray(int[] array) {
    final StringBuilder printer = new StringBuilder("Sorted Array -> [ ");
    Arrays.stream(array).forEach(num -> printer.append(num).append(" "));
    printer.append("]");
    System.out.println(printer.toString());
  }

  public static void main(String args[]) {
    int[] testArray = { 2, 1, 3, 1, 2 };
    System.out.println("Inversions -> " + countInversions(testArray));
    printArray(testArray);

    int[] testArray2 = { 1, 1, 1, 2, 2 };
    System.out.println("Inversions -> " + countInversions(testArray2));
    printArray(testArray2);

    int[] testArray3 = { 14, 7, 23, 72, 1, 11, 9, 2 };
    System.out.println("Inversions -> " + countInversions(testArray3));
    printArray(testArray3);
  }
}
