/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.
* */

public class Solution {
    public int majorityElement(int[] nums) {
        int maj = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                maj = num; 
                count++;
            } else if (num != maj) count--;
            else count++;
        }
        return maj;
    }
}
