import java.util.HashSet;
import java.util.Set;

class Node {
  public int data;
  public Node next;
}

public class LinkedLists {
  static boolean hasCycle(Node head) {
    Set<Node> visited = new HashSet<>();

    if (head == null || head.next == null) {
      return false;
    }

    Node node = head;
    while (node.next != null) {
      if (visited.contains(node.next)) {
        return true;
      }

      visited.add(node.next);
      node = node.next;
    }

    return false;
  }

  public static void main(String args[]) {
    Node node0 = new Node();
    Node node1 = new Node();
    Node node2 = new Node();

    node0.data = 1;
    node1.data = 2;
    node2.data = 3;

    node0.next = node1;
    node1.next = node2;
    node2.next = node1;

    System.out.println("test -> " + hasCycle(node0));
  }
}