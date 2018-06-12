// There are a total of n courses you have to take, labeled from 0 to n - 1.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
// There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
// For example:
//
// 2, [[1,0]]
// There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
//
// 4, [[1,0],[2,0],[3,1],[3,2]]
// There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
//
// Note:
//
// The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
// You may assume that there are no duplicate edges in the input prerequisites.


class Solution {
public:
    vector<int> findOrder(int numCourses, vector<pair<int, int>>& prerequisites) {
        unordered_map<int, unordered_set<int>> g;
        for (int i = 0; i < numCourses; ++i) g[i];
        vector<int> indegrees(numCourses);
        for (auto& p : prerequisites) {
            if (g[p.second].count(p.first) == 0) {
                g[p.second].insert(p.first);
                indegrees[p.first]++;
            }
        }
        vector<int> order;
        queue<int> q;
        for (int i = 0; i < numCourses; ++i) {
            if (indegrees[i] == 0) q.push(i);
        }
        while (!q.empty()) {
            auto c = q.front();
            q.pop();
            order.push_back(c);
            for (auto cc : g[c]) {
                if (--indegrees[cc] == 0) q.push(cc);
            }
        }
        // for (auto d : indegrees) if (d > 0) return vector<int>{};
        return order.size() == numCourses ? order : vector<int>{};
    }

    vector<int> findOrderDFS(int numCourses, vector<pair<int, int>>& prerequisites) {
        unordered_map<int, unordered_set<int>> g;
        for (int i = 0; i < numCourses; ++i) g[i];
        for (auto& p : prerequisites) g[p.second].insert(p.first);
        vector<int> order;
        vector<bool> visited(numCourses);
        vector<bool> visiting(numCourses);
        for (int i = 0; i < numCourses; ++i) {
            if (!visited[i]) {
                if (!dfs(i, g, visited, order, visiting)) return vector<int>{};
            }
        }
        reverse(order.begin(), order.end());
        return order;
    }

    bool dfs(int curr, unordered_map<int, unordered_set<int>>& g, vector<bool>& visited, vector<int>& order, vector<bool> visiting) {
        if (visiting[curr]) return false;
        visiting[curr] = true;
        for (auto c : g[curr]) {
            if (!visited[c]) {
                if (!dfs(c, g, visited, order, visiting)) return false;
            }
        }
        order.push_back(curr);
        visiting[curr] = false;
        visited[curr] = true;
        return true;
    }
};
