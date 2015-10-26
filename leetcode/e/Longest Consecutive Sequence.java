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
  public int longestConsecutive(int[] nums) {
    Set<Integer> set = new HashSet<Integer>();
    for (int num : nums) set.add(num);
    int longest = 1;
    for (int num : nums) {
      if (set.contains(num)) {
        int len = 1;
        set.remove(num);
        int copy = num;
        while (set.contains(++num)) {
          set.remove(num);
          len++;
        }
        while (set.contains(--copy)) {
          set.remove(copy);
          len++;
        }
        longest = Math.max(longest, len);
      }
    }
    return longest;
  }
}
