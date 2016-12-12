/**
 Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only
 once. You must make sure your result is the smallest in lexicographical order among all possible results.

 Example:
 Given "bcabc"
 Return "abc"

 Given "cbacdcbc"
 Return "acdb"
 **/

public class Solution {
	// greedy, scan from left to right, when meets the first letter that has its last count at position i, the smallest
	// letter in range [0, i] should be the most left smallest letter; remove any letters before smalllest and recurse until string is empty
	public String removeDuplicateLettersGreedy(String s) {
		if (s.isEmpty()) return s;
		int[] counts = new int[26];
		for (char c : s.toCharArray()) counts[c - 'a']++;
		char smallest = s.charAt(0);
		for (char c : s.toCharArray()) {
			if (c < smallest) smallest = c;
			if (--counts[c - 'a'] == 0) break;
		}
		StringBuilder sb = new StringBuilder();
		boolean leftToSmallest = true;
		for (char c : s.toCharArray()) {
			if (c == smallest) leftToSmallest = false; // if there are multiple smallest pick the most left one
			if (!leftToSmallest && c != smallest) {
				sb.append(c);
			}
		}
		return smallest + removeDuplicateLetters(sb.toString());
	}

	// stack
	public String removeDuplicateLetters(String s) {
		boolean[] onStack = new boolean[26];
		int[] counts = new int[26];
		Deque<Character> stack = new ArrayDeque<>();
		for (char c : s.toCharArray()) counts[c - 'a']++;
		for (char c : s.toCharArray()) {
			counts[c - 'a']--;
			if (onStack[c - 'a']) continue;
			while (!stack.isEmpty() && stack.peekLast() > c && counts[stack.peekLast() - 'a'] > 0) {
				onStack[stack.pollLast() - 'a'] = false;
			}
			stack.offerLast(c);
			onStack[c - 'a'] = true;
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) sb.append(stack.pollFirst());
		return sb.toString();
	}
}