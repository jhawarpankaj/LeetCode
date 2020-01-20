/*

https://leetcode.com/problems/maximum-subarray/

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum 
and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, 
which is more subtle.

*/

class Solution {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            
            // if the previous one is a positive number, that's going to increase my current value.
            // irrespective of my current value being positive or negative. Let's add that.
            // But the global max can still be some other subarray seen before. E.g.,
            // [2, 9, -2, 1] -> [2, 11, 9, 10]
            
            if(nums[i - 1] > 0) nums[i] = nums[i - 1] + nums[i];
            max = Math.max(nums[i], max);
        }
        return max;
    }
}
