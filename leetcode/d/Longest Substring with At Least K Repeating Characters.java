/**
 Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

 Example 1:

 Input:
 s = "aaabb", k = 3

 Output:
 3

 The longest substring is "aaa", as 'a' is repeated 3 times.
 Example 2:

 Input:
 s = "ababbc", k = 2

 Output:
 5

 The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
**/

public class Solution {
	public int longestSubstring(String s, int k) {
		Map<Character, Integer> counts = new HashMap<>();
		for (char c : s.toCharArray()) {
			int count = counts.getOrDefault(c, 0) + 1;
			counts.put(c, count);
		}
		int longest = 0;
		for (char c : counts.keySet()) {
			if (counts.get(c) < k) {
				String[] substrings = s.split(String.valueOf(c));
				for (String sub : substrings) {
					longest = Math.max(longest, longestSubstring(sub, k));
				}
				return longest;
			}
		}
		return s.length();
	}
}