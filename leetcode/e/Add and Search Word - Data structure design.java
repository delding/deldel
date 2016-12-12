/**
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * <p>
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */

public class WordDictionary {
  TrieNode root = new TrieNode();
  // Adds a word into the data structure.
  public void addWord(String word) {
    TrieNode cur = root;
    for (char c : word.toCharArray()) {
      int i = c - 'a';
      if (cur.children[i] == null) cur.children[i] = new TrieNode();
      cur = cur.children[i];
    }
    cur.isWord = true;
  }

  // Returns if the word is in the data structure. A word could
  // contain the dot character '.' to represent any one letter.
  public boolean search(String word) {
    return search(word, root);
  }

  boolean search(String word, TrieNode cur) {
    if (word.isEmpty()) return cur.isWord;
    char c = word.charAt(0);
    if (c == '.') {
      for (int i = 0; i < 26; i++) {
        if (cur.children[i] != null && search(word.substring(1), cur.children[i])) return true;
      }
    } else if (cur.children[c - 'a'] != null) {
      return search(word.substring(1), cur.children[c - 'a']);
    }
    return false;
  }

  class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false;
  }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
