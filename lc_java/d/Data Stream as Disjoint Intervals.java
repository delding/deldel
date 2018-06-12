/**
 Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.

 For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:

 [1, 1]
 [1, 1], [3, 3]
 [1, 1], [3, 3], [7, 7]
 [1, 3], [7, 7]
 [1, 3], [6, 7]
 Follow up:
 What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
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
public class SummaryRanges {
	List<Integer> starts = new ArrayList<>();
	List<Integer> ends = new ArrayList<>();

	/** Initialize your data structure here. */
	public SummaryRanges() {

	}

	public void addNum(int val) {
		if (starts.size() == 0) { // first val to add
			starts.add(val);
			ends.add(val);
			return;
		}
		int is = Collections.binarySearch(starts, val);
		int ie = Collections.binarySearch(ends, val);
		if (is >= 0 || ie >= 0) return; // val is equal to start or end of an Interval
		else { // val locates either within one Interval or between two Intervals
			is = -(is + 1);
			ie = -(ie + 1);
			if (is == ie + 1) { // the val is contained in the Interval(starts.get(is - 1), ends.get(ie))
				return;
			} else if (is == ie) { // val is between Interval(starts.get(is - 1), ends.get(ie - 1)) and Interval(starts.get(is), ends.get(ie))
				if (ie > 0 && is < starts.size() && ends.get(ie - 1) == val - 1 && starts.get(is) == val + 1) { // merge two intervals
					ends.set(ie - 1, ends.get(ie));
					starts.remove(is);
					ends.remove(ie);
				} else if (ie > 0 && ends.get(ie - 1) == val - 1) {
					ends.set(ie - 1, val);
				} else if (is < starts.size() && starts.get(is) == val + 1) {
					starts.set(is, val);
				} else { // create new Interval(val, val)
					starts.add(is, val);
					ends.add(ie, val);
				}
			} else {
				// code don't reach here,
			}

		}
	}

	public List<Interval> getIntervals() {
		List<Interval> intervals = new ArrayList<>();
		for (int i = 0; i < starts.size(); i++) {
			intervals.add(new Interval(starts.get(i), ends.get(i)));
		}
		return intervals;
	}


	// solution 2: use TreeMap start->Interval(start, end) sort by start
	TreeMap<Integer, Interval> tree;
	public SummaryRanges() {
		tree = new TreeMap<>();
	}

	public void addNum(int val) {
		if(tree.containsKey(val)) return;
		Integer l = tree.lowerKey(val);
		Integer h = tree.higherKey(val);
		if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) { // merge two intervals
			tree.get(l).end = tree.get(h).end;
			tree.remove(h);
		} else if(l != null && tree.get(l).end + 1 >= val) { // merge with left interavl
			tree.get(l).end = Math.max(tree.get(l).end, val);
		} else if(h != null && h == val + 1) {
			tree.put(val, new Interval(val, tree.get(h).end)); // merge with right interval
			tree.remove(h);
		} else {
			tree.put(val, new Interval(val, val)); // create new interval
		}
	}

	public List<Interval> getIntervals() {
		return new ArrayList<>(tree.values());
	}
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
