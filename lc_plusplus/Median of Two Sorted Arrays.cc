// There are two sorted arrays nums1 and nums2 of size m and n respectively.
//
// Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
//
// Example 1:
// nums1 = [1, 3]
// nums2 = [2]
//
// The median is 2.0
// Example 2:
// nums1 = [1, 2]
// nums2 = [3, 4]
//
// The median is (2 + 3)/2 = 2.5


class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        if ((m + n) % 2 == 0) return (kth(nums1, 0, m, nums2, 0, n, (m + n) / 2 - 1) + kth(nums1, 0, m, nums2, 0, n, (m + n) / 2)) / 2;
        return kth(nums1, 0, m, nums2, 0, n, (m + n) / 2);
    }

    double kth(vector<int>& nums1, int l1, int r1, vector<int>& nums2, int l2, int r2, int k) {  // kth is 0-index
        int m = r1 - l1, n = r2 - l2;
        if (m > n) return kth(nums2, l2, r2, nums1, l1, r1, k);
        if (m == 0) return nums2[l2 + k];
        if (k == 0) return min(nums1[l1], nums2[l2]);
        auto cnt1 = min(m, (k + 1) / 2);
        auto cnt2 = k + 1 - cnt1;
        if (nums1[l1 + cnt1 - 1] == nums2[l2 + cnt2 - 1]) return nums1[l1 + cnt1 - 1];
        else if (nums1[l1 + cnt1 - 1] < nums2[l2 + cnt2 - 1]) return kth(nums1, l1 + cnt1, r1, nums2, l2, r2, k - cnt1);
        else return kth(nums1, l1, r1, nums2, l2 + cnt2, r2, k - cnt2);
    }
};
