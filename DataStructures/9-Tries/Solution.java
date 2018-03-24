import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class TrieNode {
  private Map<Character, TrieNode> children;
  private Set<String> derivatives;
  private String content;
  private boolean isWord;

  public TrieNode(String content) {
    this.content = content;
    children = new HashMap<>();
    derivatives = new HashSet<>();
  }

  public Map<Character, TrieNode> getChildren() {
    return children;
  }

  public String getContent() {
    return content;
  }

  public void setIsWord(boolean isWord) {
    this.isWord = isWord;
  }

  public boolean getIsWord() {
    return isWord;
  }

  public void addDerivative(String derivative) {
    derivatives.add(derivative);
  }

  public Set<String> getDerivatives() {
    return derivatives;
  }
}

class Trie {
  private TrieNode root;
  private Map<String, List<String>> partialFindings;

  public Trie() {
    root = new TrieNode("*");
    partialFindings = new HashMap<>();
  }

  public void insert(String word) {
    TrieNode current = root;
 
    for (int index = 0; index < word.length(); index++) {
      String content = word.substring(0, index + 1);
      current =
        current.getChildren().computeIfAbsent(word.charAt(index), c -> new TrieNode(content));
      current.addDerivative(word);
    }

    current.setIsWord(true);
  }

  public boolean find(String word) {
    TrieNode current = root;

    for (char letter : word.toCharArray()) {
      TrieNode node = current.getChildren().get(letter);
      if (node == null) {
        return false;
      }

      current = node;
    }

    return current.getIsWord();
  }

  public Set<String> findPartial(String fragment) {
    TrieNode current = root;
    for (char letter : fragment.toCharArray()) {
      TrieNode node = current.getChildren().get(letter);
      if (node == null) {
        return new HashSet<>();
      }

      current = node;
    }

    return current.getDerivatives();
  }
}

public class Solution {
  public static void main(String args[]){
    Trie trie = new Trie();
    try {
      List<String> results = new ArrayList<>();
      List<String> expected =
          Files.lines(Paths.get(ClassLoader.getSystemResource("./output02.txt").toURI()))
               .collect(Collectors.toList());

      Files.lines(Paths.get(ClassLoader.getSystemResource("./input02.txt").toURI())).forEach(line -> {
        String[] operation = line.split(" ");
        String op = operation[0];
        String contact = operation[1];

        switch(op) {
          case "add":
            trie.insert(contact);
            break;

          case "find":
            results.add(String.valueOf(trie.findPartial(contact).size()));
            break;
        }

      });

      if (results.size() != expected.size()) {
        System.out.println("FAILURE");
      } else {
        for (int index = 0; index < results.size(); index++) {
          String currentResult = results.get(index);
          StringBuilder output = new StringBuilder();
          output.append(currentResult);

          if (!currentResult.equals(expected.get(index))) {
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
