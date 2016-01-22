// pass
import java.io.*;

public class ParenthesesOrder {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int K = Integer.parseInt(line[1]);
      int[][] validSuffix = new int[N + 1][N + 1];
      for (int r = 1; r <= N; r++) {
        validSuffix[0][r] = 1;
      }
      for (int l = 1; l <= N; l++) {
        for (int r = l; r <= N; r++) {
          validSuffix[l][r] = validSuffix[l - 1][r] + validSuffix[l][r - 1];
        }
      }
      String res;
      if (K <= validSuffix[N][N]) {
        StringBuilder sb = new StringBuilder();
        int l = N;
        int r = N;
        while (l > 0 || r > 0) { // BUG: MUST first add a parenthesis then check the remaining valid suffixes, which is now based on current prefix
          if (l == 0) { // BUG, must add this to guard l-1 out of index when l = 0
            sb.append(")");
            r--;
          } else if (validSuffix[l - 1][r] >= K) {// BUG: must l - 1, which gives how many different valid suffix left if add "(", and if bigger than K, can add "("
            sb.append("(");
            l--;
          } else {
            sb.append(")");
            K -= validSuffix[l - 1][r]; // BUG: should be l - 1, adding ")" instead of "(" skip validSuffix[l - 1][r] number of smaller items
            r--;
          }
        }
        res = sb.toString();
      } else res = "Doesn't Exist!";
      bw.write("Case #" + t + ": " + res);
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}
