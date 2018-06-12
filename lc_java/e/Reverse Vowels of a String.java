/**
 Write a function that takes a string as input and reverse only the vowels of a string.

 Example 1:
 Given s = "hello", return "holle".

 Example 2:
 Given s = "leetcode", return "leotcede".
**/

public class Solution {
	public String reverseVowels(String s) {
		String vowels = "aeiouAEIOU";
		char[] ca = s.toCharArray();
		int i = 0;
		int j = ca.length - 1;
		while (i < j) {
			if (vowels.indexOf(ca[i]) == -1) i++;
			else if (vowels.indexOf(ca[j]) == -1) j--;
			else {
				char tmp = ca[i];
				ca[i] = ca[j];
				ca[j] = tmp;
				i++;
				j--;
			}
		}
		return String.valueOf(ca);
	}
}

