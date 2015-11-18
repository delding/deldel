/**
 Additive number is a positive integer whose digits can form additive sequence.
 A valid additive sequence should contain at least three numbers. Except for the first two numbers,
 each subsequent number in the sequence must be the sum of the preceding two.

 For example:
 "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.

 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199
 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Given a string represents an integer, write a function to determine if it's an additive number.

 Follow up:
 How would you handle overflow for very large input integers?
 **/

public class Solution {
  public boolean isAdditiveNumber(String num) {
    return isAdditive(num, "", "");
  }

  private boolean isAdditive(String num, String num1, String num2) {
    if (num.isEmpty()) return false; // num1 and num2 consume all of the num
    if (!num1.isEmpty() && !num2.isEmpty() && num.equals(add(num1, num2))) return true;
    if (num1.isEmpty()) {
      if (num.charAt(0) == '0') {
        if (isAdditive(num.substring(1), "0", "")) return true;
      } else {
        for (int i = 1; i <= num.length(); i++) {
          if (isAdditive(num.substring(i), num.substring(0, i), "")) return true;
        }
      }
    } else if (num2.isEmpty()) {
      if (num.charAt(0) == '0') {
        if (isAdditive(num.substring(1), num1, "0")) return true;
      } else {
        for (int i = 1; i <= num.length(); i++) {
          if (isAdditive(num.substring(i), num1, num.substring(0, i))) return true;
        }
      }
    } else {
      String num3 = add(num1, num2);
      // must first check num.length > num3.length, otherwise index could be out of range when taking num.substring
      if (num.length() > num3.length() && num3.equals(num.substring(0, num3.length()))) {
        if (isAdditive(num.substring(num3.length()), num2, num3)) return true;
      }
    }
    return false;
  }

  private String add(String num1, String num2) {
    int idx1 = num1.length() - 1;
    int idx2 = num2.length() - 1;
    if (idx1 < idx2) return add(num2, num1);
    String sum = "";
    int carry = 0;
    while (idx1 >= 0) {
      if (idx2 < 0) {
        int cur = carry + (num1.charAt(idx1--) - '0');
        carry = cur / 10;
        cur %= 10;
        sum = cur + sum;
      } else {
        int cur = carry + (num1.charAt(idx1--) - '0') + (num2.charAt(idx2--) - '0');
        carry = cur / 10;
        cur %= 10;
        sum = cur + sum;
      }
    }
    if (carry > 0) sum = carry + sum;
    return sum;
  }
}