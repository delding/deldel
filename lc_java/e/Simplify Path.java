/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * <p>
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * click to show corner cases.
 * <p>
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 **/

public class Solution {
  public String simplifyPath(String path) {
    Deque<String> st = new ArrayDeque<>();
    for (String p : path.split("/")) {
      if (p.equals("..")) {
        if (!st.isEmpty()) st.pop();
      }
      else if (p.equals(".")) continue;
      else if (!p.isEmpty()) st.push(p);
    }
    String ret = "";
    while (!st.isEmpty()) {
      ret = "/" + st.pop() + ret;
    }
    return ret.isEmpty() ? "/" : ret;
  }
}
