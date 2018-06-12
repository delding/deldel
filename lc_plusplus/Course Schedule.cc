// There are a total of n courses you have to take, labeled from 0 to n - 1.
//
// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
// Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
// For example:
//
// 2, [[1,0]]
// There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
//
// 2, [[1,0],[0,1]]
// There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


class Solution {
public:
    bool canFinish(int numCourses, vector<pair<int, int>>& prerequisites) {
        unordered_map<int, unordered_set<int>> g;
        for (int i = 0; i < numCourses; ++i) g[i];
        vector<int> indegrees(numCourses);
        for (auto& p : prerequisites) {
            if (g[p.second].count(p.first) == 0) {
                g[p.second].insert(p.first);
                indegrees[p.first]++;
            }
        }
        queue<int> q;
        for (int i = 0; i < numCourses; ++i) {
            if (indegrees[i] == 0) q.push(i);
        }
        while (!q.empty()) {
            auto c = q.front();
            q.pop();
            for (auto cc : g[c]) {
                if (--indegrees[cc] == 0) q.push(cc);
            }
        }
        for (auto d : indegrees) if (d > 0) return false;
        return true;
    }
};
