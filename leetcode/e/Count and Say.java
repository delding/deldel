/**
 The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence.

 Note: The sequence of integers will be represented as a string.
 **/

public class Solution {
  public String countAndSay(int n) {
    String pre = "1";
    StringBuilder cur = new StringBuilder(); // bug: don't concat strng use stringbuilder otherwise TLE
    while (n-- > 1) {
      char last = '0';
      int count = 0;
      for (int i = 0; i <= pre.length(); ) {
        if (i == pre.length()) {
          if (count > 0) {
            cur.append(count);
            cur.append(last);
          }
          break;
        }
        char c = pre.charAt(i);
        if (last == '0' || c == last) {
          last = c;
          count++;
          i++;
        } else {
          cur.append(count);
          cur.append(last);
          count = 0;
          last = '0';
        }
      }
      pre = cur.toString();
      cur = new StringBuilder();
    }
    return pre;
  }
}