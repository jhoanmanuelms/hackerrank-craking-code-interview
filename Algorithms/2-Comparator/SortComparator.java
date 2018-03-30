import java.util.Arrays;
import java.util.Comparator;

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
    if (player1.name.equals(player2.name)) {
      return 0;
    }

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
    Player[] players = new Player[5];
    players[0] = new Player("amy", 100);
    players[1] = new Player("david", 100);
    players[2] = new Player("heraldo", 50);
    players[3] = new Player("aakansha", 75);
    players[4] = new Player("aleksa", 150);

    Arrays.sort(players, checker);
    for(int index = 0; index < players.length; index++){
      System.out.printf("%s %s\n", players[index].name, players[index].score);
    }
  }
}
