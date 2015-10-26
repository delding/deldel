import java.lang.Character;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 **/

public class Solution {
  public boolean isValid(String s) {
    Stack<Character> st = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '[' || c == '{') {
        st.push(c);
      } else {
        if (st.isEmpty()) return false;
        char cc = st.pop();
        if (!(c == ')' && cc == '(' || c == ']' && cc == '[' || c == '}' && cc == '{')) {
          return false;
        }
      }
    }
    return st.isEmpty(); // bug : must check if stack is empty
  }
}