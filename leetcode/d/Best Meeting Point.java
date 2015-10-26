/**
 * A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1,
 * where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 * <p>
 * For example, given three people living at (0,0), (0,4), and (2,2):
 * <p>
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
 **/

// todo: computation of minx and miny can be optimized
// one assumption that the optimized point must fall on one of these non-zero points
// calculate the dist for first point,  distance is updated by the prev + i * diff - (len - i) * diff,
// i * diff means the first half, (len - i) means the second half.
public class Solution {
  public int minTotalDistance(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int[] distX = new int[grid[0].length];
    int[] distY = new int[grid.length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        distX[j] += grid[i][j];
      }
    }
    for (int j = 0; j < grid[0].length; j++) {
      for (int i = 0; i < grid.length; i++) {
        distY[i] += grid[i][j];
      }
    }
    int minx = Integer.MAX_VALUE;
    for (int i = 0; i < distX.length; i++) {
      int dist = 0;
      for (int j = 0; j < distX.length; j++) {
        dist += Math.abs(j - i) * distX[j];
      }
      minx = Math.min(minx, dist);
    }
    int miny = Integer.MAX_VALUE;
    for (int i = 0; i < distY.length; i++) {
      int dist = 0;
      for (int j = 0; j < distY.length; j++) {
        dist += Math.abs(j - i) * distY[j];
      }
      miny = Math.min(miny, dist);
    }
    return minx + miny;
  }
}