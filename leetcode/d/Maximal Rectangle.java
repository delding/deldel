/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 */

public class Solution {
  // stack, largest rect in histogram
  public int maximalRectangle(char[][] matrix) {
    int m = matrix.length;
    if (m == 0 || matrix[0].length == 0) return 0;
    int n = matrix[0].length;
    int[] hist = new int[n];
    int maximal = 0;
    for (int i = 0; i < m; i++) { // histogram based on row i
      for (int j = 0; j < n; j++) {
        hist[j] = matrix[i][j] == '0' ? 0 : hist[j] + 1;
      }
      Deque<Integer> st = new ArrayDeque<>();
      for (int j = 0; j < n;) {
        if (st.isEmpty() || hist[j] >= hist[st.peek()]) {
          st.push(j++);
        } else {
          // elements on both side of st.pop(), (st.peek(), j) are >= hist[st.pop()]
          // these elements have been poped out
          int h = hist[st.pop()];
          int l = st.isEmpty() ? 0 : st.peek() + 1;
          maximal = Math.max(maximal, h * (j - l));
        }
      }
      while (!st.isEmpty()) {
        int h = hist[st.pop()];
        int l = st.isEmpty() ? 0 : st.peek() + 1;
        maximal = Math.max(maximal, h * (n - l));
      }
    }
    return maximal;
  }
}
