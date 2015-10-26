/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 * <p>
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 * <p>
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */

public class Solution {
  public int strobogrammaticInRange(String low, String high) {
    int[] count = new int[1];
    int len1 = low.length();
    int len2 = high.length();
    for (int len = len1; len <= len2; len++) {
      if (len % 2 == 0) {
        dfs(low, high, count, "", len);
      } else {
        dfs(low, high, count, "1", len);
        dfs(low, high, count, "0", len);
        dfs(low, high, count, "8", len);
      }
    }
    return count[0];
  }

  private void dfs(String low, String high, int[] count, String num, int n) {
    if (num.length() == n) {
      if (num.length() > 1 && num.charAt(0) == '0')
        return; // ERROR: edge case  0 can't be the first digit, if number of digit bigger than one
      if (compare(num, low) >= 0 && compare(high, num) >= 0) count[0]++;
      return;
    }
    dfs(low, high, count, "1" + num + "1", n);
    dfs(low, high, count, "8" + num + "8", n);
    dfs(low, high, count, "0" + num + "0", n);
    dfs(low, high, count, "9" + num + "6", n);
    dfs(low, high, count, "6" + num + "9", n);
  }

  int compare(String num1, String num2) {
    if (num1.length() == num2.length()) {
      for (int i = 0; i < num1.length(); i++) {
        char c1 = num1.charAt(i);
        char c2 = num2.charAt(i);
        if (c1 - c2 > 0) return 1;
        else if (c1 - c2 < 0) return -1;
      }
      return 0;
    } else if (num1.length() > num2.length()) return 1;
    else return -1;

  }
}
