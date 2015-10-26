/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 * <p>
 * <p>
 * Example 1
 * Input: "2-1-1".
 * <p>
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Output: [0, 2]
 * <p>
 * <p>
 * Example 2
 * Input: "2*3-4*5"
 * <p>
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 */

// O(n!), n is the number of operators
// method 1 (a single dfs tree, each solution is a leaf): pick an operator find its previous and next number then compute value, the root of dfs tree has n children, each child has n -1 child, each leaf represent a possible solution
// method 2 (many trees, each solution is a tree): each possible solution correspond to a full binary tree, every leaf is a number, every internal node is an operator, each time pick an operator as root, prefix is left subtree, suffix is right subtree, like merge sort but the merge part need to compute all combination of results returned from left subtree and right subtree, the root node has all the solutions corresponding to all possible full binary tree
public class Solution {

  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> rst = new ArrayList();
    // dfs1(rst, input);
    rst = dfs2(input);
    return rst;
  }

  static String operator = "+-*";

  // No need to differentiate operator '-' and '-' fot minus number, since dfs2 doesn't modify input
  private List<Integer> dfs2(String input) {
    List<Integer> rst = new ArrayList<Integer>();
    if (input.indexOf("+") == -1 && input.indexOf("-") == -1 && input.indexOf("*") == -1) {
      rst.add(Integer.parseInt(input));
      return rst;
    }
    for (int i = 0; i < input.length(); i++) {
      char op = input.charAt(i);
      if (operator.indexOf(op) != -1) {
        List<Integer> leftRst = dfs2(input.substring(0, i));
        List<Integer> rightRst = dfs2(input.substring(i + 1));
        for (int lv : leftRst) {
          for (int rv : rightRst) {
            int val;
            switch (op) {
              case '+':
                val = lv + rv;
                break;
              case '-':
                val = lv - rv;
                break;
              default:
                val = lv * rv;
            }
            rst.add(val);
          }
        }
      }
    }
    return rst;
  }

  // ERROR: must differentiate operator '-' and '-' fot minus number
  private void dfs1(List<Integer> rst, String input) {
    if (input.length() == 1)
      rst.add(Integer.parseInt(input)); // ERROR: len of number can be larger than 1, also number can be minus
    // iterate through input, for each operator branch a subtree
    for (int i = 0; i < input.length(); i++) {
      char op = input.charAt(i);
      if (op == '+' || op == '-' || op == '*') {
        int val = compute(i, input);
        int prev = i - 1;
        while (prev >= 0) {
          char prevOp = input.charAt(prev);
          if (prevOp == '+' || prevOp == '-' || prevOp == '*') break;
          else prev--;
        }
        String prefix = prev > 0 ? input.substring(0, prev + 1) : "";
        int next = i + 1;
        while (next < input.length()) {
          char nextOp = input.charAt(next);
          if (nextOp == '+' || nextOp == '-' || nextOp == '*') break;
          else next++;
        }
        String suffix = next < input.length() ? input.substring(next) : "";
        dfs1(rst, prefix + val + suffix);
      }
    }
  }

  private int compute(int i, String input) {
    char op = input.charAt(i);
    StringBuilder first = new StringBuilder();
    int j = i - 1;
    while (j >= 0) {
      char num = input.charAt(j);
      if (num >= '0' && num <= '9') {
        first.append(num);
        j--;
      } else break;
    }
    int num1 = Integer.parseInt(first.reverse().toString());
    j = i + 1;
    int num2 = 0;
    while (j < input.length()) {
      int num = (int) (input.charAt(j) - '0');
      if (num >= 0 && num <= 9) {
        num2 = num2 * 10 + num;
        j++;
      } else break;
    }
    int rst;
    switch (op) {
      case '+':
        rst = num1 + num2;
        break;
      case '-':
        rst = num1 - num2;
        break;
      case '*':
        rst = num1 * num2;
        break;
      default:
        rst = Integer.MIN_VALUE; // invalid op
    }
    return rst;
  }
}
