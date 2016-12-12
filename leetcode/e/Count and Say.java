/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * <p>
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * <p>
 * Note: The sequence of integers will be represented as a string.
 **/

// only 1, 2, 3 can appear
// proof: in order to have 4, must have 1111,
// if first 1 is at odd position, then it is interpreted from 11, which should be 21 instead
// if fisrt 1 is at even position, a1111b, then it is interpreted from a one, one one, one b, which should instead be (a+1) one, one b
// so 1111 is not possible
public class Solution {
  public String countAndSay(int n) {
    String num = "1";
    while (--n > 0) {
      String next = "";
      int count = 0;
      for (int i = 0; i <= num.length(); i++) {
        if (count == 0) count++;
        else {
          if (i == num.length() || num.charAt(i) != num.charAt(i - 1)) {
            next += count;
            next += num.charAt(i - 1);
            count = 1;
          } else count++;
        }
      }
      num = next;
    }
    return num;
  }
}