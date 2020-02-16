/*
You are given coins of different denominations and a total amount of money amount. Write a function to compute the 
fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination 
of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
*/

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] V = new int[amount + 1];
        for(int i = 1; i < V.length; i++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++){
                if(i - coins[j] >= 0) min = Math.min(min, V[i - coins[j]]);
            }
            if(min == Integer.MAX_VALUE) V[i] = min;
            else V[i] = 1 + min; // to count the no of min coins required, +1 for using the current coin.
        }
        return V[amount] == Integer.MAX_VALUE ? -1 : V[amount];
    }
}

/* 

The idea is same like stair climb. We have to reach from a source -> target (0 -> amount) with constraints that we 
can use any combinations of coins[i]. Here, cost of using a coin is 1. We have to find the minimum number of such coins used.
 
1. When we have to reach from a source to target, we have to build the solution for all the amounts from 1 till target.
2. To reach any amount, we have the choice of using only the available denominations. So have to loop for the 
   available coins.
   
*/

