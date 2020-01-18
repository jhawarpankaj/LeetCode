/*

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
             
*/

// Idea is to break the circular chain and consider the problem as the linear house-robber problem.

class Solution {
    
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[n - 1];
        if(n == 2) return Math.max(nums[0], nums[1]); // not required actually.
        return Math.max(robs(nums, 0, n - 2), robs(nums, 1, n - 1));
    }
    
    public int robs(int[] nums, int l, int h) {
        int prev = 0, prevPrev = 0, current = 0;
        for(int i = l; i <= h; i++) {
            current = Math.max(nums[i] + prevPrev, prev);
            prevPrev = prev;
            prev = current;
        }
        return current;
    }
}
