import utils.Utils;

import java.util.ArrayList;
import java.util.List;

class MinHeap {
  List<Integer> items = new ArrayList<>();

  public void add(Integer value) {
    for (int index = 0; index < items.size(); index++) {
      if (value < items.get(index)) {
        items.add(index, value);
        return;
      }
    }

    items.add(value);
  }

  public double calculateMedian() {
    int middle = items.size() / 2;
    if (items.size() % 2 == 0) {
      return ((double) (items.get(middle) + items.get(middle - 1)) / 2);
    }

    return items.get(middle);
  }
}

public class RunningMedian {
  public static void main(String args[]) {
    MinHeap heap = new MinHeap();
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
