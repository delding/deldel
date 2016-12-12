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

// calculate the dist for first point,  distance is updated by the prev + i * diff - (len - i) * diff,
// i * diff means the first half, (len - i) means the second half.
public class Solution {
  public int minTotalDistance(int[][] grid) {
    int[] x = new int[grid.length];
    int[] y = new int[grid[0].length];
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        x[i] += grid[i][j];
        y[j] += grid[i][j];
      }
    }
    return minDist1D(x) + minDist1D(y);
  }

  int minDist1D(int[] grid) {
    int dist = 0;
    int l = 0, r = 0; // number of people on the left and right
    for (int i = 1; i < grid.length; i++) {
      dist += i * grid[i];
      r += grid[i];
    }
    int curDist = dist;
    for (int i = 1; i < grid.length; i++) {
      curDist += l + grid[i - 1] - r;
      l += grid[i - 1];
      r -= grid[i];
      dist = Math.min(curDist, dist);
    }
    return dist;
  }
}