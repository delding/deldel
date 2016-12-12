/**
 * Implement an iterator to flatten a 2d vector.
 * <p>
 * For example,
 * Given 2d vector =
 * <p>
 * [
 * [1,2],
 * [3],
 * [4,5,6]
 * ]
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 */

public class Vector2D implements Iterator<Integer> {
  Iterator<List<Integer>> it;
  Iterator<Integer> nextIt;
  Integer next;
  public Vector2D(List<List<Integer>> vec2d) {
    it = vec2d.iterator();
    if (it.hasNext()) {
      nextIt = it.next().iterator();
      while (next == null) {
        if (nextIt.hasNext()) next = nextIt.next();
        else if (it.hasNext()) nextIt = it.next().iterator();
        else break;
      }
    }
  }

  @Override
  public Integer next() {
    assert next != null;
    int ret = next;
    next = null;
    while (next == null) {
      if (nextIt.hasNext()) next = nextIt.next();
      else if (it.hasNext()) nextIt = it.next().iterator();
      else break;
    }
    return ret;
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */