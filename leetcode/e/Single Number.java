/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * */

public class Solution {
    public int singleNumber(int[] nums) {
        int single = nums[0];
        for (int i = 1; i < nums.length; i++) {
            single ^= nums[i];
        }
        return single;
    }
}
