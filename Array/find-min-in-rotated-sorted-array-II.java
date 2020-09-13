/*

https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?

*/

class Solution {
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = (l + h) >>> 1;
            if (nums[l] < nums[m]) {
                min = Math.min(min, nums[l]);
                l = m + 1;
            } else if (nums[l] > nums[m]) {
                min = Math.min(min, nums[m + 1]);
                h = m;
            } else {
                // this is the case when nums[l] == nums[m], pointer has to move in this case, otherwise infinite loop.
                // come up with this case thinking there is only one element in the array.
                min = Math.min(min, nums[l]);
                l = l + 1;
            }
        }
        return min;
    }
}
