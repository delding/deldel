/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 * For example:
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1]
 * is the same as [1, 0] and thus will not appear together in edges.
 */

// need to be connected and has no cycle
public class Solution {
  // union-find O(Elog(V))

  // dfs: O(V+E)
  public boolean validTree(int n, int[][] edges) {
    if (edges.length == 0) return n == 1; // if no edge then need to only have one veterx
    if (edges.length != n - 1)
      return false; // optimaztion: |E| = |V| - 1 must hold for undirecited graph to be a tree
    boolean[] visited = new boolean[n];
    ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
    for (int i = 0; i < n; i++) adj.add(new ArrayList<Integer>());
    for (int i = 0; i < edges.length; i++) {
      adj.get(edges[i][0]).add(edges[i][1]);
      adj.get(edges[i][1]).add(edges[i][0]);
    }
    if (hasCycle(0, -1, adj, visited)) return false; // has cycle
    for (boolean b : visited) if (!b) return false; // not all connected
    return true;
  }

  private boolean hasCycle(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {
    visited[node] = true;
    for (int child : adj.get(node)) {
      if (child != parent) { // undirected graph child-parent, parent-child are two-way paths, so don't goto parent after just go from parent
        if (visited[child]) return true;
        else {
          if (hasCycle(child, node, adj, visited)) return true;
        }
      }
    }
    return false;
  }
}

