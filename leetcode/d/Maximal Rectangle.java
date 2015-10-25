/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

public class Solution {
  public int maximalRectangle(char[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return 0;
    int[] hist = new int[matrix[0].length];
    int maxArea = 0;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        hist[j] = matrix[i][j] == '0' ? 0 : hist[j] + 1;
      }
      Stack<Integer> st = new Stack<>();
      for (int j = 0; j < hist.length; ) {
        if (!st.isEmpty() && hist[j] < hist[st.peek()]) {
          int h = hist[st.pop()];
          int left = st.isEmpty() ? 0 : st.peek() + 1;
          int w = j - left; // all elements between st.peek() + 1 and j - 1 inclusive are bigger than or equal to h
          maxArea = Math.max(maxArea, w * h);
        } else st.push(j++);
      }
      while (!st.isEmpty()) {
        int h = hist[st.pop()];
        int left = st.isEmpty() ? 0 : st.peek() + 1;
        int w = hist.length - left;
        maxArea = Math.max(maxArea, w * h);
      }
    }
    return maxArea;
  }
}