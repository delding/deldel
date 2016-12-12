/**
 Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be ≥ k.
 The given num does not contain any leading zero.
 Example 1:

 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 Example 2:

 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 Example 3:

 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 **/

public class Solution {
	// the smallest digit in first k + 1 digits must be kept to make the remaining number smallest
	// so all digits to the left of the smallest digit must be removed
	// recurse on the substring to the right of the smallest digit
	public String removeKdigits(String num, int k) {
		String res = solve(num, k, "");
		int i = 0;
		for (; i < res.length(); i++) {
			if (res.charAt(i) != '0') break;
		}
		return res.substring(i).isEmpty() ? "0" : res.substring(i);
	}

	String solve(String num, int k, String rem) {
		int len = num.length();
		if (k >= len) return rem;
		if (k == 0) return rem + num;
		int minPos = 0;
		for (int i = 1; i <= k; i++) {
			if (num.charAt(i) < num.charAt(minPos)) minPos = i;
		}
		// remove num[0:minPos - 1], keep num[minPos], recurse num[minPos + 1:]
		return solve(num.substring(minPos + 1), k - minPos, rem + num.charAt(minPos));
	}
}