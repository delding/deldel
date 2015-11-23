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
    List<String> ret = new ArrayList();
    int m = board.length;
    if (m == 0) return ret;
    int n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    TrieNode root = new TrieNode();
    for (String word : words) {
      addWord(root, word);
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dfs(board, visited, i, j, ret, root);
      }
    }
    return ret;
  }

  private void dfs(char[][] board, boolean[][] visited, int i, int j, List<String> ret, TrieNode root) {
    int idx = board[i][j] - 'a';
    if (root.edges[idx] == null) return;
    if (root.edges[idx].isWord) {
      if (!root.edges[idx].searched) { // ERROR: board may have multiple path containing same word, so word could be added multiple time
        ret.add(root.edges[idx].word); // ERROR: must check if the child is a word right now, because dfs can stop if this search after this char in the board has no where to go
        root.edges[idx].searched = true;
      }
    }
    visited[i][j] = true;
    for (int r = -1; r <= 1; r++) {
      for (int c = -1; c <= 1; c++) {
        if ((r == 0 && c != 0) || (r != 0 && c == 0)) {
          int x = i + r;
          int y = j + c;
          if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]) {
            dfs(board, visited, x, y, ret, root.edges[idx]);
          }
        }
      }
    }
    visited[i][j] = false;
  }

  private class TrieNode {
    String word;
    boolean isWord;
    TrieNode[] edges;
    boolean searched;

    TrieNode() {
      word = "";
      isWord = false;
      edges = new TrieNode[26];
      searched = false;
    }
  }

  private void addWord(TrieNode root, String word) {
    for (char c : word.toCharArray()) {
      int i = c - 'a';
      if (root.edges[i] == null) root.edges[i] = new TrieNode();
      root = root.edges[i];
    }
    root.isWord = true;
    root.word = word;
  }

}
