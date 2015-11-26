// pass test
import java.io.*;

// find longest increasing sequence in the maze
public class CubeIV {
  static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    br.readLine();
    for (int t = 1; t <= T; t++) {
      int size = Integer.parseInt(br.readLine());
      int[][] maze = new int[size][size];
      for (int i = 0; i < size; i++) {
        String[] values = br.readLine().split(" ");
        for (int j = 0; j < size; j++) {
          maze[i][j] = Integer.parseInt(values[j]);
        }
      }
      int[][] scores = new int[size][size];
      int[] globalMax = new int[1];
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (scores[i][j] == 0) dfs(scores, maze, i, j, globalMax);
        }
      }
      int win = size * size + 1;
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (scores[i][j] == globalMax[0]) {
            win = Math.min(win, maze[i][j]);
          }
        }
      }
      bw.write("Case #" + t + ": " + win + " " + globalMax[0]);
      bw.newLine();
    }
    br.close();
    bw.close();
  }


  static int dfs(int[][] scores, int[][] maze, int i, int j, int[] globalMax) {
    if (scores[i][j] != 0) return scores[i][j];
    int maxScore = 1;
    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x >= 0 && x < maze.length && y >= 0 && y < maze.length && maze[x][y] == maze[i][j] + 1) {
        maxScore = Math.max(maxScore, 1 + dfs(scores, maze, x, y, globalMax));
      }
    }
    scores[i][j] = maxScore;
    globalMax[0] = Math.max(maxScore, globalMax[0]);
    return maxScore;
  }
}