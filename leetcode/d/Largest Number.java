/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * <p>
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * <p>
 * Note: The result may be very large, so you need to return a string instead of an integer.
 **/

public class Solution {
  public String largestNumber(int[] nums) {
    Comparator<String> comp = new Comparator<String>() {
      public int compare(String s1, String s2) {
        String s12 = s1 + s2;
        String s21 = s2 + s1;
        for (int i = 0; i < s12.length(); i++) {
          if (s21.charAt(i) > s12.charAt(i)) return 1;// sort from large to small
          else if (s21.charAt(i) < s12.charAt(i)) return -1;
        }
        return 0;
      }
    };
    String[] strs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      strs[i] = nums[i] + "";
    }
    Arrays.sort(strs, comp);
    StringBuilder sb = new StringBuilder();
    for (String s : strs) sb.append(s);
    String ret = sb.toString();
    for (int i = 0; i < ret.length(); i++) { // Error: remove heading zeros for input like [0, 0]
      if (ret.charAt(i) != '0') return ret.substring(i);
    }
    return "0";
  }
}
