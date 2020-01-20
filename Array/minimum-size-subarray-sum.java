/*

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray 
of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 

*/

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int l = 0, h = 0, sum = 0, min = Integer.MAX_VALUE;
        while(h < n) {
            sum += nums[h];
            if(sum >= s) {
                while(sum - nums[l] >= s) {
                    sum = sum - nums[l];
                    l++;                    
                }
                min = Math.min(min, h - l + 1);
            }
            h++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
