/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 */

public class Solution {
  // find the minimum index such that citations[index] >= citations.length - index, i.e. citations >= book number
  public int hIndex(int[] citations) {
    int n = citations.length;
    int l = 0, r = n - 1;
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (citations[m] >= n - m) r = m - 1; // try check smaller index
      else l = m + 1;
    }
    return n - l;
  }
}