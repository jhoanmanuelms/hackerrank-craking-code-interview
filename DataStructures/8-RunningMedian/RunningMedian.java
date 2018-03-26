import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MinHeap {
  private int capacity = 10;
  private int size = 0;
  private int[] items = new int[capacity];

  public void add(int item) {
    ensureExtraCapacity();
    items[size] = item;
    size++;
    heapifyUp();
  }

  public double calculateMedian() {
    int middle = size / 2;
    if (size % 2 == 0) {
      return ((double)(items[middle] + items[middle - 1]) / 2);
    }

    return items[middle];
  }

  private void heapifyUp() {
    int index = size - 1;
    while (hasParent(index) && parent(index) > items[index]) {
      swap(getParentIndex(index), index);
      index = getParentIndex(index);
    }
  }

  private int getParentIndex(int childIndex) {
    return (childIndex - 1) / 2;
  }

  private boolean hasParent(int index) {
    return getParentIndex(index) >= 0;
  }

  private int parent(int index) {
    return items[getParentIndex(index)];
  }

  private void swap(int indexOne, int indexTwo) {
    int temp = items[indexOne];
    items[indexOne] = items[indexTwo];
    items[indexTwo] = temp;
  }

  private void ensureExtraCapacity() {
    if (size == capacity) {
      items = Arrays.copyOf(items, capacity * 2);
      capacity *= 2;
    }
  }
}

public class RunningMedian {
  public static void main(String args[]) {
    MinHeap heap = new MinHeap();
    List<String> testData = Utils.readLines("TestData8/input00.txt");
    List<String> expectedResults = Utils.readLines("TestData8/output00.txt");
    List<String> results = new ArrayList<>();

    testData.stream().forEach(value -> {
      heap.add(Integer.valueOf(value));
      results.add(String.valueOf(heap.calculateMedian()));
    });
    Utils.assertResults(testData, expectedResults, results);
  }
}
