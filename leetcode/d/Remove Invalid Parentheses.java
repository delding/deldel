/**
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
 **/

public class Solution {
  public List<String> removeInvalidParentheses(String s) {
    Set<String> res = new HashSet<>();
    Queue<String> q = new ArrayDeque<>();
    boolean done = false;
    q.add(s);
    if (isValid(s)) { // edge case!
      res.add(s);
      done = true;
    }
    while (!done && !q.isEmpty()) {
      int size = q.size();
      Set<String> nextLevel = new HashSet<>(); // avoid duplicates otherwise TLE
      while (size-- > 0) {
        String str = q.poll();
        for (int i = 0; i < str.length(); i++) {
          if (str.charAt(i) == '(' || str.charAt(i) == ')') {
            String child = str.substring(0, i) + str.substring(i + 1);
            if (isValid(child)) {
              done = true;
              res.add(child);
            } else {
              nextLevel.add(child);
            }
          }
        }
      }
      q.addAll(nextLevel);
    }
    return new ArrayList<String>(res);
  }

  private boolean isValid(String s) {
    int l = 0, r = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') l++;
      else if (c == ')') {
        r++;
        if (r > l) return false;
      }
    }
    return r == l;
  }
}