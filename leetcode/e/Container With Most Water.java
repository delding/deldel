/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn
 * such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 * <p>
 * Note: You may not slant the container.
 **/

public class Solution {
  public int maxArea(int[] height) {
    int lo = 0;
    int hi = height.length - 1;
    int max = 0;
    while (lo < hi) {
      max = Math.max(max, (hi - lo) * Math.min(height[hi], height[lo]));
      if (height[hi] <= height[lo]) {
        hi--;
      } else {
        lo++;
      }
    }
    return max;
  }
}