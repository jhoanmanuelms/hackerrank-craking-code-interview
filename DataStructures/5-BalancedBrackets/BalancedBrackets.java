import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class BracketsValidator {
  private Map<Character, Character> brackets;

  public BracketsValidator() {
    brackets = new HashMap<>();
    brackets.put('(', ')');
    brackets.put('[', ']');
    brackets.put('{', '}');
  }

  public boolean isBracketClosing(Character closing) {
    return brackets.values().contains(closing);
  }

  public boolean validateBracket(Character opening, Character closing) {
    if (!brackets.containsKey(opening)) {
      return false;
    }

    if (brackets.get(opening) != closing) {
      return false;
    }

    return true;
  }
}

public class BalancedBrackets {
  public static boolean isBalanced(String expression) {
    Stack<Character> bracketsStack = new Stack<>();
    BracketsValidator validator = new BracketsValidator();

    for (char character : expression.toCharArray()) {
      if (validator.isBracketClosing(character)) {
        if (bracketsStack.empty() || !validator.validateBracket(bracketsStack.pop(), character)) {
          return false;
        } else {
          continue;
        }
      }

      bracketsStack.push(character);
    }

    return bracketsStack.empty();
  }

  public static void main(String args[]) {
    try {
      Path testInput = Paths.get(ClassLoader.getSystemResource("TestData5/input04.txt").toURI());
      Path testOutput = Paths.get(ClassLoader.getSystemResource("TestData5/output04.txt").toURI());
      List<String> testData = Utils.readLines(testInput);
      List<String> expectedResults = Utils.readLines(testOutput);

      for (int index = 0; index < testData.size(); index++) {
        String expression = testData.get(index);
        String expectedResult = expectedResults.get(index);
        String currentResult = isBalanced(expression) ? "YES" : "NO";
        StringBuilder consoleOutput = new StringBuilder();
        consoleOutput.append(currentResult).append(" ").append(expectedResult);

        if (!expectedResult.equals(currentResult)) {
          consoleOutput.append(" FAILURE ----> ").append(expression);
        }

        System.out.println(consoleOutput.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
