/**
 * Given a nested list of integers, implement an iterator to flatten it.

 Each element is either an integer, or a list -- whose elements may also be integers or other lists.

 Example 1:
 Given the list [[1,1],2,[1,1]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

 Example 2:
 Given the list [1,[4,[6]]],

 By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
	Deque<Iterator<NestedInteger>> st = new ArrayDeque<>();
	Integer cursor;
	public NestedIterator(List<NestedInteger> nestedList) {
		st.push(nestedList.iterator());
		moveCursor();
	}

	private void moveCursor() {
		while (!st.isEmpty()) {
			Iterator<NestedInteger> it = st.peek();
			if (it.hasNext()) {
				NestedInteger ni = it.next();
				if (ni.isInteger()) {
					cursor = ni.getInteger();
					return;
				} else {
					st.push(ni.getList().iterator());
				}
			} else st.pop();
		}
		cursor = null;
	}

	@Override
	public Integer next() {
		// assert cursor != null;
		Integer ret = cursor;
		moveCursor();
		return ret;
	}

	@Override
	public boolean hasNext() {
		return cursor != null;
	}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */