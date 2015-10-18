/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.
* */

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,k,0,nums.length-1);
    }
    
    int quickSelect(int[] nums, int k, int l, int r) {
        int pivotIdx = partition(nums, l, r);
        // kth smallest
        //if (pivotIdx - l + 1==k) return nums[pivotIdx];
        //else if (pivotIdx -l+1> k) return quickSelect(nums, k, l, pivotIdx-1);
        //else return quickSelect(nums, k-(pivotIdx-l+1), pivotIdx+1, r);
        // kth largest
        if(r-pivotIdx+1==k) return nums[pivotIdx];
        else if(r-pivotIdx+1>k) return quickSelect(nums, k, pivotIdx+1, r);
        else return quickSelect(nums, k-(r-pivotIdx+1), l, pivotIdx-1);
    }
    
    int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int l=left;
        int i=left;
        int r=right;
        while(i<=r) {
            if (nums[i] < pivot) swap(nums, i++, l++);
            else if (nums[i] > pivot) swap(nums, i, r--);
            else i++;
        }
        return l;
    }
    
    void swap(int[] nums, int i, int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
}
