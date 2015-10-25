/**
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

 Each element in the array represents your maximum jump length at that position.

 Your goal is to reach the last index in the minimum number of jumps.

 For example:
 Given array A = [2,3,1,1,4]

 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 **/

public class Solution {

  // TLE
  public int jump(int[] nums) {
    int[] jumps = new int[nums.length];
    Arrays.fill(jumps, -1);
    jumps[0] = 0;
    for (int i = 0; i < jumps.length; i++) {
      for (int j = 1; j <= Math.min(nums.length - 1, i + jumps[i]); j++) {
        jumps[j] = jumps[j] == -1 ? jumps[i] + 1 : Math.min(jumps[j], jumps[i] + 1);
      }
    }
    return jumps[nums.length - 1];
  }

  // greedy algo
  // Maintain two reaches, one is the previous max reach, another is the current max reach
  // When we run out of the previous max reach, it's time to add one more step
  public int jump(int[] nums) {
    int pre = 0;
    int cur = 0;
    int step = 0;
    for (int i = 0; i < nums.length; i++) {
      if (pre < i) { // bug, use < instead of ==, since == means I am already here, if don't move further don't need more steps
        step++;
        pre = cur;
      }
      cur = Math.max(cur, i + nums[i]); // bug, must let pre update first
    }
    return step;
  }
}