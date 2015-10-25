/**
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 "((()))", "(()())", "(())()", "()(())", "()()()"
 **/

public class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> ret = new ArrayList<String>();
    dfs(0, 0, n, "", ret);
    return ret;
  }

  void dfs(int l, int r, int n, String cur, List<String> ret) {
    if (l == n && r == n) ret.add(cur);
    else {
      if (l < n) {
        dfs(l + 1, r, n, cur + "(", ret);
      }
      if (r < l) {
        dfs(l, r + 1, n, cur + ")", ret);
      }
    }
  }
}