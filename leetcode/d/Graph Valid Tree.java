/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1]
 * is the same as [1, 0] and thus will not appear together in edges.
 */

public class Solution {
  // connected and acyclic
  public boolean validTree(int n, int[][] edges) {
    if (edges.length != n - 1) return false; // optimize: |E| = |V| - 1 must hold for undirecited graph to be a tree
    Map<Integer, Set<Integer>> adj = new HashMap<>();
    for (int i = 0; i < n; i++) adj.put(i, new HashSet<>());
    for (int[] e : edges) {
      adj.get(e[0]).add(e[1]);
      adj.get(e[1]).add(e[0]);
    }
    int[] visit = new int[n]; // unvisited 0, visiting 1, visited 2
    if (cyclic(adj, visit, 0, -1)) return false;
    for (int v : visit) if (v != 2) return false;
    return true;
  }

  boolean cyclic(Map<Integer, Set<Integer>> adj, int[] visit, int vert, int parent) {
    visit[vert] = 1;
    for (int nei : adj.get(vert)) {
      if (nei == parent) continue; // because undirected graph
      if (visit[nei] == 1) return true;
      else if (visit[nei] == 0) {
        if (cyclic(adj, visit, nei, vert)) return true;
      }
    }
    visit[vert] = 2;
    return false;
  }

  // union-find O(Elog(V)), for each distinct edge do union, cyclic if two vertices already in same set, all vertices must in one set at alst
}

