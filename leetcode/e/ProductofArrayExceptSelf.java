/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
* */

public class Solution {
    // prefix product array and suffix product array
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        prefix[0] = 1; 
        suffix[nums.length - 1] = 1; // trick for easy coding
        for (int i = 1; i < nums.length; i++) {
             prefix[i] = prefix[i-1] * nums[i-1];
        }
        for (int i = nums.length -2; i >=0; i--) {
            suffix[i] = suffix[i+1] * nums[i+1];
        }
        int[] rst = new int[nums.length];
        for (int i =0; i < nums.length; i++) {
            rst[i] = prefix[i] * suffix[i];
        }
        return rst;
    }
}
