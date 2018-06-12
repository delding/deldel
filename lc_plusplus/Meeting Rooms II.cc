// Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
//
// For example,
// Given [[0, 30],[5, 10],[15, 20]],
// return 2.


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
     int minMeetingRooms(vector<Interval>& intervals) {
         sort(intervals.begin(), intervals.end(), [](Interval& i1, Interval& i2){return i1.start < i2.start;});
         auto greater = [](Interval& i1, Interval& i2){return i1.end > i2.end;};
         priority_queue<Interval, vector<Interval>, decltype(greater)> pq(greater);  // minheap
         int maxOverlap{0};
         for (auto& i : intervals) {
             while (!pq.empty() && pq.top().end <= i.start) pq.pop();  // remove intervals not overlaping with current one
             pq.push(i);
             maxOverlap = max(maxOverlap, static_cast<int>(pq.size()));  // all intervals in the pq overlap with the current one
         }
         return maxOverlap;
     }
 };
