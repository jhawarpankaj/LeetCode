/*
https://leetcode.com/problems/climbing-stairs/

You are climbing a stair case. It takes n steps to reach to the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
Example 1:
Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
*/

/* 
The idea is that we are given a source value (0) and to reach a target value(n)
constraint for reaching from source to target is: we can only take 1 or 2 steps.
Asked for: all possible paths count.
We can reach to dp[i] from dp[i - 1] by taking 1 step and dp[i - 2] by taking 2 steps.
So dp[i - 2] are the no of ways to reach dp[i - 2], same will be to reach dp[i] by taking a 2 step.
dp[i - 1] are the no of ways to reach dp[i - 1], same will be to reach dp[i] by taking 1 step.
So, dp[i] = dp[i - 1] + dp[i - 2];
Special Note: This problem however, can be solved without DP as it is just finding out the nth fibonacci number.
*/

class Solution {
    public int climbStairs(int n) {
        if(n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 1; dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}

// As we can see in the above solution that all the time we just need the last two values.
// So instead of using a full array we can use the 2 variables.

class Solution {
    public int climbStairs(int n) {
        int a = 1, b = 1;
        int c = 1;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;            
        }
        return c;
    }
}
