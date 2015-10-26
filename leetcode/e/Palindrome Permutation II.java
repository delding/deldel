/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.
 * <p>
 * For example:
 * <p>
 * Given s = "aabb", return ["abba", "baab"].
 * <p>
 * Given s = "abc", return [].
 */

public class Solution {
  public List<String> generatePalindromes(String s) {
    List<String> rst = new LinkedList();
    int[] count = new int[256];
    for (int i = 0; i < s.length(); i++) {
      count[s.charAt(i)]++;
    }
    int oddIdx = -1;
    for (int i = 0; i < 256; i++) {
      if (count[i] % 2 == 1) {
        if (oddIdx == -1) oddIdx = i;
        else return rst;
      }
    }
    String palin = "";
    if (oddIdx != -1) {
      char c = (char) oddIdx;
      palin = "" + c;
      count[oddIdx]--;
    }
    dfs(rst, palin, count, s.length());
    return rst;
  }

  private void dfs(List<String> rst, String palin, int[] count, int len) {
    if (palin.length() == len) {
      rst.add(palin);
      return;
    }
    for (int i = 0; i < count.length; i++) {
      if (count[i] != 0) {
        count[i] = count[i] - 2;
        char c = (char) i;
        dfs(rst, c + palin + c, count, len);
        count[i] = count[i] + 2;
      }
    }
  }
}
