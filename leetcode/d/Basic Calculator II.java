/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
* */

public class Solution {
    public int calculate(String s) {
        Deque<String> deq = new LinkedList<String>();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i <= s.length(); i++) {
            if (i == s.length() || "+-*/".indexOf(s.charAt(i)) != -1) { // ERROR: remember to handle last number, so use i=s.length() to handle last one
                String num2 = num.toString();
                num.delete(0, num.length());
                if (!deq.isEmpty()) {
                    String op = deq.peekLast();
                    if (op.equals("*") || op.equals("/")) { // only compute * and /
                        deq.pollLast();
                        int num1 = Integer.parseInt(deq.pollLast());
                        if (op.equals("*")) num2 = "" + num1 * Integer.parseInt(num2);
                        if (op.equals("/")) num2 = "" + num1 / Integer.parseInt(num2);
                    }
                }
                deq.addLast(num2);
                if (i != s.length()) { // meet an operator, rather than hitting end of input
                    deq.addLast("" + s.charAt(i));
                } 
            } else if ("+-*/ ".indexOf(s.charAt(i)) == -1) { // note "+-*/ " here also contains whitespace
                num.append(s.charAt(i));
            } // else represent meet a whitespace, so do nothing
        }
        while (deq.size() > 1) {
            int num1 = Integer.parseInt(deq.pollFirst());
            String op = deq.pollFirst();
            int num2 = Integer.parseInt(deq.pollFirst());
            if (op.equals("+")) num2 = num1 + num2;
            if (op.equals("-")) num2 = num1 - num2;
            deq.addFirst("" + num2);
        }
        return Integer.parseInt(deq.poll());
    }
}
