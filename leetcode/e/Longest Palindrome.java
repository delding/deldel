/**
 Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

 This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.
 Hide Company Tags
 **/

public class Solution {
	public int longestPalindrome(String s) {
		int[] counts = new int[52];
		for (char c : s.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				counts[c - 'a']++;
			} else {
				counts[26 + c - 'A']++;
			}
		}
		int len = 0;
		boolean hasOdd = false;
		for (int c : counts) {
			if (c % 2 == 1) hasOdd = true;
			len += c / 2 * 2;
		}
		return hasOdd ? len + 1 : len;
	}
}