/**
 There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of the two sorted arrays.
 The overall run time complexity should be O(log (m+n)).
 **/

public class Solution {
  public double findMedianSortedArrays(int A[], int B[]) {
    if ((A.length + B.length) % 2 != 0) { // odd
      return (double) findKthSmallest((A.length + B.length) / 2 + 1, A, B, 0, A.length - 1, 0, B.length - 1);
    } else {
      double midLeft = (double) findKthSmallest((A.length + B.length) / 2, A, B, 0, A.length - 1, 0, B.length - 1);
      double midRight = (double) findKthSmallest((A.length + B.length) / 2 + 1, A, B, 0, A.length - 1, 0, B.length - 1);
      return (midLeft + midRight) / 2;
    }
  }

  int findKthSmallest(int k, int[] A, int[] B, int aFirst, int aLast, int bFirst, int bLast) {
    if (aFirst > aLast) return B[bFirst + k - 1]; // bug, edge case
    if (bFirst > bLast) return A[aFirst + k - 1];
    int aMid = (aFirst + aLast) / 2;
    int bMid = (bFirst + bLast) / 2;
    if (A[aMid] > B[bMid]) {
      // + 2 makes both aMid and bMid belong to left part
      if ((aMid - aFirst + bMid - bFirst + 2) > k) { // target cannot be at right part of aMid, aMid belongs to right part, bMid belongs to left part
        // discard right part of A including aMid
        return findKthSmallest(k, A, B, aFirst, aMid - 1, bFirst, bLast);
      } else { // target cannot be at left part of B
        // discard left part of B including bMid
        // NOTE: don't forget substract bFirst
        return findKthSmallest(k - (bMid - bFirst + 1), A, B, aFirst, aLast, bMid + 1, bLast);
      }
    } else {
      if ((aMid - aFirst + bMid - bFirst + 2) > k) { //target cannot be at right part of B
        return findKthSmallest(k, A, B, aFirst, aLast, bFirst, bMid - 1);
      } else { // target cannot be at left part of A
        return findKthSmallest(k - (aMid - aFirst + 1), A, B, aMid + 1, aLast, bFirst, bLast);
      }
    }
  }
}