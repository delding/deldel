// pass
import java.io.*;

public class gCube {

  // big data set will cause double overflow for multiplication when computing volume, MUST use log
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int Q = Integer.parseInt(line[1]);
      line = br.readLine().split(" ");
      int[] sides = new int[N];
      for (int i = 0; i < N; i++) {
        sides[i] = Integer.parseInt(line[i]);
      }
      bw.write("Case #" + t + ":");
      bw.newLine();
      for (int i = 0; i < Q; i++) {
        String[] q = br.readLine().split(" ");
        int lo = Integer.parseInt(q[0]);
        int hi = Integer.parseInt(q[1]);
        double logV = 0; // use log to avoid multiplication overflow
        for (int j = 0; j < N; j++) {
          if (j >= lo && j <= hi) {
            logV += Math.log(sides[j]);
          }
        }
        double d = hi - lo + 1;
        double side = Math.exp(logV / d);
        bw.write(side + "");
        bw.newLine();
      }
    }
    br.close();
    bw.close();
  }
}