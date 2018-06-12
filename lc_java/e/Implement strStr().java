/**
 * Implement strStr().
 * <p>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 **/

public class Solution {
  public int strStr(String haystack, String needle) {
    for (int i = 0; i <= haystack.length() - needle.length(); i++) { // the upbound of i is an optimization
      for (int j = i, k = 0; j <= haystack.length() && k <= needle.length(); j++, k++) {
        if (k == needle.length()) return i;
        if (j == haystack.length() || haystack.charAt(j) != needle.charAt(k)) break;
      }
    }
    return -1;
  }
}

public class Solution {
  public int strStr(String haystack, String needle) {
    if (needle.isEmpty()) return 0; //edge case
    int len = needle.length();
    int j = 0;
    for (int i = 0; i < haystack.length() - len + 1; i++) { // bug: edge case makes index out of range here
      if (haystack.charAt(i) == needle.charAt(j)) {
        int k = i;
        while (j < len && haystack.charAt(k) == needle.charAt(j)) {
          k++;
          j++;
        }
        if (j == len) return i;
        j = 0;
      }
    }
    return -1;
  }
}