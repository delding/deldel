/**
 Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.

 All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

 Example 1:
 str = "aabbcc", k = 3

 Result: "abcabc"

 The same letters are at least distance 3 from each other.
 Example 2:
 str = "aaabc", k = 3

 Answer: ""

 It is not possible to rearrange the string.
 Example 3:
 str = "aaadbbcc", k = 2

 Answer: "abacabcd"

 Another possible answer is: "abcabcda"

 The same letters are at least distance 2 from each other.
 **/

public class Solution {
	// greedy, handle char with larger duplication number earlier
	public String rearrangeString(String str, int k) {
		if (k == 0) return str; // edge case
		Map<Character, Integer> counts = new HashMap<>();
		for (char c : str.toCharArray()) {
			if (!counts.containsKey(c)) counts.put(c, 1);
			else counts.put(c, 1 + counts.get(c));
		}
		Queue<int[]> pq = new PriorityQueue<>((c1, c2) -> { // c[0] is count, c[1] is char
			if (c1[0] == c2[0]) return c1[1] - c2[1];
			else return c2[0] - c1[0];
		});
		for (char c : counts.keySet()) {
			pq.add(new int[] { counts.get(c), (int)c });
		}
		StringBuilder ret = new StringBuilder();
		while (!pq.isEmpty()) {
			List<int[]> memo = new ArrayList<>();
			int runs = Math.min(k, str.length() - ret.length());
			for (int i = 0; i < runs; i++) {
				if (pq.isEmpty()) return ""; // impossible to rearrange
				int[] cc = pq.poll();
				if (--cc[0] > 0) memo.add(cc);
				ret.append((char)cc[1]);
			}
			for (int[] cc : memo) pq.add(cc);
		}
		return ret.toString();
	}
}