// pass
import java.io.*;

// reverse makes it symmetric to the middle 0, switch makes it symmetric in the sense of 1 to 0 and 0 to 1
public class GoogolString {

  // BUG: MUST use 1L to indicate this is a long literal not a int
  static long top = 1L << 61 - 1; // K <= 10^18 which is 2^60, so must use 2^61 to contain K

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      long K = Long.parseLong(br.readLine());
      int res = solve(K, top);
      bw.write("Case #" + t + ": " + res);
      bw.newLine();
    }
    bw.close();
    br.close();
  }

  static int solve(long K, long top) {
    long mid =  (top + 1) / 2;
    if (K == mid) return 0; // power of 2
    else if (K < mid) return solve(K, mid - 1);
    else return 1 - solve(mid - (K - mid), mid - 1); // 1 - make result flip
  }
}
