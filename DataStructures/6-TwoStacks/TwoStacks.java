import java.util.Stack;

class MyQueue<T> {
  private Stack<T> in;
  private Stack<T> out;
  private T front;

  public <T> MyQueue() {
    in = new Stack<>();
    out = new Stack<>();
    front = null;
  }

  public void enqueue(T element) {
    in.push(element);

    if (front == null) {
      front = element;
    }
  }

  public T dequeue() {
    T dequeued;
    if (out.empty()) {
      while (!in.empty()) {
        out.push(in.pop());
      }
    }

    dequeued = out.pop();
    front = out.empty() && in.empty() ? null : out.peek();

    return dequeued;
  }

  public T peek() {
    return front;
  }
}

public class TwoStacks {
  public static void main(String args[]) {
    MyQueue<Integer> queue = new MyQueue<>();
    queue.enqueue(42);
    queue.dequeue();
    queue.enqueue(14);
    System.out.println(queue.peek());
    queue.enqueue(28);
    System.out.println(queue.peek());
    queue.enqueue(60);
    queue.enqueue(78);
    queue.dequeue();
    queue.dequeue();
  }
}
