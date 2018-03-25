import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Utils {
  public static List<String> readLines(Path path) {
    List<String> lines = new ArrayList<>();
    try {
      Files.lines(path).forEach(line -> lines.add(line));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return lines;
  }
}
