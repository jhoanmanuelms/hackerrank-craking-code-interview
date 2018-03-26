import utils.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RunningMedian {
  private static Set<Integer> heap = new TreeSet<>();

  private static Double addValue(Integer value) {
    heap.add(value);
    int index = 0;
    int size = heap.size();
    int middle = (int)Math.floor(size / 2);
    Iterator<Integer> iterator = heap.iterator();

    if (heap.size() == 1) {
      return Double.valueOf(value);
    }

    while(index < middle - 1) {
      iterator.next();
      index++;
    }

    if (size % 2 == 0) {
      int middleValue = iterator.next();
      int nextToMiddleValue = iterator.next();

      return Double.valueOf((double)(middleValue + nextToMiddleValue) / 2);
    }

    int skipped = iterator.next();
    int median = iterator.next();
    return Double.valueOf(median);
  }

  public static void main(String args[]) {
    List<String> testData = Utils.readLines("TestData8/input00.txt");
    List<String> expectedResults = Utils.readLines("TestData8/output00.txt");
    List<String> results = new ArrayList<>();

    testData.stream().forEach(value -> results.add(String.valueOf(addValue(Integer.valueOf(value)))));
    Utils.assertResults(testData, expectedResults, results);
  }
}
