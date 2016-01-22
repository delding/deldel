// pass
import java.io.*;

// zero can reveal nonzero cells but only trigger zero cells automatically spawn
public class Minesweeper {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      int size = Integer.parseInt(br.readLine());
      char board[][] = new char[size][size];
      for (int i = 0; i < size; i++) {
        board[i] = br.readLine().toCharArray();
      }
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          boolean isZero = true;
          for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1 ; l++) {
              int x = i +k;
              int y = j + l;
              if (x >=0 && x < size && y >= 0 && y < size) {
                if (board[x][y] == '*') {
                  isZero = false;
                  break;
                }
              }
            }
            if (!isZero) break;
          }
          if (isZero) board[i][j] = '0';
        }
      }
      int click = 0;
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (board[i][j] == '0') {
            click++;
            dfs(i, j, board, size);
          }
        }
      }
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          if (board[i][j] == '.') {
            click++;
          }
        }
      }
      bw.write("Case #" + t + ": " + click);
      bw.newLine();
    }
    br.close();
    bw.close();
  }

  static void dfs(int i, int j, char[][] board, int size) {
    board[i][j] = '#';
    for (int k = -1; k <= 1; k++) {
      for (int l = -1; l <= 1; l++) {
        int x = i + k;
        int y = j + l;
        if (x >=0 && x < size && y >= 0 && y < size) {
          if (board[x][y] == '0') {
            dfs(x, y, board, size);
          } else if (board[x][y] == '.') board[x][y] = '#'; // not trigger further spawn
        }
      }
    }
  }
}