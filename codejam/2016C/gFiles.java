import java.io.*;
import java.util.*;

public class gFiles {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(args[0]));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      int N = Integer.parseInt(br.readLine());
      long lowbound = Long.MIN_VALUE;
      long upbound = Long.MAX_VALUE;
      long num = -1;
      boolean ok = false;
      for (int i = 0; i < N; i++) {
        String[] line = br.readLine().split(" ");
        if (ok) continue;
        long P = Integer.parseInt(line[0]);
        long K = Long.parseLong(line[1]);
        //if (P == 0) continue; // bug: P = 0 cannot compute upper bound but can still compute lower bound
        if (P == 100) {
          num = K;
          ok = true; // bug: can not break, must still consume inputs
        }
        lowbound = Math.max(lowbound, K * 100 / (P + 1) + 1L); // multiply 100 first, if divide first could result in 0
        if (P > 0) {
          upbound = Math.min(upbound, K * 100 / P) ;
        }
      }
      if (!ok) {
        num = lowbound == upbound ? lowbound : -1;
      }
      bw.write("Case #" + t + ": " + num);
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}