import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
      List<String> testData = Utils.readLines("TestData5/input04.txt");
      List<String> expectedResults = Utils.readLines("TestData5/output04.txt");
      List<String> results = new ArrayList<>();

      testData.stream().forEach(data -> results.add(isBalanced(data) ? "YES" : "NO"));
      Utils.assertResults(testData, expectedResults, results);
  }
}
