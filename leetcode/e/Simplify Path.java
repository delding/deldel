/**
 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 click to show corner cases.

 Corner Cases:
 Did you consider the case where path = "/../"?
 In this case, you should return "/".
 Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 In this case, you should ignore redundant slashes and return "/home/foo".
 **/

public class Solution {
  public String simplifyPath(String path) {
    Stack<String> st = new Stack<>();
    String[] p = path.split("/");
    for (String s : p) {
      if (s.equals(".")) {
        continue;
      } else if (s.equals("..")) {
        if (!st.isEmpty()) st.pop();
      } else if (!s.isEmpty()) st.push(s);
    }
    String ret = "";
    while (!st.isEmpty()) {
      ret = "/" + st.pop() + ret;
    }
    if (ret == "") ret = "/";
    return ret;
  }
}