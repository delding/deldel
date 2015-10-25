/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
**/

public class Solution {
    public boolean isPalindrome(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        while (lo < hi) {
            char l = toLowercase(s.charAt(lo));
            char h = toLowercase(s.charAt(hi));
            if (!isalphanumeric(l)) lo++;
            else if (!isalphanumeric(h)) hi--;
            else if (l != h) return false;
            else {
                lo++;
                hi--;
            }
        }
        return true;
    }
    
    private char toLowercase(char c) {
        if (c - 'A' >= 0 && c - 'Z' <= 0) return (char)('a' + c - 'A');
        else return c;
    }
    private boolean isalphanumeric(char c) {
        if (c - '0' >= 0 && c - '9' <= 0) return true;
        if (c - 'a' >= 0 && c - 'z' <= 0) return true;
        return false;
    }
}
