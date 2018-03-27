import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class MediumsHeap {
  private PriorityQueue<Integer> secondHalf = new PriorityQueue<>();
  private PriorityQueue<Integer> firstHalf = new PriorityQueue<>((value1, value2) -> {
    if (value1 == value2) return 0;

    return value1 < value2 ? 1 : -1;
  });

  public void add(Integer value) {
    if (firstHalf.isEmpty() || value < firstHalf.peek()) {
      firstHalf.add(value);
    } else {
      secondHalf.add(value);
    }

    balanceHeaps();
  }

  public double calculateMedian() {
    PriorityQueue<Integer> biggerHeap = getBiggerHeap();
    PriorityQueue<Integer> smallerHeap = getSmallerHeap();

    if (biggerHeap.size() == smallerHeap.size()) {
      return ((double)(biggerHeap.peek() + smallerHeap.peek())) / 2;
    } else {
      return (double)(biggerHeap.peek());
    }
  }

  private void balanceHeaps() {
    PriorityQueue<Integer> biggerHeap = getBiggerHeap();
    PriorityQueue<Integer> smallerHeap = getSmallerHeap();

    if (biggerHeap.size() - smallerHeap.size() > 1) {
      smallerHeap.add(biggerHeap.poll());
    }
  }

  private PriorityQueue<Integer> getBiggerHeap() {
    return firstHalf.size() > secondHalf.size() ? firstHalf : secondHalf;
  }

  private PriorityQueue<Integer> getSmallerHeap() {
    return firstHalf.size() > secondHalf.size() ? secondHalf : firstHalf;
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

    Utils.assertResults(results, expectedResults, results);
  }
}
