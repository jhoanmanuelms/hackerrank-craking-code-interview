package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
  public static List<String> readLines(String path) {
    List<String> lines = new ArrayList<>();
    try {
      Path filePath = Paths.get(ClassLoader.getSystemResource(path).toURI());
      Files.lines(filePath).forEach(line -> lines.add(line));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return lines;
  }

  public static void assertResults(List<String> testData, List<String> expectedResults, List<String> results) {
    for (int index = 0; index < testData.size(); index++) {
      Object currentData = testData.get(index);
      Object expectedResult = expectedResults.get(index);
      Object currentResult = results.get(index);

      StringBuilder consoleOutput = new StringBuilder();
      consoleOutput.append(currentResult).append(" ").append(expectedResult);

      if (!expectedResult.equals(currentResult)) {
        consoleOutput.append(" FAILURE ----> ").append(currentData);
      }

      System.out.println(consoleOutput.toString());
    }
  }
}
