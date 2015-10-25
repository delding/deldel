/**
 Given an array of integers, find two numbers such that they add up to a specific target number.

 The function twoSum should return indices of the two numbers such that they add up to the target,
 where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

 You may assume that each input would have exactly one solution.

 Input: numbers={2, 7, 11, 15}, target=9
 Output: index1=1, index2=2
 **/

public class Solution {
  public int[] twoSum(int[] nums, int target) {
    int[] ret = new int[2];
    Map<Integer, List<Integer>> vals = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      List<Integer> pos = vals.get(nums[i]);
      if (pos == null) {
        pos = new ArrayList<>();
        vals.put(nums[i], pos);
      }
      pos.add(i);
    }
    for (int i = 0; i < nums.length; i++) {
      int remain = target - nums[i];
      if (vals.containsKey(remain)) {
        List<Integer> pos = vals.get(remain);
        if (remain != nums[i]) {
          ret[0] = Math.min(pos.get(0), i) + 1;
          ret[1] = Math.max(pos.get(0), i) + 1;
          return ret;
        } else {
          if (pos.size() > 1) {
            ret[0] = Math.min(pos.get(0), pos.get(1)) + 1;
            ret[1] = Math.max(pos.get(0), pos.get(1)) + 1;
            return ret;
          }
        }
      }
    }
    return ret;
  }
}

// one pass is enough
public class Solution {
  public int[] twoSum(int[] numbers, int target) {
    int[] res = new int[2];
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < numbers.length; i++) {
      if (!map.containsKey(target - numbers[i])) map.put(numbers[i], i);
      else {
        res[0] = map.get(target - numbers[i]) + 1;
        res[1] = i + 1;
        return res;
      }
    }
    return null;
  }
}