package utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlgorithmsUtils {
  public static List<String> readLines(String path) {
    try {
      Path filePath = Paths.get(ClassLoader.getSystemResource(path).toURI());
      return Files.lines(filePath).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    return new ArrayList<>();
  }

  public static void assertResults(List<String> testData, List<String> expectedResults, List<String> results) {
    for (int index = 0; index < testData.size(); index++) {
      Object currentData = testData.get(index);
      Object expectedResult = expectedResults.get(index).trim();
      Object currentResult = results.get(index).trim();

      StringBuilder consoleOutput = new StringBuilder();
      consoleOutput.append(currentResult).append(" | ").append(expectedResult);

      if (!expectedResult.equals(currentResult)) {
        consoleOutput.append(" FAILURE ----> ").append(currentData);
      }

      System.out.println(consoleOutput.toString());
    }
  }
}
