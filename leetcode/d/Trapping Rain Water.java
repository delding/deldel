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

// another solution can be generalized to 2D version
public class Solution {
  public int trap(int[] height) {
    int l = 0;
    int r = height.length - 1;
    int vol = 0;
    while (l < r - 1) {
      if (height[l] <= height[r]) {
        if (height[l] > height[l + 1]) {
          vol += height[l] - height[l + 1];
          height[l + 1] = height[l];
        }
        l++;
      } else {
        if (height[r - 1] < height[r]) {
          vol += height[r] - height[r- 1];
          height[r-1] = height[r];
        }
        r--;
      }
    }
    return vol;
  }
}