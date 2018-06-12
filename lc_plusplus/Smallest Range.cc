// You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

// We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

// Example 1:
// Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
// Output: [20,24]
// Explanation:
// List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
// List 2: [0, 9, 12, 20], 20 is in range [20,24].
// List 3: [5, 18, 22, 30], 22 is in range [20,24].
// Note:
// The given list may contain duplicates, so ascending order means >= here.
// 1 <= k <= 3500
// -105 <= value of elements <= 105.


class Solution {
public:
    vector<int> smallestRange(vector<vector<int>>& nums) {
        using vit = vector<int>::iterator;
        auto greater = [](pair<vit, vit>& p1, pair<vit, vit>& p2) {
            return *p1.first > *p2.first;
        };
        priority_queue<pair<vit, vit>, vector<pair<vit, vit>>, decltype(greater)> pq(greater);
        auto lo = INT_MAX, hi = INT_MIN;  // [lo, hi] includes all elements in pq, the pop the smallest element at a time
        for (auto& n : nums) if (!n.empty()) {
            lo = min(lo, n[0]);
            hi = max(hi, n[0]);
            pq.emplace(n.begin(), n.end());
        }
        vector<int> range{lo, hi};
        while (true) {
            auto p = pq.top(); pq.pop();
            if (++p.first == p.second) break;
            pq.push(p);
            lo = *pq.top().first;
            hi = max(hi, *p.first);
            if (hi - lo < range[1] - range[0]) {
                range[0] = lo;
                range[1] = hi;
            }
        }
        return range;
    }
};
