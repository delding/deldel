/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells
 * are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

public class Solution {
  public List<String> findWords(char[][] board, String[] words) {
    List<String> found = new ArrayList<>();
    int m = board.length;
    if (m == 0 || board[0].length == 0) return found;
    int n = board[0].length;
    Trie trie = new Trie();
    for (String w : words) trie.add(w);
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        search(found, trie.root, board, i, j);
      }
    }
    return found;
  }

  static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

  void search(List<String> found, TrieNode tn, char[][] board, int i, int j) {
    char c = board[i][j];
    board[i][j] = '\0'; // mark visited
    if (tn.children[c - 'a'] != null) {
      if (!tn.children[c - 'a'].word.isEmpty()) {
        found.add(tn.children[c - 'a'].word); // add word here
        tn.children[c - 'a'].word = ""; // remove duplicate result
      }
      for (int[] dir : dirs) {
        int x = i + dir[0];
        int y = j + dir[1];
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != '\0') {
          search(found, tn.children[c - 'a'], board, x, y);
        }
      }
    }
    board[i][j] = c;
  }


  class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word = "";
  }

  class Trie {
    TrieNode root = new TrieNode();

    void add(String word) {
      TrieNode tn = root;
      for (char c : word.toCharArray()) {
        if (tn.children[c - 'a'] == null) {
          tn.children[c - 'a'] = new TrieNode();
        }
        tn = tn.children[c - 'a'];
      }
      tn.word = word;
    }
  }
}

public class Solution {
  public List<String> findWords(char[][] board, String[] words) {
    List<String> ret = new ArrayList<>();
    TrieNode root = new TrieNode();
    for (String w : words) addWord(root, w);
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        search(board, root, ret, i, j, "");
      }
    }
    return ret;
  }

  void search(char[][] b, TrieNode n, List<String> ret, int i, int j, String word) {
    if (n.isWord) {
      ret.add(word);
      n.isWord = false; // avoid duplication
    }
    if (i < 0 || j < 0 || i >= b.length || j >= b[0].length || b[i][j] == '.') return;
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    char c = b[i][j];
    int idx = c - 'a';
    b[i][j] = '.'; // set as visited
    if (n.ch[idx] != null) {
      for (int[] d : dir) {
        search(b, n.ch[idx], ret, i + d[0], j + d[1], word + c);
      }
    }
    b[i][j] = c; // reset
  }

  void addWord(TrieNode root, String word) {
    TrieNode cur = root;
    for (char c : word.toCharArray()) {
      int idx = c - 'a';
      if (cur.ch[idx] == null) cur.ch[idx] = new TrieNode();
      cur = cur.ch[idx];
    }
    cur.isWord = true;
  }

  static class TrieNode {
    TrieNode[] ch = new TrieNode[26];
    boolean isWord = false;
  }
}

