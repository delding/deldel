// Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
//
// However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
//
// You need to return the least number of intervals the CPU will take to finish all the given tasks.
//
// Example 1:
// Input: tasks = ["A","A","A","B","B","B"], n = 2
// Output: 8
// Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
// Note:
// The number of tasks is in the range [1, 10000].
// The integer n is in the range [0, 100].


class Solution {
public:
    int leastInterval(vector<char>& tasks, int n) {
        unordered_map<char, int> counts;
        for (auto t : tasks) counts[t]++;
        auto less = [](pair<char, int>& p1, pair<char, int>& p2) {
            return p1.second < p2.second || (p1.second == p2.second && p1.first > p2.first);
        };
        priority_queue<pair<char, int>, vector<pair<char, int>>, decltype(less)> pq(less);
        for (auto& p : counts) pq.push(p);
        int total = 0;
        while (!pq.empty()) {
            vector<pair<char, int>> tmp;
            int cycle = 1;
            for (; cycle <= n + 1; cycle++) {  // need to run n + 1 tasks to allow same tasks have interval of n
                auto t = pq.top(); pq.pop();
                if (--t.second > 0) tmp.push_back(t);
                if (pq.empty()) break;
            }
            for (auto t : tmp) pq.push(move(t));
            total += pq.empty() ? cycle : n + 1;

        }
        return total;
    }
};
