/*
 * You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
  */
public class Solution {
    static final int INF = Integer.MAX_VALUE;
    
    private class Pair {
        int x;
        int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        if (n == 0) return;
        Queue<Pair> q = new LinkedList<Pair>();    
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) q.offer(new Pair(i, j));
            }
        }
        int dist = 0;
        while (!q.isEmpty()) {
            dist++;
            int size = q.size();
            while (size-- > 0) {
                Pair room = q.poll();
                for (int i = -1; i <=1; i++) {
                    for (int j = -1; j <=1; j++) {
                        if (i != 0 && j != 0) continue; // (x-1,y), (x+1, y), (x, y-1), (x, y+1)
                        if (i == 0 && j == 0) continue;
                        int x = room.x +i;
                        int y = room.y +j;
                        if (x >=0 && x < m && y >=0 && y < n && rooms[x][y] == INF) {
                            rooms[x][y] = dist;
                            q.offer(new Pair(x, y));
                        }
                    }
                }
            }
        }
        
    }
}
