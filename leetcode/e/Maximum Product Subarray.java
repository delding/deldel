/**
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
* */

public class Solution {
    public int maxProduct(int[] nums) {
        int globalmax = nums[0];
        int min = nums[0]; // min product at array[0, i] containning element i
        int max = nums[0]; // max product at array[0, i] containning element i
        for (int i = 1; i < nums.length; i++) {
            int curmin = Math.min(Math.min(min * nums[i], max * nums[i]), nums[i]);
            int curmax = Math.max(Math.max(min * nums[i], max * nums[i]), nums[i]);
            min = curmin;
            max = curmax;
            globalmax = Math.max(max, globalmax);
        }
        return globalmax;
    }
}
