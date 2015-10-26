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

public class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (numCourses == 0) return true;
    boolean[] indegrees = new boolean[numCourses];
    for (int[] edge : prerequisites) {
      indegrees[edge[1]] = true;
    }
    boolean[] visited = new boolean[numCourses];
    boolean[] backedgeNodes = new boolean[numCourses];
    int countIndegree = 0;
    for (boolean in : indegrees) {
      if (in) countIndegree++;
    }
    if (countIndegree == numCourses) return false;
    for (int i = 0; i < numCourses; i++) {
      if (!indegrees[i]) {
        if (hasCycle(i, prerequisites, visited, backedgeNodes)) return false;
      }
    }
    int visitCount = 0;
    for (boolean v : visited) {
      if (v) visitCount++;
    }
    return visitCount == numCourses;
  }

  boolean hasCycle(int node, int[][] edges, boolean[] visited, boolean[] backedgeNodes) {
    visited[node] = true;
    backedgeNodes[node] = true;
    for (int[] edge : edges) {
      if (edge[0] == node) {
        if (!visited[edge[1]]) {
          if (hasCycle(edge[1], edges, visited, backedgeNodes))
            return true;
        } else if (backedgeNodes[edge[1]]) return true;

      }
    }
    backedgeNodes[node] = false;
    return false;
  }
}
