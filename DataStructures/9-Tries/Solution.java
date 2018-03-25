import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class TrieNode {
  private Map<Character, TrieNode> children;
  private int derivativesCount;

  public TrieNode() {
    children = new HashMap<>();
  }

  public Map<Character, TrieNode> getChildren() {
    return children;
  }

  public void increaseDerivativesCount() {
    derivativesCount++;
  }

  public int getDerivativesCount() {
    return derivativesCount;
  }
}

class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode();
  }

  public void insert(String word) {
    TrieNode current = root;
 
    for (char letter : word.toCharArray()) {
      current =
        current.getChildren().computeIfAbsent(letter, c -> new TrieNode());
      current.increaseDerivativesCount();
    }
  }

  public int findPartialCount(String fragment) {
    TrieNode current = root;
    for (char letter : fragment.toCharArray()) {
      TrieNode node = current.getChildren().get(letter);
      if (node == null) {
        return 0;
      }

      current = node;
    }

    return current.getDerivativesCount();
  }
}

public class Solution {
  public static void main(String args[]){
    Trie trie = new Trie();
    try {
      List<String> results = new ArrayList<>();
      List<String> expected =
          Files.lines(Paths.get(ClassLoader.getSystemResource("TestData/output07.txt").toURI()))
               .collect(Collectors.toList());

      Files.lines(Paths.get(ClassLoader.getSystemResource("TestData/input07.txt").toURI())).forEach(line -> {
        String[] operation = line.split(" ");
        String op = operation[0];
        String contact = operation[1];

        switch(op) {
          case "add":
            trie.insert(contact);
            break;

          case "find":
            results.add(String.valueOf(trie.findPartialCount(contact)));
            break;
        }

      });

      if (results.size() != expected.size()) {
        System.out.println("FAILURE");
      } else {
        for (int index = 0; index < results.size(); index++) {
          String currentResult = results.get(index);
          String expectedResult = results.get(index);
          StringBuilder output = new StringBuilder();
          output.append(currentResult).append(" ").append(expectedResult);

          if (!currentResult.equals(expectedResult)) {
            output.append(" FAILURE");
          }

          System.out.println(output.toString());
        }
      }
    } catch (Exception e ) {
      e.printStackTrace();
    }
  }
}
