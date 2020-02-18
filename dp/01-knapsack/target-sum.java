/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. 
For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
*/

// Normal backtracking approach

class Solution {
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        getCount(nums, 0, 0, S);
        return count;
    }
    
    void getCount(int[] nums, int i, int sum, int target) {
        if(i == nums.length)  
            if(sum == target) count++;
            else return;
        else {
            getCount(nums, i + 1, sum + nums[i], target);
            getCount(nums, i + 1, sum - nums[i], target);
        }
    }
}

/*
DP version, the problem can be converted to subset sum problem.
Why: https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
*/

class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) sum += nums[i];
        if(S > sum) return 0;
        sum += S;
        if(sum % 2 == 1) return 0;
        sum = sum / 2;
        return subset_dp(nums, sum);
    }
    
    public int subset_normal_dp(int[] nums, int sum) {
        int n = nums.length;
        int[][] dp = new int[n + 1][sum + 1];
        for(int i = 1; i <= n; i++) dp[i][0] = 1;
        for(int i = 1; i <= sum; i++) dp[0][i] = 0;        
        dp[0][0] = 1;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= sum; j++) {
                if(j - nums[i] >= 0) dp[i + 1][j] = dp[i][j - nums[i]] + dp[i][j]; 
                else dp[i + 1][j] = dp[i][j]; // if the current coin cannot be used,                                                                                   // then get the value from previous element.
            }
        }
        return dp[n][sum];
    }
    
    public int subset_space_optimized_dp(int[] nums, int sum) {
        int n = nums.length;
        int[] dp = new int[sum + 1]; 
        dp[0] = 1;
        for(int i = 0; i < n; i++) {
            for(int j = sum; j >= 0; j--) {
                if(j - nums[i] >= 0) dp[j] = dp[j - nums[i]] + dp[j];
            }            
        }
        return dp[sum];
    } 
}
