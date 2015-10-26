/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * <p>
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 */

public class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] ret = new int[numCourses];
    Stack<Integer> postorder = new Stack();
    boolean[] visited = new boolean[numCourses];
    boolean[] visiting = new boolean[numCourses];
    int[][] adj = new int[numCourses][];
    for (int i = 0; i < numCourses; i++) { // find out each vertex has how many edges
      int edgeCount = 0;
      for (int[] e : prerequisites) {
        if (e[1] == i) edgeCount++;
      }
      adj[i] = new int[edgeCount];
    }
    for (int i = 0; i < numCourses; i++) { // assign edges to each vertex
      int edgeCount = 0;
      for (int[] e : prerequisites) {
        if (e[1] == i) adj[i][edgeCount++] = e[0];
      }
    }
    for (int i = 0; i < numCourses; i++) {
      if (!visited[i]) {
        if (hasCycle(i, adj, postorder, visited, visiting)) return new int[0];
      }
    }
    for (int i = 0; i < numCourses; i++) {
      ret[i] = postorder.pop();
    }
    return ret;
  }


  // dfs while check cycle
  private boolean hasCycle(int node, int[][] adj, Stack<Integer> postorder, boolean[] visited, boolean[] visiting) {
    visiting[node] = true;
    for (int child : adj[node]) {
      if (!visited[child]) {
        if (visiting[child]) return true;
        else {
          if (hasCycle(child, adj, postorder, visited, visiting)) return true;
        }
      }
    }
    visiting[node] = false;
    visited[node] = true;
    postorder.push(node);
    return false;
  }
}
