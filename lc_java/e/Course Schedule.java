/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * <p>
 * 2, [[1,0],[0,1]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 * <p>
 * Note:
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * <p>
 * This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
 * Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
 * Topological sort could also be done via BFS.
 */

// needs to be a DAG, i.e. no cycle
public class Solution {
  // bfs, topo-sort by indegrees
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    int[] indegrees = new int[numCourses];
    Map<Integer, Set<Integer>> adj = new HashMap<>();
    for (int i = 0; i < numCourses; i++) adj.put(i, new HashSet<>());
    for (int[] edge : prerequisites) {
      // in case of multiple inputs of same edge, indegree of sink vertex will be added for than once
      if (!adj.get(edge[1]).contains(edge[0])) {
        indegrees[edge[0]]++;
        adj.get(edge[1]).add(edge[0]);
      }
    }
    Queue<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < numCourses; i++) {
      if (indegrees[i] == 0) q.add(i);
    }
    while (!q.isEmpty()) {
      int course = q.poll();
      for (int c : adj.get(course)) {
        if (--indegrees[c] == 0) q.add(c);
      }
    }
    for (int d : indegrees) {
      if (d > 0) return false;
    }
    return true;
  }

  // dfs
  public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
    Map<Integer, Set<Integer>> adj = new HashMap<>();
    for (int i = 0; i < numCourses; i++) adj.put(i, new HashSet<Integer>());
    for (int[] edge : prerequisites) {
      adj.get(edge[1]).add(edge[0]);
    }
    boolean[] visited = new boolean[numCourses];
    for (int v = 0; v < numCourses; v++) {
      if (!visited[v]) {
        if (isCyclic(v, visited, new boolean[numCourses], adj)) return false;
      }
    }
    return true;
  }

  boolean isCyclic(int v, boolean[] visited, boolean[] visiting, Map<Integer, Set<Integer>> adj) {
    visiting[v] = true;
    for (int course : adj.get(v)) {
      if (visiting[course]) return true;
      if (!visited[course]) {
        if (isCyclic(course, visited, visiting, adj)) return true;
      }
    }
    visiting[v] = false;
    visited[v] = true;
    return false;
  }
}
