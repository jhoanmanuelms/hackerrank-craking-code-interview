import utils.AlgorithmsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.stream.IntStream;

class Graph {
  List<List<Integer>> adjacentLst;
  int size;

  public Graph(int size) {
    adjacentLst = new ArrayList<>();
    this.size = size;
    IntStream.range(0, size).forEach(value -> adjacentLst.add(new ArrayList<>()));
  }

  public void addEdge(int first, int second) {
    adjacentLst.get(first).add(second);
    adjacentLst.get(second).add(first);
  }

  public int[] shortestReach(int startId) {
    int[] distances = new int[size];
    Arrays.fill(distances, -1);
    Queue<Integer> que = new LinkedList<>();

    que.add(startId);
    distances[startId] = 0;
    HashSet<Integer> seen = new HashSet<>();

    seen.add(startId);
    while(!que.isEmpty()) {
      Integer curr = que.poll();
      for(int node : adjacentLst.get(curr)) {
        if(!seen.contains(node)) {
          que.offer(node);
          seen.add(node);
          distances[node] = distances[curr] + 6;
        }
      }
    }

    StringBuilder distancesPrinter = new StringBuilder();
    for (int distance : distances) {
      distancesPrinter.append(distance).append(" ");
    }

    System.out.println(distancesPrinter.toString());
    return distances;
  }
}

public class ShortestReach {
  public static void main(String args[]) {
    List<String> testData = AlgorithmsUtils.readLines("AlgorithmsTestData6/input01.txt");
    ListIterator<String> testDataIterator = testData.listIterator();
    int q = Integer.valueOf(testDataIterator.next());

    for (int index = 0; index < q; index++) {
      String[] values = testDataIterator.next().split(" ");

      // Create graph
      Graph graph = new Graph(Integer.valueOf(values[0]));
      int m = Integer.valueOf(values[1]);

      // read and set edges
      for (int i = 0; i < m; i++) {
        values = testDataIterator.next().split(" ");
        graph.addEdge(Integer.valueOf(values[0]) - 1, Integer.valueOf(values[1]) - 1);
      }

      // Find shortest reach from node s
      int startId = Integer.valueOf(testDataIterator.next()) - 1;
      graph.shortestReach(startId);
    }
  }
}
