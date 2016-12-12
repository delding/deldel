/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add
 * binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p>
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */

public class Solution {
  public List<String> addOperators(String num, int target) {
    List<String> exprs = new ArrayList<>();
    search(num, 0, target, exprs, "", 0, 0);
    return exprs;
  }

  void search(String num, int idx, int target, List<String> exprs, String expr, long curr, long prev) {
    if (idx == num.length()) {
      if (curr == target) exprs.add(expr);
    } else {
      for (int i = idx; i < num.length(); i++) {
        if (num.charAt(idx) == '0' && i > idx) break;
        long val = Long.parseLong(num.substring(idx, i + 1));
        if (idx == 0) search(num, i + 1, target, exprs, expr +  val, val, val);
        else {
          search(num, i + 1, target, exprs, expr + "+" + val, curr + val, val);
          search(num, i + 1, target, exprs, expr + "-" + val, curr - val, -val);
          search(num, i + 1, target, exprs, expr + "*" + val, curr - prev + prev * val, prev * val);
        }
      }
    }
  }
}