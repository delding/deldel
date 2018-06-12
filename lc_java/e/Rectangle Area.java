/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <p>
 * Rectangle Area
 * Assume that the total area is never beyond the maximum possible value of int.
 */

public class Solution {
  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    int bot = Math.max(B, F);
    int left = Math.max(A, E);
    int right = Math.min(C, G);
    int top = Math.min(D, H);

    int overlap = 0;
    if (bot < top && left < right) overlap = (top - bot) * (right - left);
    return (C - A) * (D - B) + (G - E) * (H - F) - overlap;
  }
}
