/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return all possible palindrome partitioning of s.
 * <p>
 * For example, given s = "aab",
 * Return
 * <p>
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 **/

public class Solution {
  // DP: strng(0, n) = palindrome(0,i) + string(i, n) for all possible i
  // O(n*2^n), 2^n subproblems each subproblem has a O(n) palindrome checking
  public List<List<String>> partition(String s) {
    List<List<String>> ret = new ArrayList<List<String>>();
    dfs(ret, new ArrayList<String>(), s);
    return ret;
  }

  private void dfs(List<List<String>> ret, List<String> palin, String s) {
    if (s.isEmpty()) {
      ret.add(new ArrayList<String>(palin));
      return;
    }
    List<String> prefix = palindromePrefix(s);
    for (String p : prefix) {
      int len = p.length();
      palin.add(p);
      dfs(ret, palin, s.substring(len));
      palin.remove(palin.size() - 1);
    }
  }

  // can use a boolean matrix to reduce palindrome checking time from O(n) to O(1)
  // matrix[i][j] denote if s.substring(i, j) is a palindrome, the input paramenter would be the total string s and substring index i and j
  private List<String> palindromePrefix(String s) {
    List<String> prefix = new ArrayList<String>();
    for (int j = s.length() - 1; j >= 0; j--) {
      int lo = 0;
      int hi = j;
      while (lo < hi) {
        if (s.charAt(lo) == s.charAt(hi)) {
          lo++;
          hi--;
        } else break;
      }
      if (lo >= hi) prefix.add(s.substring(0, j + 1));
    }
    return prefix;
  }
}
