/**
 Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 **/

import java.lang.Object;
import java.util.Collections;
import java.util.Comparator;

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
  public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, new Comparator<Interval>() {
      public int compare(Interval i1, Interval i2) {
        if (i1.start == i2.start) return i1.end - i2.end;
        else return i1.start - i2.start;
      }
    });
    int len =intervals.size();
    for (int i = 1; i < len; ) {
      Interval pre = intervals.get(i - 1);
      Interval cur = intervals.get(i);
      if (cur.start <= pre.end) {
        pre.end = Math.max(pre.end, cur.end);
        intervals.remove(i);
        len--;
      } else i++;
    }
    return intervals;
  }
}