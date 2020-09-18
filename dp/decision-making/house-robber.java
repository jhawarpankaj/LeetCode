/*
https://leetcode.com/problems/house-robber/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, 
the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 
it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount 
of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
*/

// The main idea for such type of problems is to check what happens 
// when including the current and not including the current.


// DIFFERENT SOLUTIONS ...


// Naive recurive solution without DP.
// Here we are calculating same solution again and again. Can be improvised with the use of Dynamic Programming.

class Solution {
    public int rob(int[] nums) {
        return helper(nums, nums.length - 1);
    }
    
    int helper(int[] nums, int i) {
        if(i < 0) return 0;
        return Math.max(nums[i] + helper(nums, i - 2), helper(nums, i - 1));
    }
}

// Recursion with DP. Logic is same as above, just instead of recursing on all values, we store the intermittent results.

class Solution {
    int[] dp;
    public int rob(int[] nums) {
        dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return helper(nums, nums.length - 1);
    }
    
    int helper(int[] nums, int i) {
        if(i < 0) return 0;
        if(dp[i] != -1) return dp[i];
        dp[i] = Math.max(nums[i] + helper(nums, i - 2), helper(nums, i - 1));
        return dp[i];
    }
}

/*
DP Bottom Up.

How did you come uo with the for loop indexes i = 2?
    If you think that you want a solution for every index i and come up with your recurrence relation, you will understand that we need to access i - 2 for one of the cases when we rob the current house. So if we want to take care of that we have to start for loop index from i = 2;
    
Why did you initialize dp of size n + 1?
    We always want to solve all DP problems for every index i, and final soln will be value of index i - 1.
    We can also have manually initialized dp[0] and dp[1].
*/

class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[n];
    }
}

// Space can be easily optimized as we need only last 2 values.
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int a = 0, b = nums[0], c = nums[0];
        for (int i = 2; i <= n; i++) {
            c = Math.max(nums[i - 1] + a, b);
            a = b;
            b = c;
        }
        return c;
    }
}
