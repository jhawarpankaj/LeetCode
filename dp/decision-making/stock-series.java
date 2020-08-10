/*
 Problem 1: (only 1 transaction allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ 
 
 Problem 2: (2 transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 
 Problem 3: (unlimited transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 
 Problem 4: (k transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 
 Problem 5: (Transaction with an associated fee)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/submissions/

 Problem 6: (Trasaction with cooldown)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
*/

==============================================================================================================================================================================
==============================================================================================================================================================================

/*
SOLUTION APPROACH FOR ALL PROBLEMS:
The stock series problems are a classic example of Decision Making in Dynamic Programming. Steps to think:
 1. At any given day i, we have 3 options: BUY, SELL, NEITHER BUY NOR SELL. 
 2. But we don't know which action to take on which day.
 3. So we will try all 3 of them and store the required optimization (max profit). Just note that, NEITHER BUY NOR SELL is equivalent to not changing the BUY/SELL values.
Now, to solve all these problems we just need to understand one thing that max profit is obtained when one buys at the minimum price and sell at the maximum price.
With this we are good to solve all problems.
*/
 
// With only one transaction.
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int buy = prices[0], profit = 0; // profit represents selling on that day.
        for (int i = 1; i < n; i++) {
            buy = Math.min(buy, prices[i]); // always buy at minimum.
            profit = Math.max(profit, prices[i] - buy); // note that we are calculating profit and not updating a sell variable, as we don't want to sell before buying (think). 
        }
        return profit;
    }
}

==============================================================================================================================================================================
==============================================================================================================================================================================

/*
With 2 transactions.
It is very important understand this problem. All other problems will be solved easily if we understand this.
Looking at the question, we can see that now on any day we have 4 options to choose from: BUY/SELL for tran1 and BUY/SELL for tran2 (i.e., BUY1, SELL1, BUY2, SELL2).
If you look at the first 2 line in the for loop below it's the code for only one transaction. 
Also, looking at the last 2 line in the for loop below it is doing the same thing as the first 2 line except in the buy2 we subtract the profit earned from tran1.
That is what is important to understand. Note that this time we are looking for the 2 LARGEST INCREASING RANGES in the array. 
1. The first LARGEST INCREASING RANGE is always present at PROFIT1.
2. In the BUY2 line we are subtracting the PROFIT1, which will give us the profit obtained from 2 transactions when we calculated profit2. 
(Think of this as attaching a range of value profit1 below BUY2).

There is another way of thinking too which helps. When we are doing the BUY2 transaction, it means we already had done a BUY1/SELL1 and while doing BUY2 we would ofcourse like
to use the money earned from BUY1/SELL1 transaction.
*/
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int buy1 = Integer.MAX_VALUE, profit1 = 0;
        int buy2 = Integer.MAX_VALUE, profit2 = 0;
        for (int i = 0; i < n; i++) {
            buy1 = Math.min(buy1, prices[i]);
            profit1 = Math.max(profit1, prices[i] - buy1);
            buy2 = Math.min(buy2, prices[i] - profit1);
            profit2 = Math.max(profit2, prices[i] - buy2);
        }
        return profit2;
    }
}

==============================================================================================================================================================================
==============================================================================================================================================================================

/*
Unlimited Transactions.
Unlimited transaction is same as 2 transactions above with the only change that instead of 2 we can have infinite transactions.
Like in the above solution, the second buy considered the profit earned after the first buy/sell. Here every buy should consider the profit earned previously.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int buy = prices[0];
        int profit = 0;
        for (int i = 1; i < n; i++) {
            buy = Math.min(buy, prices[i] - profit); // allowing infinite transactions.
            profit = Math.max(profit, prices[i] - buy);
        }
        return profit;
    }
}

/*
On a side note, this problem can be solved easily otherwise too.
*/
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i - 1]) profit += prices[i] - prices[i - 1];
        }
        return profit;
    }
}

==============================================================================================================================================================================
==============================================================================================================================================================================

/*
With K transactions.
K is similar to the 2 transactions problems. Instead of 2 variables we will now need k variables (Get an array instead).
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;
        if (k >= n / 2) return unlimitedTransactions(prices); // the problem turns to the same unlimited transaction problem.
        int[] buy = new int[k + 1]; // k + 1 to fit in the first buy case, otherwise we have to do that specific step separately.
        int[] profit = new int[k + 1];
        Arrays.fill(buy, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.min(buy[j], prices[i] - profit[j - 1]);
                profit[j] = Math.max(profit[j], prices[i] - buy[j]);
            }
        }
        return profit[k];
    }
    
    int unlimitedTransactions(int[] prices) {
        int n = prices.length;
        int buy = prices[0], profit = 0;
        for (int i = 1; i < n; i++) {
            buy = Math.min(buy, prices[i] - profit);
            profit = Math.max(profit, prices[i] - buy);
        }
        return profit;
    }
}

==============================================================================================================================================================================
==============================================================================================================================================================================

/*
Unlimited transaction with a transacton fee for one transaction (note that buy and sell together makes one transaction).
*/

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;
        int buy = prices[0] + fee, profit = 0;
        for (int i = 1; i < n; i++) {
            buy = Math.min(buy, prices[i] + fee - profit);
            profit = Math.max(profit, prices[i] - buy);
        }
        return profit;
    }
}

/*
We can do the same thing buy applying the transaction fee while selling.
*/

class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        if (n == 0) return 0;
        int buy = prices[0], profit = 0;
        for (int i = 1; i < n; i++) {
            buy = Math.min(buy, prices[i] - profit);
            profit = Math.max(profit, prices[i] - buy - fee);
        }
        return profit;
    }
}

==============================================================================================================================================================================
==============================================================================================================================================================================

/*
With cooldown.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int buy = prices[0], profit = 0;
        int temp = profit;
        for (int i = 1; i < n; i++) {             
            // As subtracting profit will consider back to back transaction. Hence keep a temp for storing prev to last.
            buy = Math.min(buy, prices[i] - temp); 
            temp = profit;
            profit = Math.max(profit, prices[i] - buy);
        }
        return profit;
    }
}

/*
Solutions inspired from: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems   
*/

==============================================================================================================================================================================
==============================================================================================================================================================================
