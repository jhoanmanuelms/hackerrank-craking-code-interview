import utils.AlgorithmsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class IceCreamParlor {
  private static List<String> results = new ArrayList<>();
  private static Map<Integer, int[]> costsMap = new HashMap<>();

  private static void populateCostsMap(int[] costs) {
    costsMap.clear();
    for (int index = 0; index < costs.length; index++) {
      int cost = costs[index];
      if (costsMap.containsKey(cost)) {
        costsMap.get(cost)[1] = index + 1;
      } else {
        int[] idsForCost = { index + 1, -1 };
        costsMap.put(cost, idsForCost);
      }
    }
  }

  private static int[] filterPool(int pool, int[] costs) {
    return Arrays.stream(costs).filter(cost -> cost < pool).sorted().toArray();
  }

  private static void displayFlavors(int cost1, int cost2) {
    int flavor1 = costsMap.get(cost1)[0];
    int flavor2 = cost1 == cost2 ? costsMap.get(cost2)[1] : costsMap.get(cost2)[0];
    StringBuilder response = new StringBuilder();

    if (flavor1 < flavor2) {
      response.append(flavor1).append(" ").append(flavor2);
    } else {
      response.append(flavor2).append(" ").append(flavor1);
    }

    results.add(response.toString());
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
    List<String> data = new ArrayList<>();
    List<String> testData = AlgorithmsUtils.readLines("AlgorithmsTestData4/input00.txt");
    List<String> expectedResults = AlgorithmsUtils.readLines("AlgorithmsTestData4/output00.txt");
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
          data.add(line);
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

    AlgorithmsUtils.assertResults(data, expectedResults, results);
  }
}
