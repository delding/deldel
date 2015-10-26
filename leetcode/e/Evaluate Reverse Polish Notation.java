/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 **/

public class Solution {
  public int evalRPN(String[] tokens) {
    Stack<String> st = new Stack();
    for (String token : tokens) {
      if ("+-*/".indexOf(token) == -1) st.push(token);
      else {
        int num2 = Integer.parseInt(st.pop());
        int num1 = Integer.parseInt(st.pop());
        int res = eval(num1, num2, token);
        st.push("" + res);
      }
    }
    return Integer.parseInt(st.pop());
  }

  private int eval(int num1, int num2, String token) {
    switch (token) {
      case "+":
        return num1 + num2;
      case "-":
        return num1 - num2;
      case "*":
        return num1 * num2;
      default:
        return num1 / num2;
    }
  }
}
