/**
 Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

 A string such as "word" contains only the following valid abbreviations:

 ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

 Note:
 Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

 Example 1:
 Given s = "internationalization", abbr = "i12iz4n":

 Return true.
 Example 2:
 Given s = "apple", abbr = "a2e":

 Return false.
 **/

public class Solution {
	public boolean validWordAbbreviation(String word, String abbr) {
		int num = 0;
		int i = 0, j = 0;
		for (; i < word.length() && j < abbr.length();) {
			if ('0' <= abbr.charAt(j) && abbr.charAt(j) <= '9') {
				if (num == 0 && abbr.charAt(j) == '0') return false; // edge case: invalid number in abbr
				while (j < abbr.length() && '0' <= abbr.charAt(j) && abbr.charAt(j) <= '9') {
					num = 10 * num + (abbr.charAt(j) - '0');
					j++;
				}
				i += num;
				num = 0;
			} else {
				if (word.charAt(i++) != abbr.charAt(j++)) return false;
			}
		}
		return i == word.length() && j == abbr.length();
	}
}