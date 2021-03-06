// You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
//
// Define a pair (u,v) which consists of one element from the first array and one element from the second array.
//
// Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
//
// Example 1:
// Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
//
// Return: [1,2],[1,4],[1,6]
//
// The first 3 pairs are returned from the sequence:
// [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
// Example 2:
// Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
//
// Return: [1,1],[1,1]
//
// The first 2 pairs are returned from the sequence:
// [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
// Example 3:
// Given nums1 = [1,2], nums2 = [3],  k = 3
//
// Return: [1,3],[2,3]
//
// All possible pairs are returned from the sequence:
// [1,3],[2,3]


class Solution {
public:
    vector<pair<int, int>> kSmallestPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        vector<pair<int, int>> ret;
        if (nums1.empty() || nums2.empty()) return ret;
        auto greater = [&nums1, &nums2](pair<int, int>& p1, pair<int, int>& p2) {
            return nums1[p1.first] + nums2[p1.second] > nums1[p2.first] + nums2[p2.second];
        };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(greater)> pq{greater};
        for (int i = 0; i < min(static_cast<int>(nums1.size()), k); ++i) {
            pq.emplace(i, 0);
        }
        while (k-- > 0 && !pq.empty()) {
            auto p = pq.top();
            pq.pop();
            ret.emplace_back(nums1[p.first], nums2[p.second]);
            if (++p.second < nums2.size()) pq.emplace(p.first, p.second);
        }
        return ret;
    }
};
