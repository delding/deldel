/**
 Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

 Example 1:
 Given words = ["bat", "tab", "cat"]
 Return [[0, 1], [1, 0]]
 The palindromes are ["battab", "tabbat"]
 Example 2:
 Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 **/

public class Solution {
	/*
	 * If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut:] , then s2+s1 is palindrome.
	 * If s1[cut: ] is palindrome and there exists s2 is the reversing string of s1[0:cut] , then s1+s2 is palindrome.
	 * s1[0: cut] and s1[cut:] should include empty string case
	 * s1[0: cut] is empty means s1 is reverse of s2 which will be count again when consider s2[cut: ] being empty
	 * s1[0: cut] or s1[cut:] is entire word means s1 is itself a palindrome s2 will be an empty string
	*/
	public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> ret = new ArrayList<>();
		Map<String, Integer> wordToIdx = new HashMap<>();
		for (int i = 0; i < words.length; i++) wordToIdx.put(words[i], i);
		for (int i = 0; i < words.length; i++) {
			String w = words[i];
			for (int end = 0; end <= w.length(); end++) {
				String prefix = w.substring(0, end); // from empty string to entire word
				String suffix = w.substring(end);
				if (isPalin(prefix)) {
					String reverse = new StringBuilder(suffix).reverse().toString();
					Integer j = wordToIdx.get(reverse);
					if (j != null && j != i) { // j != i to prevent using same word twice
						ret.add(Arrays.asList(j, i));
					}
				}
				if (isPalin(suffix)) {
					String reverse = new StringBuilder(prefix).reverse().toString();
					Integer j = wordToIdx.get(reverse);
					if (j != null && j != i && end != w.length()) { // end != w.length() to prevent duplicate count when two words are reverse of each other
						ret.add(Arrays.asList(i, j));
					}
				}
			}
		}
		return ret;
	}

	boolean isPalin(String w) {
		int l = 0;
		int r = w.length() - 1;
		while (l < r) {
			if (w.charAt(l++) != w.charAt(r--)) return false;
		}
		return true;
	}
}