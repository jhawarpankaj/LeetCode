/*
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


// Naive recurive solution without DP ...
// Problem here is we will be calculating same solution again and again.
// Can be improvised with the use of Dynamic Programming.

class Solution {
    public int rob(int[] nums) {
        return helper(nums, nums.length - 1);
    }
    
    int helper(int[] nums, int i) {
        if(i < 0) return 0;
        return Math.max(nums[i] + helper(nums, i - 2), helper(nums, i - 1));
    }
}

// Recursion with DP ...
// Logic is same as above, just instead of recursing on all values, we store the intermittent results.

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

// Iteration with DP ...

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        dp[0] = 0; dp[1] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i + 1] = Math.max(dp[i], dp[i - 1] + nums[i]);
        }
        return dp[nums.length];
    }
}

// Iteration DP without extra space.
// From the above solution, we can see, we need only the last 2 values
// So, instead of storing the entire information we can just store the last 2 values with 2 variables.

class Solution {
    public int rob(int[] nums) {
        int prev = 0, prevPrev = 0, current = 0;
        for(int i = 0; i < nums.length; i++) {
            current = Math.max(nums[i] + prevPrev, prev);
            prevPrev = prev;
            prev = current;
        }
        return current;
    }
}


