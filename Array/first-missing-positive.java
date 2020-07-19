/*
https://leetcode.com/problems/first-missing-positive/

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) nums[i] = n + 1;
        }
        for (int i = 0; i < n; i++) {
            if (Math.abs(nums[i]) == n + 1) continue;
            // this is the index whose value we want to convert to negative 
            // which will later act as a proof that the array must have has got an element with value = i;
            // For example, the array changes as: [2, 1, 4] => [2, -1, 4] => [-2, -1, 4] => [-2, -1, 4]
            int ind = Math.abs(nums[i]) - 1; 
            nums[ind] = nums[ind] > 0 ? -nums[ind] : nums[ind];
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return n + 1;
    }
}
