// pass
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class BrokenCalculator {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] buttons = br.readLine().split(" ");
      int target = Integer.parseInt(br.readLine());
      String digits = "";
      for (int i = 0; i < 10; i++) {
        if (buttons[i].equals("1")) digits += i;
      }
      int ret = dfs(target, digits, new HashMap<Integer, Integer>()) + 1; // 1 for clicking =
      if (ret != 0) {
        bw.write("Case #" + t + ": " + ret);
      } else bw.write("Case #" + t + ": Impossible");
      bw.newLine();
    }
    br.close();
    bw.close();
  }

  static int dfs(int target, String digits, Map<Integer, Integer> memo) {
    if (memo.containsKey(target)) return memo.get(target);
    boolean can = true;
    for (char c : String.valueOf(target).toCharArray()) { // if all digits of a number are contained in the not broken set, then can type that number
      if (digits.indexOf(c) == -1) can = false;
    }
    if (can) return String.valueOf(target).length(); // this must be the min number of clicks because 99 * 99 five clicks, 9801 four clicks, * always wasted one click
    int min = -1;
    for (int i = 2; i * i <= target; i++) { // upbound for i is when i * i = target
      if (target % i == 0) {
        int first = dfs(i, digits, memo);
        int second = dfs(target / i, digits, memo);
        if (first != -1 && second != -1) {
          min = min == -1 ? first + second + 1 : Math.min(min, first + second + 1); // 1 is due to *
        }
      }
    }
    memo.put(target, min);
    return min;
  }
}
