import java.io.*;
import java.util.*;

public class gGames {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int M = Integer.parseInt(line[1]);
      Map<Integer, SenElf> sens = new HashMap<>();
      for (int i = 0; i < M; i++) {
        line = br.readLine().split(" ");
        int E = Integer.parseInt(line[0]);
        int K = Integer.parseInt(line[1]);
        int B = Integer.parseInt(line[2]);
        SenElf elf = new SenElf(E - 1, K);
        sens.put(E - 1, elf);
        line = br.readLine().split(" ");
        for (int j = 0; j < B; j++) {
          elf.friends.add(Integer.parseInt(line[j]) - 1);
        }
      }
      boolean[] assigned = new boolean[1 << N];
      int[] positions = new int[1 << N];
      String res = dfs(sens, 0, positions, assigned) ? "YES" : "NO";
      bw.write("Case #" + t + ": " + res);
      bw.newLine();
    }
    br.close();
    bw.close();
  }

  static boolean dfs(Map<Integer, SenElf> sens, int idx, int[] positions, boolean[] assigned) {
    if (idx == positions.length) {
      for (int i = 0; i < positions.length; i++) {
        int elf = positions[i];
        if (sens.containsKey(elf)) {
          SenElf e = sens.get(elf);
          int k = e.round;
          int groupNo = i / (1 << k);
          int start = groupNo * (1 << k);
          int end = start + (1 << k) - 1;
          for (int j = start; j <= end ; j++) {
            if (e.friends.contains(positions[j])) return false;
          }
        }
      }
      return true;
    }
    for (int i = 0; i < assigned.length; i++) {
      if (!assigned[i]) {
        assigned[i] = true;
        positions[idx] = i;
        if (dfs(sens, idx + 1, positions, assigned)) return true;
        assigned[i] = false;
      }
    }
    return false;
  }

  static class SenElf {
    int id;
    Set<Integer> friends = new HashSet<>();
    int round;

    SenElf(int i, int r) {
      id = i;
      round = r;
    }
  }
}