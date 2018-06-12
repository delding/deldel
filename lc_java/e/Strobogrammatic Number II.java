/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Find all strobogrammatic numbers that are of length = n.
 * <p>
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 */

public class Solution {

  public List<String> findStrobogrammatic(int n) {
    List<String> rst = new ArrayList();
    if (n % 2 == 0) dfs(rst, "", n);
    else {
      dfs(rst, "1", n);
      dfs(rst, "8", n);
      dfs(rst, "0", n);
    }
    return rst;
  }

  private void dfs(List<String> rst, String num, int n) {
    if (num.length() == n) {
      if (num.length() > 1 && num.charAt(0) == '0')
        return; // ERROR: edge case  0 can't be the first digit, if number of digit bigger than one
      rst.add(num);
      return;
    }
    dfs(rst, "1" + num + "1", n);
    dfs(rst, "8" + num + "8", n);
    dfs(rst, "0" + num + "0", n);
    dfs(rst, "9" + num + "6", n);
    dfs(rst, "6" + num + "9", n);
  }
}
