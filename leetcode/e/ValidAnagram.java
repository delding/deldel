/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.
* */

public class Solution {
    public boolean isAnagram(String s, String t) {
        int[] count = new int[256];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        for (char c : t.toCharArray()) {
            count[c]--;
        }
        for (int i : count) {
            if(i != 0) return false;
        }
        return true;
    }
}
