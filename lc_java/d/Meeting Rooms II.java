/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * find the minimum number of conference rooms required.
 * <p>
 * For example,
 * Given [[0, 30],[5, 10],[15, 20]],
 * return 2.
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
  public int minMeetingRooms(Interval[] intervals) {
    Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
    Queue<Interval> minHeap = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);
    int maxOverlap = 0;
    for (Interval intv : intervals) {
      while (!minHeap.isEmpty() && minHeap.peek().end <= intv.start) {
        minHeap.poll();
      }
      minHeap.offer(intv);
      maxOverlap = Math.max(maxOverlap, minHeap.size());
    }
    return maxOverlap;
  }
}

public class Solution2 {
  public int minMeetingRooms(Interval[] intervals) {
    PriorityQueue<Time> pq = new PriorityQueue<Time>();
    for (Interval i : intervals) {
      pq.add(new Time(i.start, true));
      pq.add(new Time(i.end, false));
    }
    int count = 0, required = 0;
    while (!pq.isEmpty()) {
      Time t = pq.poll();
      if (t.start) count++;
      else count--;
      required = Math.max(required, count);
    }
    return required;
  }

  private class Time implements Comparable<Time> {
    int time;
    boolean start;

    Time(int t, boolean b) {
      time = t;
      start = b;
    }

    public int compareTo(Time that) {
      if (this.time == that.time) {
        return this.start ? 1 : -1;  // if equal time, end point will be polled first
      } else {
        return this.time - that.time;
      }
    }
  }
}
