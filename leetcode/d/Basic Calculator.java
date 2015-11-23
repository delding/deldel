/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * You may assume that the given expression is always valid.
 * Some examples:
 * "1 + 1" = 2
 * " 2-1 + 2 " = 3
 * "(1+(4+5+2)-3)+(6+8)" = 23
 */

public class Solution {
  public int calculate(String s) {
    Deque<String> deq = new LinkedList();
    StringBuilder num = new StringBuilder();
    for (int i = 0; i <= s.length(); i++) {
      if (i == s.length()) {
        if (num.length() != 0)
          deq.addLast(num.toString()); // ERROR: edge case, "(1)" as input, when reaching the end num can be empty
      } else {
        char c = s.charAt(i);
        if (c == ' ') continue;
        if (c == '(') deq.addLast("" + c);
        else if ("-+".indexOf(c) != -1) {
          if (num.length() != 0) { // ERROR: edge case "(1)+2", since ) before +, when reaching +, num is empty
            deq.addLast(num.toString());
            num.delete(0, num.length());
          }
          deq.addLast("" + c);
        } else if (c == ')') {
          if (num.length() != 0) {
            deq.addLast(num.toString());
            num.delete(0, num.length());
          }
          Stack<String> st = new Stack<String>();
          while (!deq.peekLast().equals("(")) {
            st.push(deq.pollLast());
          }
          deq.pollLast();
          while (st.size() > 1) {
            int num1 = Integer.parseInt(st.pop());
            String op = st.pop();
            int num2 = Integer.parseInt(st.pop());
            if (op.equals("+")) st.push(String.valueOf(num1 + num2));
            if (op.equals("-")) st.push(String.valueOf(num1 - num2));
          }
          deq.addLast(st.pop());
        } else num.append(c);
      }
    }
    while (deq.size() > 1) {
      int num1 = Integer.parseInt(deq.pollFirst());
      String op = deq.pollFirst();
      int num2 = Integer.parseInt(deq.pollFirst());
      if (op.equals("+")) deq.addFirst(String.valueOf(num1 + num2));
      if (op.equals("-")) deq.addFirst(String.valueOf(num1 - num2));
    }
    return Integer.parseInt(deq.poll());
  }
}
