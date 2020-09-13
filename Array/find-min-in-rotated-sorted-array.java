/*
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
*/

class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = (l + h) >>> 1;   
            // means the array nums[l:m] is sorted. So min will be nums[l]
            if (nums[l] <= nums[m]) {
                min = Math.min(min, nums[l]);
                l = m + 1;
            } else { // means the array nums[m+1:h] is sorted. So min will be nums[m+1] and nums[m+1] will always exist.
                min = Math.min(min, nums[m + 1]);
                h = m; // as while (l <= h), we should move pointers in the case when nums[l] == nums[m], in this part its okay.
            }
        }
        return min;
    }
}
