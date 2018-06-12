// Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.
//
// You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.
//
// Example 1:
//
// Input: "19:34"
// Output: "19:39"
// Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
// Example 2:
//
// Input: "23:59"
// Output: "22:22"
// Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.


class Solution {
public:
    string nextClosestTime(string time) {
        time.erase(2, 1);  // remove ':'
        string uniq{time}; // don't call unique on time directly as this will modify its content
        uniq.erase(unique(uniq.begin(), uniq.end()), uniq.end());
        vector<string> times;
        string t;
        collectTimes(times, uniq, t);
        sort(times.begin(), times.end());
        auto it = times.begin();
        auto upb = upper_bound(times.begin(), times.end(), time);
        if (upb != times.end()) it = upb;
        it->insert(2, ":");
        return *it;
    }

    void collectTimes(vector<string>& times, string& cand, string& t) {
        if (t.size() == 4) {
            auto val = stoi(t);
            if (val / 100 >= 0 && val / 100 < 24 && val % 100 >= 0 && val % 100 < 60) times.push_back(t);
        } else {
            for (auto c : cand) {
                // collectTimes(times, cand, t * 10 + c - '0',  k - 1); // 00:00 will become just 0
                t += c;
                collectTimes(times, cand, t);
                t.pop_back();
            }
        }
    }
};
