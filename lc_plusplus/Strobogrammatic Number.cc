// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
// Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//
// For example, the numbers "69", "88", and "818" are all strobogrammatic.


class Solution {
public:
    bool isStrobogrammatic(string num) {
      int i = 0, j = num.size() - 1;
      for (; i < j; ++i, --j) {
        if (num[i] == '6' && num[j] == '9' || num[i] == '9' && num[j] == '6' || num[i] == '1' && num[j] == '1' || num[i] == '0' && num[j] == '0' || num[i] == '8' && num[j] == '8') continue;
        else return false;
      }
      if (i == j) return (num[i] == '1' || num[i] == '0' || num[i] == '8');
      return true;
    }
};
