/**
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 **/

// todo: makes nums[] itself act as a hashmap, nums[i] -> i + 1, then find the first that doesn't map to i + 1
public class Solution {
  public int firstMissingPositive(int[] nums) {

  }
}


public class Solution {
  public int firstMissingPositive(List<Integer> nums) {
    int N = nums.size();
    if (nums.size() == 0) return 1;
    for (int i = 0; i < nums.size(); i++) {
      while (nums.get(i) != i + 1 && nums.get(i) > 0 && nums.get(i) <= N && nums.get(nums.get(i) - 1) != nums.get(i)) { // keep swapping, until the value in A[i] is legal, eighter it's i+1 or it's out of range [1, N]
        swap(nums, i, nums.get(i) - 1);
      }
    }
    for (int i = 0; i < N; i++) {
      if (nums.get(i) != i + 1) return i + 1;
    }
    return N + 1;
  }

  void swap(List<Integer> nums, int i, int j) {
    int tmp = nums.get(i);
    nums.set(i, nums.get(j));
    nums.set(j, tmp);
  }
}

