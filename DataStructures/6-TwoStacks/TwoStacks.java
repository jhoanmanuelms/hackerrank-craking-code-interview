import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class MyQueue<T> {
  private Stack<T> in;
  private Stack<T> out;

  public <T> MyQueue() {
    in = new Stack<>();
    out = new Stack<>();
  }

  public void enqueue(T element) {
    in.push(element);
  }

  public T dequeue() {
    pour();
    return out.pop();
  }

  public T peek() {
    pour();
    return out.peek();
  }

  private void pour() {
    if (out.empty() && !in.empty()) {
      while (!in.empty()) {
        out.push(in.pop());
      }
    }
  }
}

public class TwoStacks {
  public static void main(String args[]) {
    MyQueue<Integer> queue = new MyQueue<>();
    List<String> testData = Utils.readLines("TestData6/input02.txt");
    List<String> expectedResults = Utils.readLines("TestData6/output02.txt");
    List<String> results = new ArrayList<>();

    testData.stream().forEach(line -> {
      String[] operation = line.split(" ");
      String op = operation[0];
      String data = operation.length > 1 ? operation[1] : "";

      switch(op) {
        case "1":
          queue.enqueue(Integer.valueOf(data));
          break;

        case "2":
          queue.dequeue();
          break;

        case "3":
          results.add(String.valueOf(queue.peek()));
          break;
      }
    });

    // The test data is hard to determine for this case since it's the first executed insertion.
    // If needed it can be tracked but it was not necessary for this exercise.
    Utils.assertResults(results, expectedResults, results);
  }
}
