/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
 */

// Boyer-Moore Majority Vote
// http://gregable.com/2013/10/majority-vote-algorithm-find-majority.html
// https://leetcode.com/discuss/43248/boyer-moore-majority-vote-algorithm-and-my-elaboration
public class Solution {
  public List<Integer> majorityElement(int[] nums) {
    int cand1 = 0;
    int cand2 = 0;
    int count1 = 0;
    int count2 = 0;
    for (int num : nums) {
      if (num == cand1) count1++;
      else if (num == cand2) count2++;
      else if (count1 == 0) {
        cand1 = num;
        count1++;
      } else if (count2 == 0) {
        cand2 = num;
        count2++;
      } else {
        count1--;
        count2--;
      }
    }
    count1 = 0;
    count2 = 0;
    for (int num : nums) {
      if (num == cand1) count1++;
      if (num == cand2) count2++;
    }
    List<Integer> rst = new ArrayList();
    int size = nums.length / 3;
    if (count1 > size) rst.add(cand1);
    if (count2 > size && cand2 != cand1)
      rst.add(cand2); // because cand1 and cand2 intialized to 0, if input only has 0, cand2 will not be reassinged
    return rst;

  }
}
