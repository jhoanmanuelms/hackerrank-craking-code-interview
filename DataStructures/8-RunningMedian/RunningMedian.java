import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MediumsHeap {
  private int size = 0;
  private int[] items;

  public void add(int item) {
    items[size] = item;
    size++;
    Arrays.parallelSort(items, 0, size);
  }


  public double calculateMedian() {
    int middle = size / 2;
    if (size % 2 == 0) {
      return ((double) (items[middle] + items[middle - 1]) / 2);
    }

    return items[middle];
  }
}

public class RunningMedian {
  public static void main(String args[]) {
    MediumsHeap heap = new MediumsHeap();
    List<String> testData = Utils.readLines("TestData8/input01.txt");
    List<String> expectedResults = Utils.readLines("TestData8/output01.txt");
    List<String> results = new ArrayList<>();

    testData.stream().forEach(value -> {
      heap.add(Integer.valueOf(value));
      results.add(String.valueOf(heap.calculateMedian()));
    });
    Utils.assertResults(testData, expectedResults, results);
  }
}
