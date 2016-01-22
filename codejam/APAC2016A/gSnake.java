// not pass
import java.io.*;
import java.util.Deque;
import java.util.LinkedList;

public class gSnake {

  static final long limit = (long) 1e9;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int S = Integer.parseInt(line[0]);
      int R = Integer.parseInt(line[1]);
      int C = Integer.parseInt(line[2]);
      int[][] board = new int[R+1][C+1];
      for (int i = 1; i <= R; i++) {
        for (int j = 1; j <= C; j++) {
          if ((i + j) % 2 == 1) {
            board[i][j] = 1;
          } else board[i][j] = 0;
        }
      }
      board[1][1] = 2; // 0: nothing 1: food 2: body
      long preTime = 1;
      int face = 0; // 0: right, 1: left, 2: up 3: down
      int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      boolean over = false;
      Deque<int[]> snake = new LinkedList<>();
      snake.add(new int[]{1, 1});
      for (int s = 0; s < S; s++) {
        line = br.readLine().split(" ");
        if (over) continue;
        long currTime = Long.parseLong(line[0]);
        if (currTime >= limit) {
          currTime = limit;
          over = true;
        }
        long span = currTime - preTime;
        if (span == 0) continue;
        if (face == 0 || face == 1) {
          span = span % C + C;
        }  else {
          span = span % R + R;
        }
        int[] head = snake.peekFirst();
        while (span-- > 0) {
          int i = head[0] + dirs[face][0];
          if (i == 0) i = R;
          if (i == R + 1) i = 1;
          int j = head[1] + dirs[face][1];
          if (j == 0) j = C;
          if (j == C + 1) j = 1;
          if (board[i][j] == 1) {
            snake.addFirst(new int[]{i, j});
            if (snake.size() > 2) {
              board[head[0]][head[1]] = 2;
            }
          } else if (board[i][j] == 0) {
            snake.addFirst(new int[]{i, j});
            snake.pollLast();
            int[] tail = snake.peekLast();
            board[tail[0]][tail[1]] = 0;
          } else { // board = 2, hit body
            over = true;
            continue;
          }
        }
        String act = line[1];
        if (act.equals("R")) {
          if (face == 0) face = 3;
          else if (face == 1) face = 2;
          else if (face == 2) face = 0;
          else face = 1;
        } else {
          if (face == 0) face = 2;
          else if (face == 1) face = 3;
          else if (face == 2) face = 1;
          else face = 0;
        }
        preTime = currTime;
      }
      bw.write("Case #" + t + ": " + snake.size());
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}

