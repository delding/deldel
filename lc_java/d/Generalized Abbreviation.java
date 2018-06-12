/**
 Write a function to generate the generalized abbreviations of a word.

 Example:
 Given word = "word", return the following list (order does not matter):
 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 **/

public class Solution {
	public List<String> generateAbbreviations(String word) {
		List<String> ret = new ArrayList<>();
		if (word.isEmpty()) {
			ret.add("");
			return ret;
		}
		dfs(ret, word.toCharArray(), 0);
		return ret;
	}

	void dfs(List<String> abbrs, char[] abbr, int idx) {
		if (idx == abbr.length) {
			StringBuilder sb = new StringBuilder();
			int last = 0;
			for (int i = 0; i < abbr.length; i++) {
				char c = abbr[i];
				if (c >= 'a' && c <= 'z') {
					if (last != 0) {
						sb.append(last);
						last = 0;
					}
					sb.append(c);
				}
				else {
					last++;
				}
			}
			if (last != 0) sb.append(last);
			abbrs.add(sb.toString());
		} else {
			dfs(abbrs, abbr, idx + 1);
			char keep = abbr[idx];
			abbr[idx] = '1';
			dfs(abbrs, abbr, idx + 1);
			abbr[idx] = keep;
		}
	}
}