/*
https://leetcode.com/problems/partition-equal-subset-sum/

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
 
Example 1:

Input: [1, 5, 11, 5]
Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
*/

/*
=> Bounded Kanpsack (as we can select each item only once to form sum/2).
1. Note how the recurrence dp[i][j] changes because of bounded knapsack. We no longer search in the same array after consuming num to avoid repeatition.
2. Note how in the space optimized version we have to decrement the j loop. Incrementing j loop will lead to error because we would have already updated
   the values with current element, which will lead to repeatition.
*/

// Normal DP.
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length, sum = 0;
        
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        
        sum = sum >> 1;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        
        // for 0th row and column
        dp[0][0] = true;
        for (int i = 0, j = 1; j <= sum; j++) dp[i][j] = false;
        for (int i = 1, j = 0; i <= n; i++) dp[i][j] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                int num = nums[i - 1]; // curr num.
                dp[i][j] = dp[i - 1][j] || (j >= num ? dp[i - 1][j - num] : false); // without and with respectively.
            }
            if (dp[i][sum]) return true; // an optimization, as sum already found.
        }
        
        return dp[n][sum];
    }
}

// Space Optimized DP.
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length, sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        sum = sum >> 1;
        boolean[] dp = new boolean[sum + 1];
        
        dp[0] = true;
        for (int i = 0, j = 1; j <= sum; j++) dp[j] = false;
        
        for (int i = 1; i <= n; i++) {
            for (int j = sum; j >= 0; j--) { // note the decrementing order of this for loop.
                int num = nums[i - 1];
                dp[j] = dp[j] || (j >= num ? dp[j - num] : false); // without and with respectively.
            }
            if (dp[sum]) break;
        }
        
        return dp[sum];
    }
}
