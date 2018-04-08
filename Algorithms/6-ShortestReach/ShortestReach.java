import utils.AlgorithmsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

class Graph {
  private Map<Integer, List<Integer>> nodes;

  public Graph(int length) {
    nodes = new HashMap<>();
    for (int index = 1; index <= length; index++) {
      nodes.put(index, new ArrayList<>());
    }
  }

  public void addEdge(int from, int to) {
    nodes.get(from).add(to);
    nodes.get(to).add(from);
  }

  public int pathToValue(int from, int to) {
    int path = 0;
    List<Integer> children = nodes.get(from);
    if (children.contains(to)) {
      return 6;
    } else {
      for (Integer child : children) {
        List<Integer> grandChildren = nodes.get(child);
        if (grandChildren.isEmpty() || child == from) {
          return 0;
        }

        path += pathToValue(child, to);
      }
    }

    return path;
  }

  public void shortestReach(int from) {
    int size = nodes.size();
    StringBuilder reaches = new StringBuilder();

    for (int index = 1; index <= size; index++) {
      if (index != from) {
        int path = pathToValue(from, index);
        reaches.append(path == 0 ? -1 : path).append(" ");
      }
    }

    System.out.println(reaches.toString());
  }
}

public class ShortestReach {
  public static void main(String args[]) {
    List<String> testData = AlgorithmsUtils.readLines("AlgorithmsTestData6/input00.txt");
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
        graph.addEdge(Integer.valueOf(values[0]), Integer.valueOf(values[1]));
      }

      // Find shortest reach from node s
      int startId = Integer.valueOf(testDataIterator.next());
      graph.shortestReach(startId);
    }
  }
}
