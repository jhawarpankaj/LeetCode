/*
 Problem 1: (only 1 transaction allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ 
 
 Problem 2: (2 transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 
 Problem 3: (unlimited transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 
 Problem 4: (k transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
*/

/*
Required: We have to calculate the maximum profit from the given stock series.

Idea: 
a. We will build the solution bottom up, i.e., starting from the first day, we will keep track of the "maximum amount" 
we can have on any given "ith" day (and on any ith day we can do 3 things: buy, sell, or rest (not do anything)).
b. So returning the last day's sell value will give us the maximum profit from the entire array (maximum because every day, we
were taking only the maximum).

Solution: 
a. The 3 actions on any ith day: buy, sell, or rest can lead us to have:
   1 stock at hand (buy) or 
   0 stock at end (sell) and
   rest will also let us have whatever we had the day before (i.e., either 1 stock or 0 stock at hand). 

b. So the amount of money we could have on any ith day will be based on our buy, sell or rest action. Let:
   M0 denotes the money we have on any ith after selling the stock that day (0 denotes the no of stock at hand).
   M1 denotes the money we have on any ith after buying the stock that day (1 denotes the no of stock at hand).

c. To get the value of selling, i.e., M0, we should use the value of M1 (the money at hand, the previous day, when we bought a
   stock) + prices[i] (note that selling will increase the total money at hand, so plus prices[i]).

d. To get the value of buying can be tricky and depends on the number of transactions allowed:

   Note: In all of the below explanation, the "rest" action is not discussed. It's just comparing the value with the current
   value and inherent in meaning.
   
   i) If only 1 transaction is allowed, it should be -prices[i]. (As this is the first time buying is happening)
   
   ii) If 2 transactions are allowed, this can be the second buying transaction or the first buying transaction. 
       If this is second buying transaction subtract the amount from the first buy transaction, i.e., 
       T_i21 = Math.max(T_i21, T_i11 - prices[i])
       But if this is the first buy transaction then the value will simply be T_i11 = Math.max(T_i11, -prices[i]).
       
   iii) Now, if infinite transactions are allowed, the no. of transaction constraint is not there and every buy
        will be reducing the price from the previous buy.
        
   iv) If only K buys are allowed, its similar to 2 buys. The first buy will be -prices[i] and rest of the buys will just
       reduce the price from previous buys.
       
 */
 
 // With only transaction...
 
 class Solution {
    public int maxProfit(int[] prices) {
    
        // M0 and M1 denotes the money at hand after making a sell and buy.
        // Sell makes our stock at hand = 0, hence denoted by M0.
        // Buy makes our stock at hand = 1, hence denoted by M1.
        
        // The initial values are set so that it can support our day 1 (prices[0]) buy and sell transactions, i.e., 
        // Buy on day 0 should lead to -prices[i] and sell on day 1 should not be allowed so that should be 0. M0 = 0;
        
        int M0 = 0, M1 = Integer.MIN_VALUE;
        
        for(int i = 0; i < prices.length; i++) {
        
            // maximum of not doing anything(rest) or selling.
            M0 = Math.max(M0, M1 + prices[i]);
            
            // maximum of not doing anything(rest) or buying.
            M1 = Math.max(M1, -prices[i]);
        }
        
        // Returning the sell will always be maximum and greater than M1
        return M0;
    }
}

// with 2 transactions...

class Solution {
    public int maxProfit(int[] prices) {
        // Notation: 
        // T_i20 denotes second transaction sell.
        // T_i21 denotes second transaction buy.        
        // T_i10 denotes first transaction sell.
        // T_i11 denotes first transaction buy.
        
        int T_i20 = 0, T_i10 = 0;
        int T_i21 = Integer.MIN_VALUE, T_i11 = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            T_i20 = Math.max(T_i20, T_i21 + prices[i]);
            T_i21 = Math.max(T_i21, T_i10 - prices[i]);            
            T_i10 = Math.max(T_i10, T_i11 + prices[i]);
            // As this is first transaction buy, means there are no more buys before it. Hence, 0 - prices[i];
            T_i11 = Math.max(T_i11, - prices[i]); 
        }
        return T_i20;
    }
}

// with K transactions...

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if(k > n / 2) {
            int M0 = 0, M1 = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++) {
                M0 = Math.max(M0, M1 + prices[i]);
                M1 = Math.max(M1, M0 - prices[i]);
            }
            return M0;
        }
        int[] K1 = new int[k + 1];
        Arrays.fill(K1, Integer.MIN_VALUE);
        int[] K0 = new int[k + 1];
        for(int i = 0; i < prices.length; i++) {
            for(int j = k; j > 0; j--) {
                K0[j] = Math.max(K0[j], K1[j] + prices[i]);
                
                // All buy decreases from previous buy's value. The first one automatically becomes -prices[i].
                K1[j] = Math.max(K1[j], K0[j - 1] - prices[i]);
            }
        }
        return K0[k];
    }
}

// with infinte transactions...
class Solution {
    public int maxProfit(int[] prices) {
        int M0 = 0, M1 = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            int temp = M0; // storing the last day sell amount.
            M0 = Math.max(M0, M1 + prices[i]);
            M1 = Math.max(M1, temp - prices[i]); // though temp is not required but for understanding its good.
                                                 // putting M0 value will produce the same max value.
        }
        return M0;
    }
}

/*
Problem 5: Transaction with an associated fee...
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/submissions/
*/

// The idea is same as above, only that fee can pe paid eiter during buy or sell (doesn't matter).

// paying fees during buy...

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int M0 = 0, M1 = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            int temp = M0;
            M0 = Math.max(M0, M1 + prices[i]);
            M1 = Math.max(M1, temp - prices[i] - fee);
        }
        return M0;
    }
}

// paying fee during sell...

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int M0 = 0, M1 = Integer.MIN_VALUE + fee; // to avoid overflow during M0 first calculation, if fee > prices[0].
        for(int i = 0; i < prices.length; i++) {
            int temp = M0;
            M0 = Math.max(M0, M1 + prices[i] - fee);
            M1 = Math.max(M1, temp - prices[i]);
        }
        return M0;
    }
}

/*
Problem 6: with cooldown...
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
*/

// Store the M0 value of the previous and previous' previous day.

class Solution {
    public int maxProfit(int[] prices) {
        int M0 = 0, M0prev = 0, M1 = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            int temp = M0;
            M0 = Math.max(M0, M1 + prices[i]);
            M1 = Math.max(M1, M0prev - prices[i]);
            M0prev = temp;
        }
        return M0;
    }
}

/*
Note: Some points to remember...
1. Sell event will happen before buy as sell value depends on previous days' buy.
2. Buy value depends on previous day's sell. So store that value in temp before modifying. But notice the case of 
   k = 1 vs infinte. In k = 1, buy does not depend on previous day sell as there will not be any previous day.
3. More details: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems   
*/   
   
   
