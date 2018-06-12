// Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
//
// Example 1:
// Input: [1,2,3,4,5], k=4, x=3
// Output: [1,2,3,4]
// Example 2:
// Input: [1,2,3,4,5], k=4, x=-1
// Output: [1,2,3,4]


class Solution {
public:
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        vector<int> res;
        auto it = lower_bound(arr.begin(), arr.end(), x);
        auto it2 = it - 1;
        while (k-- > 0) {
            if (it2 == arr.begin() - 1) ++it;
            else if (it == arr.end()) --it2;
            else if (*it - x < x - *it2) ++it;
            else --it2;
        }
        res.assign(it2 + 1, it);
        return res;
    }
};
