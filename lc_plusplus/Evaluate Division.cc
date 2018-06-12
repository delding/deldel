// Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
//
// Example:
// Given a / b = 2.0, b / c = 3.0.
// queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
// return [6.0, 0.5, -1.0, 1.0, -1.0 ].
//
// The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
//
// According to the example above:
//
// equations = [ ["a", "b"], ["b", "c"] ],
// values = [2.0, 3.0],
// queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
// The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.


class Solution {
public:
    vector<double> calcEquation(vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries) {
        unordered_map<string, vector<pair<string, double>>> graph;
        for (auto i = 0; i < equations.size(); ++i) {
            auto src = equations[i].first;
            auto sink = equations[i].second;
            graph[src].emplace_back(sink, values[i]);
            graph[sink].emplace_back(src, 1 / values[i]);

        }
        vector<double> res;
        for (auto q : queries) {
            unordered_set<string> visited;
            res.push_back(dfs(q.first, q.second, graph, 1.0, visited));
        }
        return res;
    }

    double dfs(string curr, string end, unordered_map<string, vector<pair<string, double>>>& graph, double res, unordered_set<string>& visited) {
        if (graph.count(curr) == 0 || visited.count(curr) == 1) return -1;
        visited.insert(curr);
        if (curr == end) return res;
        for (auto& p : graph[curr]) {
            auto& sink = p.first;
            auto val = p.second;
            auto result = dfs(sink, end, graph, res * val, visited);
            if (result != -1) return result;
        }
        return -1;
    }
};
