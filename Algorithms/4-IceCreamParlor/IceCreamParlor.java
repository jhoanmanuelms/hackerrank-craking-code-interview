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
    Arrays.sort(costs);
    int left = 0;
    int right = costs.length - 1;
    int middle = (left + ((left + right) / 2));
    int middleCost = costs[middle];
    do {
      if (pool > middleCost) {
        right = middle;
      } else {
        left = middle + 1;
      }

      middle = (left + ((left + right) / 2));
      middleCost = costs[middle];
    }while (middleCost > pool);

    return Arrays.copyOfRange(costs, left, right + 1);
  }

  private static void expendPool(int startFrom, int pool, int[] costs) {
    int currentCost;
    int initialCost = costs[startFrom];
    for (int index = startFrom; index < costs.length; index++) {
      currentCost = costs[index];
      if ((initialCost + currentCost) == pool) {
        int flavor1 = costsMap.get(initialCost).get(0);
        int flavor2 =
          initialCost == currentCost ?
          costsMap.get(currentCost).get(1) :
          costsMap.get(currentCost).get(0);

        System.out.println(flavor1 + " " + flavor2);
        return;
      }
    }

    expendPool(startFrom + 1, pool, costs);
  }

  public static void main(String args[]) {
    List<String> testData = AlgorithmsUtils.readLines("AlgorithmsTestData4/input-1.txt");
    int trips = -1;
    int pool = -1;
    int flavors = -1;
    int[] costs = { - 1 };
    for (int index = 0; index < testData.size(); index++) {
      String line = testData.get(index);
      if (index == 0) {
        trips = Integer.valueOf(line);
        continue;
      }

      if (index % 3 == 0) {
        costs = Stream.of(line.split(" ")).mapToInt(cost -> Integer.parseInt(cost)).toArray();
        break;
      } else if (index % 2 == 0) {
        flavors = Integer.valueOf(line);
      } else {
        pool = Integer.valueOf(line);
      }
    }

    populateCostsMap(costs);
    int [] filteredCosts = filterPool(pool, costs);
    expendPool(0, pool, filteredCosts);
  }
}
