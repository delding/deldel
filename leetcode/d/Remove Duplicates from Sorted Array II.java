/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
 */

public class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int dup = 1;
    int head = 0;
    int curr = 1;
    while (curr < nums.length) {
      if (nums[curr] != nums[head]) {
        dup = 1;
        nums[++head] = nums[curr++];
      } else {
        if (dup == 1) {
          nums[++head] = nums[curr++];
          dup++;
        } else {
          curr++;
        }
      }
    }
    return head + 1;
  }
}
