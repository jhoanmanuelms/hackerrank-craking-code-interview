
public class BubbleSort {
  public static void sort(int[] array) {
    int size = array.length;
    int swaps = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size - 1; j++) {
        // Swap adjacent elements if they are in decreasing order
        if (array[j] > array[j + 1]) {
          swap(array, j, j + 1);
          swaps++;
        }
      }
    }

    System.out.println("Array is sorted in " + swaps + " swaps");
    System.out.println("First Element: " + array[0]);
    System.out.println("Last Element: " + array[size - 1]);
  }

  private static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  public static void main (String args[]) {
    int[] test = { 3, 2, 1 };
    sort(test);
  }
}
