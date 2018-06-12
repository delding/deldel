// There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
//
// Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
//
// Example 1:
// Input:
// [[1,1,0],
//  [1,1,0],
//  [0,0,1]]
// Output: 2
// Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
// The 2nd student himself is in a friend circle. So return 2.
// Example 2:
// Input:
// [[1,1,0],
//  [1,1,1],
//  [0,1,1]]
// Output: 1
// Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
// so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
// Note:
// N is in range [1,200].
// M[i][i] = 1 for all students.
// If M[i][j] = 1, then M[j][i] = 1.



class Solution {
public:
    int findCircleNum(vector<vector<int>>& M) {
        vector<int> parents(M.size());
        int num = M.size();
        for (size_t i = 0; i < M.size(); ++i) parents[i] = i;
        for (size_t i = 0; i < M.size(); ++i) {
            for (size_t j = i + 1; j < M.size(); ++j) {
                if (M[i][j] == 1) {
                    auto pi = find(i, parents);
                    auto pj = find(j, parents);
                    if (pi != pj) {
                        num--;
                        parents[pj] = pi;
                    }
                }
            }
        }
        return num;
    }

    int find(int i, vector<int>& p) {
        while (p[i] != i) {
            auto pi = p[i];
            p[i] = p[pi];
            i = pi;
        }
        return i;
    }

    int findCircleNumDFS(vector<vector<int>>& M) {
        if (M.empty()) return 0;
        vector<bool> visited(M.size());
        int groups = 0;
        for (int i = 0; i < visited.size(); i++) {
            groups += !visited[i] ? dfs(i, M, visited), 1 : 0;
        }
        return groups;
    }

    void dfs(int i, vector<vector<int>>& M, vector<bool>& visited) {
        visited[i] = true;
        for (int j = 0; j < visited.size(); j++) {
            if (i != j && M[i][j] == 1 && !visited[j]) {
                dfs(j, M, visited);
            }
        }
    }
};
