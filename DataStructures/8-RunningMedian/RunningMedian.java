import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class RunningMedian {
  private static Set<Integer> heap = new TreeSet<>();

  private static Double addValue(Integer value) {
    heap.add(value);
    int index = 0;
    int size = heap.size();
    int middle = (int)Math.floor(size / 2);
    Iterator<Integer> iterator = heap.iterator();

    if (heap.size() == 1) {
      return Double.valueOf(value);
    }

    while(index < middle - 1) {
      iterator.next();
      index++;
    }

    if (size % 2 == 0) {
      int middleValue = iterator.next();
      int nextToMiddleValue = iterator.next();

      return Double.valueOf((double)(middleValue + nextToMiddleValue) / 2);
    }

    int skipped = iterator.next();
    int median = iterator.next();
    return Double.valueOf(median);
  }

  public static void main(String args[]) {
    System.out.println(addValue(12));
    System.out.println(addValue(4));
    System.out.println(addValue(5));
    System.out.println(addValue(3));
    System.out.println(addValue(8));
    System.out.println(addValue(7));
  }
}
