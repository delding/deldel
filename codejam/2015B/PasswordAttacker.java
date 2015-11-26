// pass
import java.io.*;

public class PasswordAttacker {

  static final int MODULO = 1000000007;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int M = Integer.parseInt(line[0]);
      int N = Integer.parseInt(line[1]);
      long[][] dp = new long[N + 1][M + 1]; // imagine a string with length i derived by add one char to string with length i - 1
      for (int i = 1; i <= N ; i++) {
        dp[i][1] = 1;
      }
      for (int i = 2; i <= N ; i++) {
        for (int j = 2; j <= Math.min(i, M) ; j++) {
          // dp(i-1,j) use all j number, so i can add any of j number
          // dp(i-1,j-1) use j-1 number, it can miss any single one of j number, so also multiply by j, and i add the missing one
            dp[i][j] = dp[i -1][j] * j % MODULO + dp[i - 1][j - 1] * j % MODULO;
            dp[i][j] %= MODULO;
        }
      }
      bw.write("Case #" + t + ": " + dp[N][M]);
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}