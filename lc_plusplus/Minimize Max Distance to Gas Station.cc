// On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
//
// Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
//
// Return the smallest possible value of D.
//
// Example:
//
// Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
// Output: 0.500000
// Note:
//
// stations.length will be an integer in range [10, 2000].
// stations[i] will be an integer in range [0, 10^8].
// K will be an integer in range [1, 10^6].
// Answers within 10^-6 of the true value will be accepted as correct.


class Solution {
public:
    double minmaxGasDist(vector<int>& stations, int K) {
        auto N = stations.size();
        double low = 0, high = stations[N - 1] - stations[0];
        while (low + 1e-6 < high) {
            auto mid = (low + high) / 2;
            int count = 0;
            for (size_t i = 0; i + 1 < N; ++i) {
                count += ceil((stations[i + 1] - stations[i]) / mid) - 1;
            }
            if (count > K) low = mid;
            else high = mid;
        }
        return high;
    }
};
