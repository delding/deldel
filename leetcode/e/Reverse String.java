/**
 Write a function that takes a string as input and returns the string reversed.

 Example:
 Given s = "hello", return "olleh".
 **/

public class Solution {
	public String reverseString(String s) {
		char[] cs = s.toCharArray();
		int i = 0;
		int j = cs.length - 1;
		while (i < j) {
			char tmp = cs[i];
			cs[i++] = cs[j];
			cs[j--] = tmp;
		}
		return String.valueOf(cs);
	}
}