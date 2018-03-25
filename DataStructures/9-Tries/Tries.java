import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

public class Tries {
  public static void main(String args[]){
    Trie trie = new Trie();
    List<String> testData = Utils.readLines("TestData9/input07.txt");
    List<String> expectedResults = Utils.readLines("TestData9/output07.txt");
    List<String> results = new ArrayList<>();
    List<String> findOperationData = new ArrayList<>();

    testData.stream().forEach(line -> {
      String[] operation = line.split(" ");
      String op = operation[0];
      String contact = operation[1];

      switch(op) {
        case "add":
          trie.insert(contact);
          break;

        case "find":
          findOperationData.add(contact);
          results.add(String.valueOf(trie.findPartialCount(contact)));
          break;
      }
    });

    Utils.assertResults(findOperationData, expectedResults, results);
  }
}
