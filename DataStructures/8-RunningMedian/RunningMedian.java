import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class RunningMedian {
  private static Comparator<Integer> comparator = new Comparator<Integer>() {
    @Override
    public int compare(Integer o1, Integer o2) {
      if (o1 < o2) return -1;
      if (o1 > o2) return 1;
      return 0;
    }
  };

  private static PriorityQueue<Integer> heap = new PriorityQueue<>(comparator);

  private static Double addValue(Integer value) {
    heap.add(value);
    Integer[] values = new Integer[heap.size()];
    heap.toArray(values);

    int size = values.length;
    int middle = size / 2;

    if (size % 2 == 0) {

      return Double.valueOf((values[middle] + values[middle - 1]) / 2);
    }

    return Double.valueOf(values[(int)Math.floor(middle)]);
  }

  public static void main(String args[]) {
    heap.add(12);
    heap.add(4);
    heap.add(5);
    heap.add(3);
    heap.add(8);
    heap.add(7);
//    System.out.println(addValue(12));
//    System.out.println(addValue(4));
//    System.out.println(addValue(5));
//    System.out.println(addValue(3));
//    System.out.println(addValue(8));
//    System.out.println(addValue(7));
//    heap.add(10);
//    heap.add(2);
//
//    Iterator<Integer> heapIterator = heap.iterator();
//    while (heapIterator.hasNext()) {
//      System.out.println(heapIterator.next());
//    }
//
//    heap.add(1);
//    heap.add(4);
//
//    heapIterator = heap.iterator();
//    while (heapIterator.hasNext()) {
//      System.out.println(heapIterator.next());
//    }
//
    Integer data;
    do {
      data = heap.poll();
      System.out.println(data);
    }while (data != null);
  }
}
