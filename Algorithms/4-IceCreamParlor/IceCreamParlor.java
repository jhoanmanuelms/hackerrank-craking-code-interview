import utils.AlgorithmsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class IceCreamParlor {
  private static Map<Integer, List<Integer>> costsMap = new HashMap<>();

  private static void populateCostsMap(int[] costs) {
    costsMap.clear();
    for (int index = 0; index < costs.length; index++) {
      int cost = costs[index];
      if (costsMap.containsKey(cost)) {
        costsMap.get(cost).add(index + 1);
      } else {
        List<Integer> idsForCost = new ArrayList<>();
        idsForCost.add(index + 1);
        costsMap.put(cost, idsForCost);
      }
    }
  }

  private static int[] filterPool(int pool, int[] costs) {
    return Arrays.stream(costs).filter(cost -> cost < pool).sorted().toArray();
  }

  private static void displayFlavors(int cost1, int cost2) {
    int flavor1 = costsMap.get(cost1).get(0);
    int flavor2 = cost1 == cost2 ? costsMap.get(cost2).get(1) : costsMap.get(cost2).get(0);

    System.out.println(flavor1 + " " + flavor2);
  }

  private static void expendPool(int pool, int[] costs) {
    int index1 = 0;
    int index2 = costs.length - 1;
    int guess = costs[index1] + costs[index2];

    while (guess != pool) {
      if (guess < pool) {
        index1++;
      } else {
        index2--;
      }

      guess = costs[index1] + costs[index2];
    }

    displayFlavors(costs[index1], costs[index2]);
  }

  public static void main(String args[]) {
    List<String> testData = AlgorithmsUtils.readLines("AlgorithmsTestData4/input00.txt");
    int pool = -1;
    int[] costs;
    int index = 0;
    for(String line : testData) {
      switch (index) {
        case 0:
          index++;
          continue;

        case 1:
          pool = Integer.valueOf(line);
          index++;
          break;

        case 2:
          index++;
          break;

        case 3:
          costs = Stream.of(line.split(" ")).mapToInt(cost -> Integer.parseInt(cost)).toArray();
          populateCostsMap(costs);
          int[] filteredCosts = filterPool(pool, costs);
          expendPool(pool, filteredCosts);
          index = 1;
          break;
      }
    }
  }
}
