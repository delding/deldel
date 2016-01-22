// pass
import java.io.*;
import java.util.Arrays;

public class NewYearsEve {

  static double[][][] wine = new double[2][401][401]; // 2 means current level and next level, level l has (l+1)*l/2 glasses

  // put ALL wine in the top glass, put the overflow evenly in the three glasses under it
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int B = Integer.parseInt(line[0]);
      int L = Integer.parseInt(line[1]);
      int N = Integer.parseInt(line[2]);

      double total = B * 750;
      int curr = 0;
      int next = 1;
      for (int i = 0; i < 401; i++) {
        Arrays.fill(wine[next][i], 0.0); // BUG: must clear values for each test, it is static global
        Arrays.fill(wine[curr][i], 0.0);
      }
      wine[curr][1][1] = total;
      for (int l = 1; l < L; l++) {
        curr = (l + 1) % 2;
        next = l % 2;
        for (int i = 0; i < 401; i++) {
          Arrays.fill(wine[next][i], 0.0); // BUG: must clear values
        }
        for (int i = 1; i <= L; i++) {
          for (int j = 1; j <= i; j++) {
            if (wine[curr][i][j] > 250) {
              double overflow = wine[curr][i][j] - 250;
              wine[next][i][j] += overflow / 3;
              wine[next][i + 1][j] += overflow / 3;
              wine[next][i + 1][j + 1] += overflow / 3;
            }
          }
        }
      }
      double res = 0;
      for (int i = 1; i <= L; i++) {
        if (N == 0) break;
        for (int j = 1; j <= i; j++) {
          if (--N == 0) {
            res = wine[next][i][j] > 250 ? 250 : wine[next][i][j]; // BUG: compare with 250 first
            break;
          }
        }
      }
      bw.write("Case #" + t + ": " + res);
      bw.newLine();
    }
    bw.close();
    br.close();
  }
}
