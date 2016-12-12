/**
 * Given a collection of intervals, merge all overlapping intervals.
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
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

public class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, (l, r) -> {
      return l.start - r.start;
    });
    if (intervals == null || intervals.size() == 0) return intervals;
    List<Interval> res = new ArrayList<>();
    res.add(intervals.get(0));
    for (int i = 1; i < intervals.size(); i++) {
      Interval l = res.get(res.size() - 1);
      Interval r = intervals.get(i);
      if (l.end >= r.start) {
        l.end = Math.max(l.end, r.end);
      } else {
        res.add(r);
      }
    }
    return res;
  }
}

// inplace
public class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    Collections.sort(intervals, new Comparator<Interval>() {
      public int compare(Interval i1, Interval i2) {
        if (i1.start == i2.start) return i1.end - i2.end;
        else return i1.start - i2.start;
      }
    });
    int len = intervals.size();
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