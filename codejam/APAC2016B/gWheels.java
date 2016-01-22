// pass
import java.io.*;
import java.util.HashSet;
import java.util.Set;

// brute force need to choose one p, two e, one q, O(n^4)
// pre-compute ratios of all pair of e, run time reduce to O(n^2)
public class gWheels {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      br.readLine();
      String[] line = br.readLine().split(" ");
      int Np = Integer.parseInt(line[0]);
      int Ne = Integer.parseInt(line[1]);
      int Nt = Integer.parseInt(line[2]);
      line = br.readLine().split(" ");
      int[] PP = new int[Np];
      for (int i = 0; i < Np; i++) {
        PP[i] = Integer.parseInt(line[i]);
      }
      line = br.readLine().split(" ");
      int[] EE = new int[Ne];
      for (int i = 0; i < Ne; i++) {
        EE[i] = Integer.parseInt(line[i]);
      }
      line = br.readLine().split(" ");
      int[] TT = new int[Nt];
      for (int i = 0; i < Nt; i++) {
        TT[i] = Integer.parseInt(line[i]);
      }
      Set<E> eSet = new HashSet<>();
      for (int i = 0; i < Ne; i++) {
        for (int j = 0; j < Ne; j++) {
          if (i == j) continue;
          long gcd = gcd(EE[i], EE[j]);
          eSet.add(new E(EE[i] / gcd, EE[j] / gcd));
        }
      }
      int query = Integer.parseInt(br.readLine());
      bw.write("Case #" + t + ":");
      bw.newLine();
      for (int i = 0; i < query; i++) {
        line = br.readLine().split(" ");
        long P = Long.parseLong(line[0]);
        long Q = Long.parseLong(line[1]);
        boolean found = false;
        for (int j = 0; j < Np; j++) {
          if (found) break;
          for (int k = 0; k < Nt; k++) {
            int pp = PP[j];
            int tt = TT[k];
            long v1 = pp * Q;
            long v2 = tt * P;
            long gcd = gcd(v1, v2);
            if (eSet.contains(new E(v1 / gcd, v2 / gcd)) || eSet.contains(new E(v2 / gcd, v1 / gcd))) {
              found = true;
              break;
            }
          }
        }
        if (found) bw.write("Yes");
        else bw.write("No");
        bw.newLine();
      }
    }
    bw.close();
    br.close();
  }

  static long gcd(long e1, long e2) {
    if (e1 < e2) return gcd(e2, e1);
    while (e2 != 0) {
      long tmp = e2;
      e2 = e1 % e2;
      e1 = tmp;
    }
    return e1;
  }

  static class E {
    long e1;
    long e2;

    E(long ee1, long ee2) {
      e1 = ee1;
      e2 = ee2;
    }
    // to define equality to check contains MUST override both equals and hashCode
    public boolean equals (Object that) {  // BUG: parameter is Object
      E t = (E)that;
      return this.e1 ==t.e1 && this.e2 == t.e2;
    }
    public int hashCode() {
      return (int) (e1 + e2);
    }
  }
}
