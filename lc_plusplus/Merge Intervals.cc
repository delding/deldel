// Given a collection of intervals, merge all overlapping intervals.
//
// For example,
// Given [1,3],[2,6],[8,10],[15,18],
// return [1,6],[8,10],[15,18].


/**
 * Definition for an interval.
 * struct Interval {
 *     int start;
 *     int end;
 *     Interval() : start(0), end(0) {}
 *     Interval(int s, int e) : start(s), end(e) {}
 * };
 */
class Solution {
public:
    vector<Interval> merge(vector<Interval>& intervals) {
      sort(intervals.begin(), intervals.end(), [](Interval& i1, Interval& i2){
        return i1.start < i2.start;
      });
      for (size_t i = 1; i < intervals.size();) {
        if (intervals[i - 1].end >= intervals[i].start) {
          intervals[i - 1].end = max(intervals[i - 1].end, intervals[i].end);
          intervals.erase(intervals.begin() + i);
        } else ++i;
      }
      return intervals;
    }
};


class Solution {
public:
    vector<Interval> merge(vector<Interval>& intervals) {
        sort(intervals.begin(), intervals.end(), [](Interval& i1, Interval& i2){
            return i1.start < i2.start;
        });
        vector<Interval> ret;
        for (auto& i : intervals) {
            if (ret.empty() || ret.back().end < i.start) ret.push_back(i);
            else {
                ret.back().end = max(ret.back().end, i.end);
            }
        }
        return ret;
    }
};
