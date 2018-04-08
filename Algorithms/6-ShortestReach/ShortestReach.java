import utils.AlgorithmsUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

class Node {
  private int value;
  private List<Node> children;

  public Node(int value) {
    this.value = value;
    children = new ArrayList<>();
  }

  public void addChild(Node child) {
    children.add(child);
  }

  public List<Node> getChildren() {
    return children;
  }

  public boolean hasChildren() {
    return !children.isEmpty();
  }

  public int getValue() {
    return value;
  }
}

class Graph {
  private Map<Integer, Node> nodes;

  public Graph(int length) {
    nodes = new HashMap<>();
    for (int i = 1; i <= length; i++) {
      nodes.put(i, new Node(i));
    }
  }

  public void addEdge(int from, int to) {
    nodes.get(from).addChild(new Node(to));
    nodes.get(to).addChild(new Node(from));
  }

  public int pathToValue(int from, int to) {
    int path = 0;
    Node start = nodes.get(from);
    for (Node child : start.getChildren()) {
      if (child.getValue() == to) {
        path += 6;
        break;
      } else if(child.hasChildren()) {
        path += pathToValue(child.getValue(), to);
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
