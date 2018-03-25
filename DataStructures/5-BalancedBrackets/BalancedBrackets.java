import java.util.HashMap;
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
        if (!validator.validateBracket(bracketsStack.pop(), character)) {
          return false;
        } else {
          continue;
        }
      }

      bracketsStack.push(character);
    }

    return true;
  }

  public static void main(String args[]) {
    System.out.println(isBalanced("[]{}()")); // true
    System.out.println(isBalanced("[({})]{}()")); // true
    System.out.println(isBalanced("({(){}[]})[]")); // true
    System.out.println(isBalanced("{[()]}")); // true
    System.out.println(isBalanced("{[(])}")); // false
    System.out.println(isBalanced("{{[[(())]]}}")); // true
    System.out.println(isBalanced("{()[][{}]}")); // true
    System.out.println(isBalanced("({}{[]})({)}")); // false
    System.out.println(isBalanced("()[]")); // true
    System.out.println(isBalanced("[()][{}]{[({})[]]}")); // true
    System.out.println(isBalanced("((){)}")); // false
  }
}
