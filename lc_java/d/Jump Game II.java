/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * For example:
 * Given array A = [2,3,1,1,4]
 * <p>
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
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
  // When we run out of the current max reach, it's time to add one more jump
  public class Solution {
    public int jump(int[] nums) {
      int curreach = 0;
      int nextreach = 0;
      int jump = 0;
      for (int i = 0; i < nums.length; i++) {
        if (curreach >= nums.length - 1) return jump;
        nextreach = Math.max(nextreach, i + nums[i]);
        if (curreach == i) {
          jump++;
          curreach = nextreach;
        }
      }
      return -1;
    }
  }
}