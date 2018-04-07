import java.util.HashMap;
import java.util.Map;

enum Coordinate {
  NORTH,
  NORTHEAST,
  EAST,
  SOUTHEAST,
  SOUTH,
  SOUTHWEST,
  WEST,
  NORTHWEST,
  CENTER
}
class Soil {
  private int x;
  private int y;
  private int value;
  private boolean visited;

  Soil(int value, int x, int y) {
    this.value = value;
    this.x = x;
    this.y = y;
    visited = false;
  }

  public int getValue() {
    return value;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean isVisited() {
    return visited;
  }

  public void visit() {
    visited = true;
  }
}

class Conqueror {
  private Soil[][] terrain;
  private int maxX;
  private int maxY;
  private int biggestExtension;

  public Conqueror(int[][] map, int maxX, int maxY) {
    biggestExtension = -1;
    this.maxX = maxX;
    this.maxY = maxY;
    terrain = prepareTerrain(map, maxX, maxY);
  }

  public int getBiggestExtension() {
    return biggestExtension;
  }

  public void conquer() {
    for (int xIndex = 0; xIndex < maxX; xIndex++) {
      for (int yIndex = 0; yIndex < maxY; yIndex++) {
        Soil currentSoil = terrain[xIndex][yIndex];
        if (currentSoil.getValue() == 1 && !currentSoil.isVisited()) {
          int currentSoilExtension = conquerRegion(xIndex, yIndex);
          System.out.println(String.format(
            "Current region starting at [%d, %d] has an extension of %d", xIndex, yIndex, currentSoilExtension));

          biggestExtension = currentSoilExtension > biggestExtension ? currentSoilExtension : biggestExtension;
        }
      }
    }
  }

  private int conquerRegion(int x, int y) {
    int acres = 0;
    if (terrain[x][y].getValue() == 0) {
      return acres;
    }

    Map<Coordinate, Soil> region = getAdjacent(x, y);
    for (Coordinate coordinate : region.keySet()) {
      Soil currentSoil = region.get(coordinate);
      if (currentSoil == null) {
        continue;
      }

      if (currentSoil.getValue() == 0) {
        currentSoil.visit();
        continue;
      } else if (!currentSoil.isVisited()) {
          acres++;
          currentSoil.visit();
          acres += conquerRegion(currentSoil.getX(), currentSoil.getY());
      }
    }

    return acres;
  }

  private Map<Coordinate, Soil> getAdjacent(int x, int y) {
    Map<Coordinate, Soil> adjacents = new HashMap<>();
    adjacents.put(Coordinate.CENTER, terrain[x][y]);
    adjacents.put(Coordinate.NORTH, x - 1 >= 0 ? terrain[x - 1][y] : null);
    adjacents.put(Coordinate.NORTHEAST, x - 1 >= 0 && y + 1 < maxY ? terrain[x -1][y + 1] : null);
    adjacents.put(Coordinate.EAST, y + 1 < maxY  ? terrain[x][y + 1] : null);
    adjacents.put(Coordinate.SOUTHEAST, x + 1 < maxX && y + 1 < maxY ? terrain[x + 1][y + 1] : null);
    adjacents.put(Coordinate.SOUTH, x + 1 < maxX ? terrain[x + 1][y] : null);
    adjacents.put(Coordinate.SOUTHWEST, x + 1 < maxX && y - 1 >= 0 ? terrain[x + 1][y - 1] : null);
    adjacents.put(Coordinate.WEST, y - 1 >= 0 ? terrain[x][y - 1] : null);
    adjacents.put(Coordinate.NORTHWEST, x - 1 >= 0 && y - 1 >= 0 ? terrain[x -1][y - 1] : null);

    return adjacents;
  }

  private Soil[][] prepareTerrain(int[][] map, int width, int height) {
    Soil[][] terrain = new Soil[width][height];
    for (int index1 = 0; index1 < width; index1++) {
      for (int index2 = 0; index2 < height; index2++) {
        terrain[index1][index2] = new Soil(map[index1][index2], index1, index2);
      }
    }

    return terrain;
  }
}

public class ConnectedCell {
  public static void main(String args[]) {
    int width = 5;
    int height = 4;
    int[][] terrain = {
        { 0, 0, 1, 1 },
        { 0, 0, 1, 0 },
        { 0, 1, 1, 0 },
        { 0, 1, 0, 0 },
        { 1, 1, 0, 0 }
    };

    Conqueror conqueror = new Conqueror(terrain, width, height);
    conqueror.conquer();
    System.out.println(conqueror.getBiggestExtension());
  }
}
