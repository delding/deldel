/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 * */

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.length() == t.length()) { // if same length, can be only differnt at on position
            boolean diff = false;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if (diff == true) return false;
                    diff = true;
                }
            }
            return diff; // Error: if diff true means diff at one char, if diff false means s and t are equal
        }
        else if (s.length() == t.length() -  1) {
            boolean erase = false;
            for (int i = 0, j = 0; i < s.length(); i++, j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    if (erase == true) return false;
                    erase = true; // erase j
                    i--;
                }
            }
            return true; // erase last char in t 
        }
        else if (s.length() -1 == t.length()) return isOneEditDistance(t, s);
        else return false; // length diff > 1
    }
}
