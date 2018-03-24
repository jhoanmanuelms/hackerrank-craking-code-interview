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
    trie.insert("car");
    trie.insert("cat");
    trie.insert("dog");
    trie.insert("day");
    trie.insert("programming");
    trie.insert("programmer");
    trie.insert("program");
    trie.insert("progress");

    System.out.println("find chapter -> " + trie.find("chapter")); // false
    System.out.println("find cat -> " + trie.find("cat")); // true
    System.out.println("find dog -> " + trie.find("dog")); // true
    System.out.println("find days -> " + trie.find("days")); // false
    System.out.println("findPartial prog -> " + trie.findPartial("prog")); // false
    System.out.println("findPartial car -> " + trie.findPartial("car")); // false
    System.out.println("findPartial d -> " + trie.findPartial("d")); // false
    System.out.println("findPartial do -> " + trie.findPartial("do")); // false*/
  }
}
