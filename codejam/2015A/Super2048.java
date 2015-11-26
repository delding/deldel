// past test
import java.io.*;

public class Super2048 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] s = br.readLine().split(" ");
      int size = Integer.parseInt(s[0]);
      String dir = s[1];
      String[][] board = new String[size][size];
      for (int i = 0; i < size; i++) {
        board[i] = br.readLine().split(" ");
      }
      switch (dir) {
        case "left":
          for (int i = 0; i < size; i++) {
            boolean empty = false;
            for (int j = 0; j < size; ) {
              String val = board[i][j];
              empty = val.equals("0");
              int k = j + 1;
              for ( ; k < size; k++) {
                if (board[i][k].equals("0")) continue;;
                if (!empty) {
                  if (board[i][k].equals(val)) {
                    board[i][j] = (Integer.parseInt(val) * 2) + "";
                    board[i][k] = "0";
                  }
                  j++;
                } else {
                  board[i][j] = board[i][k];
                  board[i][k] = "0";
                }
                break;
              }
              if (k == size) break;
            }
          }
          break;
        case "right":
          for (int i = 0; i < size; i++) {
            boolean empty = false;
            for (int j = size - 1; j >= 0; ) {
              String val = board[i][j];
              empty = val.equals("0");
              int k = j - 1;
              for ( ; k >= 0; k--) {
                if (board[i][k].equals("0")) continue;;
                if (!empty) {
                  if (board[i][k].equals(val)) {
                    board[i][j] = (Integer.parseInt(val) * 2) + "";
                    board[i][k] = "0";
                  }
                  j--;
                } else {
                  board[i][j] = board[i][k];
                  board[i][k] = "0";
                }
                break;
              }
              if (k == -1) break;
            }
          }
          break;
        case "down":
          for (int j = 0; j < size; j++) {
            boolean empty = false;
            for (int i = size - 1; i >= 0; ) {
              String val = board[i][j];
              empty = val.equals("0");
              int k = i - 1;
              for ( ; k >= 0; k--) {
                if (board[k][j].equals("0")) continue;;
                if (!empty) {
                  if (board[k][j].equals(val)) {
                    board[i][j] = (Integer.parseInt(val) * 2) + "";
                    board[k][j] = "0";
                  }
                  i--;
                } else {
                  board[i][j] = board[k][j];
                  board[k][j] = "0";
                }
                break;
              }
              if (k == -1) break;
            }
          }
          break;
        case "up":
          for (int j = 0; j < size; j++) {
            boolean empty = false;
            for (int i = 0; i < size; ) {
              String val = board[i][j];
              empty = val.equals("0");
              int k = i + 1;
              for ( ; k < size; k++) {
                if (board[k][j].equals("0")) continue;;
                if (!empty) {
                  if (board[k][j].equals(val)) {
                    board[i][j] = (Integer.parseInt(val) * 2) + "";
                    board[k][j] = "0";
                  }
                  i++;
                } else {
                  board[i][j] = board[k][j];
                  board[k][j] = "0";
                }
                break;
              }
              if (k == size) break;
            }
          }
          break;
      }
      bw.write("Case #" + t + ":\n");
      for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
          bw.write(board[i][j] + " ");
        }
        bw.newLine();
      }
    }
    br.close();
    bw.close();
  }
}