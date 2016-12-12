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
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be
 * taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 */

public class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> adj = new HashMap<>();
    for (int i = 0; i < numCourses; i++) adj.put(i, new HashSet<Integer>());
    for (int[] edge : prerequisites) {
      adj.get(edge[1]).add(edge[0]);
    }
    boolean[] visited = new boolean[numCourses];
    Deque<Integer> st = new ArrayDeque<>();
    boolean can = true;
    for (int v = 0; v < numCourses; v++) {
      if (!visited[v]) {
        if (!toposort(v, visited, new boolean[numCourses], adj, st)) return new int[0];
      }
    }
    int[] order = new int[numCourses];
    for (int i = 0; i < numCourses; i++) order[i] = st.pop();
    return order;
  }

  boolean toposort(int v, boolean[] visited, boolean[] visiting, Map<Integer, Set<Integer>> adj, Deque<Integer> st) {
    visiting[v] = true;
    for (int course : adj.get(v)) {
      if (visiting[course]) return false;
      if (!visited[course]) {
        if (!toposort(course, visited, visiting, adj, st)) return false;
      }
    }
    st.push(v);
    visiting[v] = false;
    visited[v] = true;
    return true;
  }
}