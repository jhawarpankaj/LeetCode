/*
https://leetcode.com/problems/coin-change-2/

You are given coins of different denominations and a total amount of money. Write a function to compute the number 
of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

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
Below is the WRONG SOLUTION. 
The problem with this approach is it may count same solutions more than once. For example,
To find an amount = 3, from given coins, we will have duplicates [1, 2] and [2, 1].
*/

class Solution {
    public int change(int amount, int[] coins) {
        int[] V = new int[amount + 1];
        V[0] = 1;
        for(int i = 1; i < V.length; i++) {            
            for(int j = 0; j < coins.length; j++) {
                if(i - coins[j] >= 0) V[i] += V[i - coins[j]]; 
            }
        }
        return V[amount];
    }
}

/*
If we use, the idea of 0/1 Knapsack, get the count when coin 1 is used once for all the amounts, then coin 2 and then coin 5, 
we can avoid the duplicates, as in this approach for any amount i, the coins [1, 2, 5] are used only once.
*/
class Solution {
    public int change(int amount, int[] coins) {
        int[] V = new int[amount + 1];
        V[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i]; j <= amount; j++) {
                V[i] += V[j - coins[i]];
            }
        }
        return V[amount];
    }
}
