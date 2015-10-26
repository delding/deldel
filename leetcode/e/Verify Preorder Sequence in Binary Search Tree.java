/**
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * <p>
 * You may assume each number in the sequence is unique.
 * <p>
 * Follow up:
 * Could you do it using only constant space complexity?
 */

public class Solution {
  public boolean verifyPreorder(int[] preorder) {
    return verify(0, preorder.length - 1, preorder);

  }

  boolean verify(int lo, int hi, int[] preorder) {
    if (lo >= hi) return true;
    int rootVal = preorder[lo];
    int mid = lo + 1;
    while (mid <= hi) {
      if (preorder[mid] > rootVal) break;
      mid++;
    }
    for (int i = mid; i <= hi; i++) {
      if (preorder[i] < rootVal)
        return false; // all value starting from mid should on the right subtree and so bigger than rootVal
    }
    return verify(lo + 1, mid - 1, preorder) && verify(mid, hi, preorder); // verify left and right subtree
  }
}
