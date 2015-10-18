/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

public class Solution {
    // O(N)
    /*
    * Traverse the given array ‘arr’ from left to right. While traversing, maintain count of non-zero elements in array. 
    * Let the count be ‘count’. For every non-zero element arr[i], put the element at ‘arr[count]’ and increment ‘count’. After complete traversal, 
    * all non-zero elements have already been shifted to front end and ‘count’ is set as index of first 0. Now all we need to do is that run a loop which
    *  makes all elements zero from ‘count’ till end of the array
    */
    public void moveZeroes(int[] nums) {
        int count =0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != 0) {
                nums[count++] = nums[i];
            }
        }
        for (int i=count; i <nums.length; i++) {
            nums[i] = 0;
        }
    }
}
