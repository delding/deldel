/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 */

public class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    for (int i1 = m - 1, i2 = n - 1, i = m + n - 1; i >= 0; i--) {
      if (i1 == -1) nums1[i] = nums2[i2--];
      else if (i2 == -1) break;
      else if (nums1[i1] < nums2[i2]) nums1[i] = nums2[i2--];
      else nums1[i] = nums1[i1--];
    }
  }
}