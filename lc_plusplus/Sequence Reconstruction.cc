// Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.
//
// Example 1:
//
// Input:
// org: [1,2,3], seqs: [[1,2],[1,3]]
//
// Output:
// false
//
// Explanation:
// [1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.
// Example 2:
//
// Input:
// org: [1,2,3], seqs: [[1,2]]
//
// Output:
// false
//
// Explanation:
// The reconstructed sequence can only be [1,2].
// Example 3:
//
// Input:
// org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
//
// Output:
// true
//
// Explanation:
// The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
// Example 4:
//
// Input:
// org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
//
// Output:
// true


class Solution {
public:
    // If a topological sort has the property that all pairs of consecutive vertices in the sorted order are connected by edges, then these edges form a directed Hamiltonian path in the DAG
    // Hamiltonian path (or traceable path) is a path in an undirected or directed graph that visits each vertex exactly once
    // The hamiltonian path is also a topologically sorted path.
    // There will only be one hamiltonian path in the solution because if multiple are possible, it will contradict each other as they have different ordering requirements.
    bool sequenceReconstruction(vector<int>& org, vector<vector<int>>& seqs) {
        int N = org.size();
        unordered_map<int, unordered_set<int>> g;
        vector<int> indegrees(N + 1);
        int numEdges = 0;
        for (auto& v : seqs) {
            for (auto val : v) if (val > N || val < 1) return false;  // invalid vertex
            for (int i = 0; i < v.size(); ++i) {
                g[v[i]];  // add all vertices to graph
                if (i + 1 < v.size() && g[v[i]].count(v[i + 1]) == 0) {  // avoid count duplicate edge
                    g[v[i]].insert(v[i + 1]);
                    ++indegrees[v[i + 1]];
                    ++numEdges;
                }
            }
        }
        if (g.size() != N) return false;  // missing vertex in seqs
        queue<int> q;
        auto it = org.begin();
        for (int v = 1; v < indegrees.size(); ++v) {
            if (indegrees[v] == 0) q.push(v);
        }
        while (!q.empty()) {
            if (q.size() > 1) return false;  // one vertex with 0 indegree each time to gurantee uniqueness of Hamiltonian path
            auto v = q.front();
            q.pop();
            if (v != *it++) return false;  // org is not a valid toposort (Hamiltonian path)
            for (auto sink : g[v]) {
                --numEdges;
                if (--indegrees[sink] == 0) {
                    q.push(sink);
                }
            }
        }
        if (numEdges != 0) return false; // has cycle, no toposort
        return true;
    }
};
