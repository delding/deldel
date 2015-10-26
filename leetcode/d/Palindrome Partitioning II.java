/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */

public class Solution {
  // DP: mincut(0, j) = min{mincut(0, i) + 1} for all i that makes string(i, j) a palindrome, or 0 if string(0, j) is itself a palindrome
  // compute mincut of string(0, i) for i = 0 to n
  public int minCut(String s) {

    // ERROR: all palindrome checking for (0, j) depends on palindrome checking for (0, j - 1), must optimize memo computation otherwise TLE
    boolean[][] memo = new boolean[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++) memo[i][i] = true; // single char is palindrome
    for (int len = 2; len <= s.length(); len++) {
      for (int j = len - 1; j < s.length(); j++) { // ERROR: j starts from len - 1
        int i = j - len + 1;
        if (s.charAt(i) != s.charAt(j)) memo[i][j] = false;
        else if (i + 1 == j) memo[i][j] = true;
        else if (memo[i + 1][j - 1]) memo[i][j] = true;
        else memo[i][j] = false;
      }
    }

    int[] mincut = new int[s.length()];
    mincut[0] = 0; // single char needs zero cut
    int prev[] = new int[s.length()]; // not use prev to recover cut string for this problem
    prev[0] = -1;

    for (int j = 1; j < s.length(); j++) {
      int min = j; // every char as a single cut, this is the upbound for mincut
      int pre = -1;
      for (int i = 0; i <= j; i++) {
        if (memo[i][j]) {
          if (i == 0) {
            min = 0; // entire (0, j) is palindrome
            pre = -1;
          } else {
            if (1 + mincut[i - 1] < min) {
              min = 1 + mincut[i - 1];
              pre = i - 1;
            }
          }
        }
      }
      mincut[j] = min;
      prev[j] = pre;
    }

    return mincut[s.length() - 1];
  }
}
