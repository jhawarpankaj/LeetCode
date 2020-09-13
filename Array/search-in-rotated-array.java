/*
https://leetcode.com/problems/search-in-rotated-sorted-array/

You are given an integer array nums sorted in ascending order, and an integer target.
Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
If target is found in the array return its index, otherwise, return -1.
Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 
Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is guranteed to be rotated at some pivot.
-10^4 <= target <= 10^4

*/

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = (l + h) >>> 1;
            if (nums[m] == target) return m;            
            if (nums[l] <= nums[m]) { // this condition tells us that array[l : m] is sorted.
                if (target >= nums[l] && target < nums[m]) h = m - 1;
                else l = m + 1;
            } else { // if the code reach here it means, nums[m+1:h] is sorted part.
                // here m + 1 won't throw error. If m + 1 was not there the code will go to the if part.
                if (target >= nums[m + 1] && target <= nums[h]) l = m + 1; 
                else h = m - 1;
            }
        }
        return -1;
    }
}
