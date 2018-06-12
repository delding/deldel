/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * <p>
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */

public class Solution {
  public List<String> summaryRanges(int[] nums) {

    List<String> rst = new ArrayList<String>();
    if (nums.length == 0) return rst;
    int last = nums[0];
    for (int i = 0; i < nums.length; i++) {
      if (i == nums.length - 1) { // ERROR: need to handle last range upon finish iterating input
        if (nums[i] == last) {
          rst.add("" + last);
        } else {
          rst.add(last + "->" + nums[i]);
        }
        break;
      }
      if (nums[i] + 1 != nums[i + 1]) {
        if (nums[i] == last) {
          rst.add(last + "");
        } else {
          rst.add(last + "->" + nums[i]);
        }
        last = nums[i + 1];
      }
    }
    return rst;
  }
}
