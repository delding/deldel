// A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
//
// Each LED represents a zero or one, with the least significant bit on the right.

// For example, the above binary watch reads "3:25".
//
// Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
//
// Example:
//
// Input: n = 1
// Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
// Note:
// The order of output does not matter.
// The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
// The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".


class Solution {
public:
    vector<string> readBinaryWatch1(int num) {
        vector<string> res;
        for (int hour{0}; hour < 12; ++hour) {
            for (int min{0}; min < 60; ++min) {
                if (bitset<10>{(hour << 6) + min}.count() == num) {
                    res.push_back(to_string(hour) + (min > 9 ? ":" : ":0") + to_string(min));
                }
            }
        }
        return res;
    }

    vector<string> readBinaryWatch(int num) {
        vector<string> res;
        bitset<10> time;
        dfs(res, time, num, 0);
        return res;
    }

    void dfs(vector<string>& res, bitset<10>& time, int num, int idx) {
        if (time.count() == num) {
            auto val = time.to_ulong();
            auto hour = val >> 6;
            auto min = val & 0b111111;
            if (0 <= hour && hour < 12 && 0 <= min && min < 60) {
                res.push_back(to_string(hour) + (min > 9 ? ":" : ":0") + to_string(min));
            }
        } else {
            for (auto i = idx; i < 10; ++i) {
                time.set(i);
                dfs(res, time, num, i + 1);
                time.reset(i);
            }
        }
    }
};
