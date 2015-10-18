/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
* */

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ret = new ArrayList<String>();
        int prev = lower - 1; // to handle lower at first iteration
        for (int hi = 0; hi <= nums.length; hi++) {
            if (hi == nums.length) {
                if (prev != upper) {
                   if (prev + 1 == upper) ret.add("" + upper);
                   else ret.add((prev + 1) + "->" + upper);
                }
                continue;
            }
            if (nums[hi] == prev + 1) {
                prev = nums[hi];
            } else {
                if (nums[hi] - 2 == prev) ret.add("" + (prev + 1));
                else ret.add((prev + 1) + "->" + (nums[hi] -1));
                prev = nums[hi];
            }
        }
        return ret;
    }
}
