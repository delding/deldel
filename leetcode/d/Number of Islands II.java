/**
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation
 * which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number
 * of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * Example:
 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
 * 0 0 0
 * 0 0 0
 * 0 0 0
 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
 * 1 0 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
 * 1 1 0
 * 0 0 0   Number of islands = 1
 * 0 0 0
 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 2
 * 0 0 0
 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
 * 1 1 0
 * 0 0 1   Number of islands = 3
 * 0 1 0
 * We return the result as an array: [1, 1, 2, 3]
 **/

public class Solution {
  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> nums = new ArrayList<>();
    int num = 0;
    int[] parents = new int[m * n];
    Arrays.fill(parents, -1); // -1 means water
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] land : positions) {
      int x = land[0];
      int y = land[1];
      if (parents[x * n + y] == -1) { // multiply column number to convert to one dimentional array
        num++;
        parents[x * n + y] = x * n + y;
        for (int[] dir : dirs) {
          int xx = x + dir[0];
          int yy = y + dir[1];
          if (xx >= 0 && yy >= 0 && xx < m && yy < n && parents[xx * n + yy] != -1) {
            if (union(parents, x * n + y, xx * n + yy)) num--;
          }
        }
      }
      nums.add(num);
    }
    return nums;
  }

  private boolean union(int[] parents, int pos1, int pos2) { // not optimize by set head of smaller set to head of larger set
    int parent1 = find(parents, pos1);
    int parent2 = find(parents, pos2);
    if (parent1 == parent2) return false;
    else {
      parents[parent2] = parent1; // union
      return true;
    }
  }

  private int find(int[] parents, int pos) {
    while (parents[pos] != pos) {
      int parent = parents[pos];
      parents[pos] = parents[parent]; // optimize by compaction
      pos = parent;
    }
    return pos;
  }
}

// solution 2
public class Solution {
  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    List<Integer> counts = new ArrayList<>();
    int count = 0;
    Node[][] b = new Node[m][n];
    for (int[] pos : positions) {
      int x = pos[0];
      int y = pos[1];
      b[x][y] = new Node(); // better to first check if b[x][y] == null, that is it's water
      count++;
      for (int[] d : new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1 ,0}}) {
        int i = x + d[0];
        int j = y + d[1];
        if (i >= 0 && j >= 0 && i < m && j < n) {
          if (b[i][j] != null && union(b[x][y], b[i][j])) {
            count--;
          }
        }
      }
      counts.add(count);
    }
    return counts;
  }

  static class Node {
    Node parent;
    int size = 1;
    Node () {
      parent = this;
    }
  }

  boolean union(Node n1, Node n2) {
    if (n1.size > n2.size) union(n2, n1);
    Node p1 = find(n1);
    Node p2 = find(n2);
    if (p1 == p2) return false;
    p1.parent = p2;
    p2.size += p1.size;
    return true;
  }

  Node find(Node n) {
    while (n.parent != n) {
      Node p = n.parent;
      n.parent = p.parent; // compression
      n = p;
    }
    return n;
  }
}