/**
 An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region.
 Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned)
 rectangle that encloses all black pixels.

 For example, given the following image:

 [
 "0010",
 "0110",
 "0100"
 ]
 and x = 0, y = 2,
 Return 6.
 **/

public class Solution {
  public int minArea(char[][] image, int x, int y) {
    if (image.length == 0 || image[0].length == 0) return 0;
    Queue<Rect> q = new ArrayDeque<>();
    q.add(new Rect(x, y));
    image[x][y] = '2';
    int top = x, bot = x;
    int left = y, right = y;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    while (!q.isEmpty()) {
      Rect rect = q.poll();
      for (int[] dir : dirs) {
        int i = rect.x + dir[0];
        int j = rect.y + dir[1];
        if (i >= 0 && j >= 0 && i < image.length && j < image[0].length) {
          if (image[i][j] == '1') {
            q.add(new Rect(i, j));
            image[i][j] = '2';
            top = Math.min(top, i);
            bot = Math.max(bot, i);
            left = Math.min(left, j);
            right = Math.max(right, j);
          }
        }
      }
    }
    return (bot - top + 1) * (right - left + 1);
  }

  private class Rect {
    int x;
    int y;

    Rect(int xx, int yy) {
      x = xx;
      y = yy;
    }
  }
}