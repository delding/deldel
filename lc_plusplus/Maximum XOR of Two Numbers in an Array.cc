// Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
//
// Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
//
// Could you do this in O(n) runtime?
//
// Example:
//
// Input: [3, 10, 5, 25, 2, 8]
//
// Output: 28
//
// Explanation: The maximum result is 5 ^ 25 = 28.


class Solution {
    struct Node {
        Node* ch[2];
        Node() : ch{} {};
    };
public:
    int findMaximumXOR(vector<int>& nums) {
        int maxXor = 0;
        auto root = new Node{};
        for (auto n : nums) {
            auto val = 0;
            auto cur = root;
            auto other = root;
            for (auto i = 30; i >= 0; --i) {
                auto b = (n >> i) & 1;
                if (cur->ch[b] == nullptr) cur->ch[b] = new Node{};  // must add new node before deal with other
                cur = cur->ch[b];
                if (other->ch[1 - b] != nullptr) {
                    val |= 1 << i;
                    other = other->ch[1 - b];
                } else {
                    other = other->ch[b];
                }
            }
            maxXor = max(val, maxXor);
        }
        return maxXor;
    }
};
