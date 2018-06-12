/**
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * <p>
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * <p>
 * word1 and word2 may be the same and they represent two individual words in the list.
 * <p>
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 * <p>
 * Note:
 * You may assume word1 and word2 are both in the list.
 */

public class Solution {
  public int shortestWordDistance(String[] words, String word1, String word2) {
    int p1 = -1, p2 = -1;
    int d = Integer.MAX_VALUE;
    for (int i = 0; i < words.length; i++) {
      if (words[i].equals(word1)) {
        if (p2 != -1) d = Math.min(d, i - p2);
        if (word1.equals(word2) && p1 != -1) d = Math.min(d, i - p1);
        p1 = i;
      }
      if (!word1.equals(word2) && words[i].equals(word2)) {
        if (p1 != -1) d = Math.min(d, i - p1);
        p2 = i;
      }
    }
    return d;
  }
}