// Given an array arr that is a permutation of [0, 1, ..., arr.length - 1], we
// split the array into some number of "chunks" (partitions), and individually
// sort each chunk.  After concatenating them, the result equals the sorted array.
//
// What is the most number of chunks we could have made?
//
// Example 1:
//
// Input: arr = [4,3,2,1,0]
// Output: 1
// Explanation:
// Splitting into two or more chunks will not return the required result.
// For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
// Example 2:
//
// Input: arr = [1,0,2,3,4]
// Output: 4
// Explanation:
// We can split into two chunks, such as [1, 0], [2, 3, 4].
// However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
// Note:
//
// arr will have length in range [1, 10].
// arr[i] will be a permutation of [0, 1, ..., arr.length - 1].


class Solution {
public:
    int maxChunksToSorted(vector<int>& arr) {
        int chunks = 0, sum = 0;
        for (int i = 0; i < arr.size(); ++i) {
            sum += i;
            sum -= arr[i];
            if (sum == 0) chunks++;  // since i is continuous, only one possible arr[i] can have equal sum i.e. same as series i
        }
        return chunks;
    }


    int maxChunksToSorted2(vector<int>& arr) {
        int n = arr.size();
        unordered_set<int> need;
        unordered_set<int> has;
        int chunks = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (has.count(i) == 1) has.erase(i);
            else need.insert(i);
            if (need.count(arr[i]) == 1) need.erase(arr[i]);
            else has.insert(arr[i]);
            if (need.empty()) chunks++;
        }
        return chunks;
    }
};
