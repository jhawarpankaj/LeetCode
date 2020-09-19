/*
https://leetcode.com/problems/coin-change-2/

You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. 
You may assume that you have infinite number of each kind of coin.

Example 1:
Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
*/

/*
=> Unbounded Kanpsack (as we have infinite supply of each coin).
Still looks the same problem Coin Change I problem. Given stair (amount), count the number of ways (allowed steps we can take are mentioned in coins).

Below is that solution but it will give wrong answer. WHY? There will be couple of problems and they are very important to understand in order to correctly solve 
other DP (Combinatorial and Optimization both) problems. Let's start:

Imagine, given coins = [1, 2, 5], and we are at amt = 4. Now iterate the coins array as per the code below.

1. When coin = 1, we will get a count because of the pair (1, 3). Note that 3 was not present in the original coin array but we will still get a value for that
   because 3 would have formed due to (1, 2) present in the coin array. But that will not be a problem in this case, as this is unbounded array. In short:
   4 => (1, 3) => (1, (1, 2)). 
   But, what if the problem mentioned that we can use only one instance of each item this would give wrong result in that case.
   For example, see the problem: https://github.com/jhawarpankaj/LeetCode/blob/master/dp/01-knapsack/equal-partition-subset-sum.java
   
2. Coming back to this problem, there is another issue with this ordering of for loop. Imagine the above same scenario, i.e., amt = 4, coins = [1, 2, 5].
   Here for amt = 4, we will get a count which will consider the solutions: 
   4 => (1, 3), (2, 2), where
   (1, 3) = (1, (1, 2)), (1, (2, 1)), (1, (1, 1, 1))
   (2, 2) = (1, 1, 1, 1), (1, 1, 2), (2, 1, 1).
   Notice that the solutions are correct but they have been repeated multiple times (all permutations), and the problem did not ask for that.
   The problem only needs the combination, i.e., (1, 1, 2), (1, 2, 1), (2, 1, 1) are counted only once.
   
If we change the order of the for loop we can avoid both these issues (Issue 2 is directly avoided and the bounded or unbounded depends on the way we implement.
But, how's that? Because by changing the ordering, each coin is considered sequentially, i.e, when 1 was considered 2 was not present at all in the problem 
and when 2 was considered (1, 2) both were present, leading to avoid different permutations.

Nut shell: Reverse the order of for loop if the problem says, either:
a) Only one instance of each item allowed (Bounded Knapsack).
b) Multiple instance of each item allowed (Unbounded Knapsack) but get only combinations and not permutations.
   (in staircase, (1, 2, 1), (1, 1, 2), (2, 1, 1) are counted as different solutions but not in this case).
*/
// Incorrect Solution. (Left here to understand the impact of the order of for loops).
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // store the count of each amount.
        dp[0] = 1;
        for (int amt = 1; amt <= amount; amt++) {            
            for (int j = 0; j < coins.length; j++) {
                int coin = coins[j];
                if (amt - coin >= 0) dp[i] = dp[i] + dp[amt - coin]; // without and with.
            }
        }
        return dp[amount];
    }
}

// Correct Solution. Standard DP.
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        
        dp[0][0] = 1; // with no coins and amount = 0, there is 1 way.
        for (int i = 0, amt = 1; amt <= amount; amt++) dp[i][amt] = 0; // with no coins and amount > 0.
        for (int i = 1, j = 0; i <= n; i++) dp[i][j] = 1; // with any number and coins and amount = 0, there is 1 way (do not select any coin).
        
        for (int i = 1; i <= n; i++) {
            for (int amt = 1; amt <= amount; amt++) {
                int coin = coins[i - 1]; // curr coin denomination.                
                dp[i][amt] = dp[i - 1][amt] + (amt - coin >= 0 ? dp[i][amt - coin] : 0); // without and with respectively. 
                                                                                         // (note the WITH part, looking in the same array as unbounded knapsack).
            }
        }
        return dp[n][amount]; // i.e., using all the given coins, count the no. of unique ways amount can be formed.
    }
}

// Correct Solution. Space Optimized DP.
class Solution {
    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int amt = 1; amt <= amount; amt++) {
                int coin = coins[i - 1];
                dp[amt] = dp[amt] + (amt - coin >= 0 ? dp[amt - coin] : 0);
            }
        }
        return dp[amount];
    }
}
