/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
  public int maxPoints(Point[] points) {
    int max = 0;
    for (int i = 0; i < points.length; i++) {
      Map<Integer, Map<Integer, Integer>> slopes = new HashMap<>();
      int origin = 1;
      int horizon = 0;
      int vertical = 0;
      int other = 0;
      for (int j = i + 1; j < points.length; j++) {
        int x = points[j].x - points[i].x;
        int y = points[j].y - points[i].y;
        if (x == 0 && y == 0) origin++;
        else if (x == 0) vertical++;
        else if (y == 0) horizon++;
        else {
          int g = gcd(x, y);
          x /= g;
          y /= g;
          int count = 1;
          if (slopes.containsKey(x)) {
            count += slopes.get(x).getOrDefault(y, 0);
          } else slopes.put(x, new HashMap<>());
          slopes.get(x).put(y, count);
          other = Math.max(other, count);
        }
      }
      max = Math.max(Math.max(Math.max(horizon, vertical), other) + origin, max);
    }
    return max;
  }

  int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
  }
}