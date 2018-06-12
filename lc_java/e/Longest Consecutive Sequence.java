/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 */

public class Solution {
  public int longestConsecutive(int[] nums) { // can't apply Arrays.asList to array of primary type
    Set<Integer> set = new HashSet<>();
    for (int num : nums) set.add(num);
    int longest = 0;
    while (set.size() > longest) {
      int len = 1;
      int v = set.iterator().next();
      set.remove(v);
      int h = v + 1;
      while (set.contains(h)) {
        len++;
        set.remove(h++);
      }
      int l = v - 1;
      while (set.contains(l)) {
        len++;
        set.remove(l--);
      }
      longest = Math.max(longest, len);
    }
    return longest;
  }
  // union-find: an edge exists between n and n - 1, or n and n + 1, union all nums and return size of largest connected component
}