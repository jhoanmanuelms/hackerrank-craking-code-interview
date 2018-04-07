import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    Graph tree = new Graph(4);
    tree.addEdge(1, 2);
    tree.addEdge(1, 3);

    tree.shortestReach(1);

    Graph tree2 = new Graph(3);
    tree2.addEdge(2, 3);

    tree2.shortestReach(2);
  }
}
