import java.lang.Integer;

/**
 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 **/

public class Solution {
  public int longestValidParentheses(String s) {
    Stack<Integer> st = new Stack<>();
    int maxlen = 0;
    int start = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        st.push(i);
      } else {
        if (!st.isEmpty()) {
          st.pop();
          int left = st.isEmpty() ? start : st.peek() + 1;
          maxlen = Math.max(maxlen, i - left + 1);
        } else {
          start = i + 1;
        }
      }
    }
    return maxlen;
  }
}