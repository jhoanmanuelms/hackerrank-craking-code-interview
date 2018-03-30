import utils.AlgorithmsUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Player {
  String name;
  int score;

  Player(String name, int score){
    this.name = name;
    this.score = score;
  }
}

class Checker implements Comparator<Player> {
  @Override
  public int compare(Player player1, Player player2) {
    if (player1.score > player2.score) {
      return -1;
    } else if (player1.score == player2.score) {
      return player1.name.compareToIgnoreCase(player2.name);
    }

    return 1;
  }
}

public class SortComparator {
  public static void main(String args[]) {
    Checker checker = new Checker();
    List<Player> playersList = new ArrayList<>();
    List<String> testData = AlgorithmsUtils.readLines("TestData2/input02.txt");
    Player[] players = new Player[testData.size()];

    testData.stream().forEach(playerData -> {
      String[] data = playerData.split(" ");
      playersList.add(new Player(data[0], Integer.valueOf(data[1])));
    });

    players = playersList.toArray(players);
    Arrays.sort(players, checker);
    for(int index = 0; index < players.length; index++){
      System.out.printf("%s %s\n", players[index].name, players[index].score);
    }
  }
}
