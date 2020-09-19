/*
https://leetcode.com/problems/target-sum/

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one 
from + and - as its new symbol.

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

/*
=> Bounded Knapsack (infact it's the subset sum problem).
The first thought that comes in mind is that let's add all the negative of each number and then it's just the subset sum problem. You are on the right thought
process but this problem has a slight catch. Given below is that implementation (identify the problem).

If you got it then you realized that we cannot have negative numbers in the array for the Knapsack problem. Then how do we solve this? See next solutions for that. 
*/

// Incorrect solution (Left here to identify and understand the issue with this implementation).
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int n = nums.length;
        int m = 2 * n;
        int[] arr = new int[m];        
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
            arr[i + 1] = -nums[i];
        }
        int[][] dp = new int[m + 1][S + 1];
        
        dp[0][0] = 1;
        for (int i = 0, j = 1; j <= S; j++) dp[i][j] = 0;
        for (int i = 1, j = 0; i <= m; i++) dp[i][j] = 1;
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= S; j++) {
                int curr = arr[i - 1];
                dp[i][j] = dp[i - 1][j] + (j >= curr ? dp[i - 1][j - curr] : 0); // what if curr < 0?
            }
        }
        return dp[m][S];
    }
}

/*
Let P be the positive subset and N be the negative subset
For example:
Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

Then let's see how this can be converted to a subset sum problem:

                  sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)
                       
So the original problem has been converted to a subset sum problem as follows:
Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
Note that the above formula has proved that target + sum(nums) must be even

That's how we got rid of the negative numbers.
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

// Side note: Normal backtracking approach

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
