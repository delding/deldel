/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 **/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
  public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
    for (int i = 0; i < intervals.size(); i++) {
      if (newInterval.start <= intervals.get(i).start) {
        intervals.add(i, newInterval);
        break;
      }
    }
    if (intervals.isEmpty() || newInterval.start > intervals.get(intervals.size() - 1).start) intervals.add(newInterval);
    List<Interval> res = new ArrayList<>();
    for (Interval inte : intervals) {
      if (res.isEmpty()) res.add(inte);
      else {
        Interval l = res.get(res.size() - 1);
        if (overlap(l, inte)) {
          l.end = Math.max(l.end, inte.end);
        } else {
          res.add(inte);
        }
      }
    }
    return res;
  }

  boolean overlap(Interval int1, Interval int2) {
    return int1.start <= int2.end && int2.start <= int1.end;
  }

  // in-place
  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    for (int i = 0; i < intervals.size(); ) {
      if (overlap(intervals.get(i), newInterval)) {
        newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
        newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
        intervals.remove(i);
      } else if (intervals.get(i).start > newInterval.start) {
        intervals.add(i, newInterval);
        newInterval = null; // indicate insertion is done
        break;
      } else {
        i++;
      }
    }
    if (newInterval != null) intervals.add(newInterval);
    return intervals;
  }
}