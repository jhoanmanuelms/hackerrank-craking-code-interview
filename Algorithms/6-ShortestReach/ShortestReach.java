import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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

class Tree {
  private Map<Integer, Node> nodes;

  public Tree(int length) {
    nodes = new HashMap<>();
    IntStream.rangeClosed(1, length).forEach(value -> nodes.put(value, new Node(value)));
  }

  public void connect(int from, int to) {
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

  public void printPathToValue(int from, int to) {
    int path = pathToValue(from, to);
    path = path == 0 ? -1 : path;
    System.out.println(String.format("From %d to %d the path is %d", from, to, pathToValue(from, to)));
  }
}

public class ShortestReach {
  public static void main(String args[]) {
    Tree tree = new Tree(4);
    tree.connect(1, 2);
    tree.connect(1, 3);

    tree.printPathToValue(1, 2);
    tree.printPathToValue(1, 3);
    tree.printPathToValue(1, 4);
  }
}
