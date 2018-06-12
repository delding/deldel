/**
 Given a string, find the length of the longest substring T that contains at most k distinct characters.

 For example, Given s = “eceba” and k = 2,

 T is "ece" which its length is 3.
 **/

public class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k == 0) return 0; // edge case which is not handled in the following logic
		int len = 0;
		Map<Character, Integer> found = new HashMap<>();
		int l = 0;
		int r = 0;
		while (r < s.length()) {
			char c = s.charAt(r);
			int count = found.getOrDefault(c, 0);
			if (count > 0) {
				found.put(c, ++count);
			} else {
				found.put(c, 1);
				while (found.size() > k && l < r) {
					c = s.charAt(l);
					count = found.get(c);
					if (count > 1) {
						found.put(c, count - 1);
					} else {
						found.remove(c);
					}
					l++;
				}
			}
			len = Math.max(len, r - l + 1);
			r++;
		}
		return len;
	}
}