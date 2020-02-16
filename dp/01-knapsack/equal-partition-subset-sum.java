/*
Partition Equal Subset Sum: https://leetcode.com/problems/partition-equal-subset-sum/
Knapsack Problem Geeks: https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/

Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 
such that the sum of elements in both subsets is equal.

Note:

Each of the array element will not exceed 100.
The array size will not exceed 200.
 
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].

*/
/*
In a knapsack problem we can either pick the item or not. 

On picking the current item: the sum we will have to search now, will be reduced by the current value.
On not picking the current: we mark true only if not picking the current item we still get a weight j.
Imagine the 2D table created because of this and also looking at the recurrence relation, we can see that at every step
we are just using values from the previous row. 
*/

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) sum += num;
        int n = nums.length;        
        if(sum % 2 != 0) return false;
        sum = sum / 2;
        
        // dp[i][j] means we need a sum of j using the elements nums[0] to nums[i].
        
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for(int i = 0; i < n; i++) dp[i][0] = true;
        for(int i = 1; i <= sum; i++) dp[0][i] = false;        
        dp[0][0] = true;
        
        for(int i = 0; i < n; i++) {
            for(int j = 1; j <= sum; j++) {
                if(j - nums[i] >= 0) dp[i + 1][j] = dp[i][j - nums[i]] || dp[i][j];
            }            
            if(dp[i + 1][sum] == true) return true; // we have found a subset having half the total sum.
        }
        return false;
    }
}

// Space optimization approach: Look at the inner j loop, we inverted it, because the recurrence relation is using the 
// values from the lower half of the array because of dp[j - nums[i]].

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) sum += num;
        int n = nums.length;
        
        if(sum % 2 != 0) return false;
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        for(int i = 1; i <= sum; i++) dp[i] = false;
        // for(int i = 1; i <= n; i++) dp[i][0] = true;
        dp[0] = true;
        
        for(int i = 0; i < n; i++) {
            for(int j = sum; j >= 1; j--) {
                if(j - nums[i] >= 0) dp[j] = dp[j - nums[i]] || dp[j];
            }            
            if(dp[sum] == true) return true;
        }
        return false;
    }
}
