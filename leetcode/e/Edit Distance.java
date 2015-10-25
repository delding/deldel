/**
 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character
 **/

public class Solution {
  public int minDistance(String word1, String word2) {
    if (word1.isEmpty() || word2.isEmpty()) return Math.max(word1.length(), word2.length());
    int[][] dist = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 1; i <= word1.length(); i++) {
      dist[i][0] = i;
    }
    for (int j = 1; j <= word2.length(); j++) {
      dist[0][j] = j;
    }
    for (int i = 1; i <= word1.length(); i++) {
      for (int j = 1; j <= word2.length(); j++) {
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) dist[i][j] = dist[i - 1][j - 1];
        else {
          dist[i][j] = 1 + Math.min(Math.min(dist[i - 1][j], dist[i][j - 1]) , dist[i - 1][j - 1]);
        }
      }
    }
    return dist[word1.length()][word2.length()];
  }
}
