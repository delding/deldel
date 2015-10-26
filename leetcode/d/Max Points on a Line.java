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
    if (points.length == 0) return 0;
    int globalmax = 0;
    for (int i = 0; i < points.length - 1; i++) { // total n choose 2 different point pair
      int localmax = 0;
      Point p1 = points[i];
      Map<Double, Integer> slopes = new HashMap();
      int infSlope = 0;
      int same = 0; // number of points that are same as p1, so they will on every lines containing p1
      for (int j = i + 1; j < points.length; j++) {
        Point p2 = points[j];
        if (p1.x == p2.x && p1.y == p2.y) same++;
        else if (p1.x == p2.x) infSlope++;
        else {
          double slope = 0.0;
          if (p1.y != p2.y) {// java define -0.0 so need to regard 0.0 and -0.0 as same
            slope = (double) (p1.y - p2.y) / (double) (p1.x - p2.x);
          }
          Integer count = slopes.get(slope);
          if (count == null) slopes.put(slope, 1);
          else slopes.put(slope, ++count);
          if (count == null) localmax = Math.max(localmax, 1);
          else localmax = Math.max(localmax, count);
        }
      }
      localmax = Math.max(localmax, infSlope);
      localmax += same;
      globalmax = Math.max(localmax, globalmax);
    }
    return globalmax + 1; // EORROR: need to add the point itself
  }
}
