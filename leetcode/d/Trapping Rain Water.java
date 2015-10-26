/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * <p>
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 **/

// very similar to largest rectangular area, the difference is that no need to consider if stack is not empty after one pass through the data
// because heights left in stack are in decreasing order, can not hold water
public class Solution {
  public int trap(int[] height) {
    Stack<Integer> st = new Stack<>();
    int water = 0;
    for (int i = 0; i < height.length; ) {
      if (st.isEmpty() || height[st.peek()] >= height[i]) {
        st.push(i++);
      } else {
        int bottom = height[st.pop()];
        while (!st.isEmpty() && height[st.peek()] == bottom) st.pop();
        int h = st.isEmpty() ? bottom : Math.min(height[i], height[st.peek()]);
        int left = st.isEmpty() ? 0 : st.peek() + 1;
        water += (i - left) * (h - bottom);
      }
    }
    return water;
  }
}

// another solution, use prefix max and suffix max
public class Solution {
  public int trap(List<Integer> height) {
    int len = height.size();
    int[] lmax = new int[len];
    int[] rmax = new int[len];
    int max = 0;
    for (int i = len - 1; i >= 0; i--) {
      rmax[i] = max;
      max = Math.max(max, height.get(i));
    }
    max = 0;
    for (int i = 0; i < len; i++) {
      lmax[i] = max;
      max = Math.max(max, height.get(i));
    }
    int res = 0;
    for (int i = 1; i < len - 1; i++) {
      int bar = Math.min(lmax[i], rmax[i]);
      if (bar > height.get(i)) res += bar - height.get(i);
    }
    return res;
  }
}
