import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

class TrieNode {
  private Map<Character, TrieNode> children;
  private String content;
  private boolean isWord;

  public TrieNode(String content) {
    this.content = content;
    children = new HashMap<>();
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
}

class Trie {
  private TrieNode root;

  public Trie() {
    root = new TrieNode("*");
  }

  public void insert(String word) {
    TrieNode current = root;
 
    for (int index = 0; index < word.length(); index++) {
      String content = word.substring(0, index + 1);
      current =
        current.getChildren().computeIfAbsent(word.charAt(index), c -> new TrieNode(content));
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

  public List<String> findPartial(String fragment) {
    TrieNode current = root;
    List<String> predictions = new ArrayList<>();
    for (char letter : fragment.toCharArray()) {
      TrieNode node = current.getChildren().get(letter);
      if (node == null) {
        return predictions;
      }

      current = node;
    }

    getWordsFromNode(current, predictions);
    return predictions;
  }

  private void getWordsFromNode(TrieNode node, final List<String> words) {
    if (node.getIsWord()) {
      words.add(node.getContent());
    }

    node.getChildren().values().stream().forEach(childNode -> getWordsFromNode(childNode, words));
  }
}

public class Solution {
  public static void main(String args[]){
    Trie trie = new Trie();
    try {
      Files.lines(Paths.get(ClassLoader.getSystemResource("./input02.txt").toURI())).forEach(line -> {
        String[] operation = line.split(" ");
        String op = operation[0];
        String contact = operation[1];

        switch(op) {
          case "add":
            trie.insert(contact);
            break;

          case "find":
            System.out.println(trie.findPartial(contact).size());
            break;
        }
      });
    } catch (Exception e ) {
      e.printStackTrace();
    }
  }
}
