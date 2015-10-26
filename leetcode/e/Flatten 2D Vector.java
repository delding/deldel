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

public class Vector2D {

  private Iterator<List<Integer>> listIter;
  private Iterator<Integer> intIter;
  private Integer next;
  private boolean isNext = false;

  public Vector2D(List<List<Integer>> vec2d) {
    listIter = vec2d.iterator();
  }

  public int next() {
    if (isNext) {
      isNext = false;
    } else goNext();
    return next;
  }

  public boolean hasNext() {
    goNext();
    isNext = true;
    return next != null;
  }

  private void goNext() {
    while (intIter == null || !intIter.hasNext()) { // error 2: forget to check intIter == null, iterator() can return null
      if (listIter.hasNext())
        intIter = listIter.next().iterator(); // error 1: forget to call iterater()
      else {
        next = null;
        return; // error 3: forget to return, infinite loop
      }
    }
    next = intIter.next();
  }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
