/**
 Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

 Example:
 Given a / b = 2.0, b / c = 3.0.
 queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 return [6.0, 0.5, -1.0, 1.0, -1.0 ].

 The input is: vector<pair<string, string>> euqations, vector<double>& values, vector<pair<string, string>> query . where equations.size() == values.size(),the values are positive. this represents the equations.return vector<double>. .
 The example above: equations = [ ["a", "b"], ["b", "c"] ]. values = [2.0, 3.0]. queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].

 The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 **/

public class Solution {
	class Node {
		String var;
		double num;
		Node(String v, double n) {
			var = v;
			num = n;
		}
	}

	public double[] calcEquation(String[][] equations, double[] values, String[][] query) {
		Map<String, List<Node>> graph = new HashMap<>();
		for (int i = 0; i < equations.length; i++) {
			String[] edge = equations[i];
			double val = values[i];
			if (!graph.containsKey(edge[0])) graph.put(edge[0], new ArrayList<Node>());
			graph.get(edge[0]).add(new Node(edge[1], val));
			if (!graph.containsKey(edge[1])) graph.put(edge[1], new ArrayList<Node>());
			graph.get(edge[1]).add(new Node(edge[0], 1.0 / val));
		}
		double[] res = new double[query.length];
		for (int i = 0; i < query.length; i++) {
			res[i] = dfs(graph, new HashSet<String>(), query[i][0], query[i][1], 1.0);
		}
		return res;
	}

	double dfs(Map<String, List<Node>> graph, Set<String> visited, String cur, String end, double num) { // num is the edge value from cur node to its parent
		if (!graph.containsKey(cur) || !graph.containsKey(end)) return -1.0;
		visited.add(cur);
		if (cur.equals(end)) return num;
		for (Node nei : graph.get(cur)) {
			if (!visited.contains(nei.var)) {
				double val = dfs(graph, visited, nei.var, end, nei.num);
				if (val != -1.0) return val * num;
			}
		}
		return -1.0;
	}
}