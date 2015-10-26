/**
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * <p>
 * For example, given two 1d vectors:
 * <p>
 * v1 = [1, 2]
 * v2 = [3, 4, 5, 6]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
 * <p>
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
 * <p>
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * It should return [1,4,8,2,5,9,3,6,7].
 */

public class ZigzagIterator {

  private int idx = -1;
  private int k = 2;
  private ArrayList<Iterator<Integer>> iters = new ArrayList();

  public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
    iters.add(v1.iterator());
    iters.add(v2.iterator());
  }

  public int next() {
    return iters.get(idx).next();
  }

  public boolean hasNext() {
    int cur = (idx + 1) % k;
    for (int i = 0; i < k; i++) {
      if (iters.get(cur).hasNext()) {
        idx = cur;
        return true;
      }
      cur = (cur + 1) % k;
    }
    return false;
  }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
