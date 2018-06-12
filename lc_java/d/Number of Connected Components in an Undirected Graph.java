/**
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

 Example 1:
 0          3
 |          |
 1 --- 2    4
 Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

 Example 2:
 0           4
 |           |
 1 --- 2 --- 3
 Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

 Note:
 You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 **/

public class Solution {
	public int countComponents(int n, int[][] edges) {
		int[] parent = new int[n];
		for (int i = 0; i < n ; i++) parent[i] = i;
		int count = n;
		for (int [] e : edges) {
			if (!union(e[0], e[1], parent)) count--;
		}
		return count;
	}

	int find(int v, int[] parent) {
		while (parent[v] != v) {
			int p = parent[v];
			parent[v] = parent[p]; // path compression
			v = p;
		}
		return v;
	}

	boolean union(int v1, int v2, int[] parent) {
		int p1 = find(v1, parent);
		int p2 = find(v2, parent);
		if (p1 == p2) return true; // same set
		parent[p2] = p1;
		return false;
	}

	public int countComponentsDFS(int n, int[][] edges) {
		int count = 0;
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				count++;
				dfs(i, edges, visited);
			}
		}
		return count;
	}

	void dfs(int node, int[][] edges, boolean[] visited) {
		visited[node] = true;
		for (int[] edge : edges) {
			if (edge[0] == node && !visited[edge[1]]) dfs(edge[1], edges, visited);
			if (edge[1] == node && !visited[edge[0]]) dfs(edge[0], edges, visited);
		}
	}
}