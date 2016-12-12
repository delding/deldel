/**
 Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

 Note: The input string may contain letters other than the parentheses ( and ).

 Examples:
 "()())()" -> ["()()()", "(())()"]
 "(a)())()" -> ["(a)()()", "(a())()"]
 ")(" -> [""]
 **/

public class Solution {
  // bfs
  public List<String> removeInvalidParentheses(String s) {
    Set<String> res = new HashSet<>();
    Queue<String> q = new ArrayDeque<>();
    q.offer(s);
    while (!q.isEmpty() && res.isEmpty()) {
      Set<String> nextLevel = new HashSet<>(); // avoid duplicate in next level
      while (!q.isEmpty()) {
        String paren = q.poll();
        if (valid(paren)) res.add(paren);
        if (res.isEmpty()) {
          for (int i = 0; i < paren.length(); i++) {
            if (paren.charAt(i) == '(' || paren.charAt(i) == ')') {
              String p = paren.substring(0, i) + paren.substring(i + 1);
              nextLevel.add(p);
            }
          }
        }
      }
      q.addAll(nextLevel);
    }
    return new ArrayList<String>(res);
  }

  boolean valid(String paren) {
    int diff = 0;
    for (char p : paren.toCharArray()) {
      if (p == '(') diff++;
      if (p == ')') diff--;
      if (diff < 0) return false;
    }
    return diff == 0;
  }
}