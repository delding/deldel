import java.lang.Integer;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * <p>
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * <p>
 * <p>
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 **/

// TLE for huge test case [1, 1, ..., 1, 1]
public class Solution {
  public int largestRectangleArea(int[] height) {
    if (height.length == 0) return 0;
    Stack<Integer> st = new Stack<Integer>();
    st.push(0);
    int area = 0;
    for (int i = 1; i < height.length; ) {
      if (!st.isEmpty() && height[i] < height[st.peek()]) {
        int h = height[st.pop()];
        int left = st.isEmpty() ? 0 : st.peek() + 1;
        int width = i - left;
        area = Math.max(area, width * h);
      } else {
        st.push(i++);
      }
    }
    while (!st.isEmpty()) {
      int h = height[st.pop()];
      int left = st.isEmpty() ? 0 : st.peek() + 1; // all heights between st.peek() + 1 and height.length - 1 are higher than or equal to h
      int width = height.length - left;
      area = Math.max(area, h * width);
    }
    return area;
  }
}
