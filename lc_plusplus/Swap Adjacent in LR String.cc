// In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL",
// a move consists of either replacing one occurrence of "XL" with "LX", or
// replacing one occurrence of "RX" with "XR". Given the starting string start
// and the ending string end, return True if and only if there exists a sequence
// of moves to transform one string to the other.
//
// Example:
//
// Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
// Output: True
// Explanation:
// We can transform start to end following these steps:
// RXXLRXRXL ->
// XRXLRXRXL ->
// XRLXRXRXL ->
// XRLXXRRXL ->
// XRLXXRRLX
// Note:
//
// 1 <= len(start) = len(end) <= 10000.
// Both start and end will only consist of characters in {'L', 'R', 'X'}.


class Solution {
public:
    bool canTransform(string start, string end) {
        for (size_t i1 = 0, i2 = 0;;) {
            while (i1 < start.size() && start[i1] == 'X') ++i1;
            while (i2 < end.size() && end[i2] == 'X') ++i2;
            if (i1 < start.size() && i2 < end.size()) {
                if (start[i1] != end[i2]) return false;
                if (start[i1] == 'L' && i1 < i2) return false;
                if (start[i1] == 'R' && i1 > i2) return false;
                i1++;
                i2++;
            } else if (i1 == start.size() && i2 == end.size()) break;
            else return false;
        }
        return true;
    }


    bool canTransform2(string start, string end) {
        if (start.size() != end.size()) return false;
        if (start == end) return true;
        for (auto& nei : getNeighbors(start)) {
            if (canTransform(nei, end)) return true;
        }
        return false;
    }

    unordered_set<string> getNeighbors(const string& curr) {
        unordered_set<string> neighbors;
        for (size_t i = 0; i + 1 < curr.size(); ++i) {
            if (curr[i] == 'X' && curr[i + 1] == 'L' || curr[i] == 'R' && curr[i + 1] == 'X') {
                string nei = curr;
                swap(nei[i], nei[i + 1]);
                neighbors.insert(nei);
            }
        }
        return neighbors;
    }
};
