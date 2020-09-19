/*
 Problem 1 (2 transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 
 Problem 2 (only 1 transaction allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 
 Problem 3: (unlimited transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 
 Problem 4: (k transactions allowed)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 
 Problem 5: (Transaction with an associated fee)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/submissions/

 Problem 6: (Trasaction with cooldown)
 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
*/

=================================================================================================================================================================
=================================================================================================================================================================

/*
1. Like other DP problems think that you are at day i. On any day i, what are all the things that we can do: B1, P1, B2, P2.
2. But we don't know which one among these will take place on that particular day. So, we'll try all of them and keep track of our objective function (max profit).
3. But realise that the result will not be P1 + P2. Why? Take one example: prices = [2, 5]. If we return P1 + P2, we will be counting the same profit twice.
   Also, in another example, prices = [2, 4, 8, 10] we will get the max profit by doing only one Buy and Sell transaction (10 - 2 = 8).
4. So, the approach is that whenever we are doing the second buy we should keep in mind the profit already made using the first B1, P1 transactions.
   So, this way P1/B1 will always hold the max profitable range and P2/B2 will always account for the profit made from P1/B1 transaction and also any new range.
*/
 
// With atmost 2 transactions.
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int B1 = prices[0], B2 = prices[0];
        int P1 = 0, P2 = 0;
        for (int i = 1; i < n; i++) {
            P1 = Math.max(P1, prices[i] - B1);
            B1 = Math.min(B1, prices[i]);
            P2 = Math.max(P2, prices[i] - B2); 
            B2 = Math.min(B2, prices[i] - P1); 
            // note that if we do not reduce P1, we end up doing the same thing as B1, P1.
            // By reducing P1, we take care of the max. profit already made and then search for another new range.
        }
        return P2;
    }
}

=================================================================================================================================================================
=================================================================================================================================================================

// One Transaction.
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int profit = 0, buy = prices[0];        
        // on any day, we can either buy or sell.
        for (int i = 1; i < n; i++) {
            profit = Math.max(profit, prices[i] - buy);
            buy = Math.min(buy, prices[i]);
        }
        return profit;
    }
}

=================================================================================================================================================================
=================================================================================================================================================================

/*
Unlimited Transactions.
Unlimited transaction is same as 2 transactions above with the only change that instead of 2 we can have infinite transactions.
Like in the above solution, the second buy considered the profit earned after the first buy/sell. Here every buy should consider the profit earned previously.
*/

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int B = prices[0], P = 0;        
        // on any day, we can buy or sell. whenever buying, we should take into consideration the profit already made.
        for (int i = 1; i < n; i++) {
            P = Math.max(P, prices[i] - B);
            B = Math.min(B, prices[i] - P);
        }
        return P;
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

=================================================================================================================================================================
=================================================================================================================================================================

/*
With K transactions.
K is similar to the 2 transactions problems. Instead of 2 variables we will now need k variables (Get an array instead).
*/

class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;
        if (k > n / 2) return unlimitedTransaction(prices);
        int[] B = new int[k];
        int[] P = new int[k];
        Arrays.fill(B, prices[0]);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                P[j] = Math.max(P[j], prices[i] - B[j]);
                B[j] = Math.min(B[j], prices[i] - (j == 0 ? 0 : P[j - 1])); // we can initialize P and B with k + 1
            }
        }
        return P[k - 1];
    }
    
    int unlimitedTransaction(int[] prices) { 
        int B = prices[0], P = 0;
        for (int i = 1; i < prices.length; i++) {
            P = Math.max(P, prices[i] - B);
            B = Math.min(B, prices[i] - P);
        }
        return P;
    }
}

=================================================================================================================================================================
=================================================================================================================================================================

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

=================================================================================================================================================================
=================================================================================================================================================================

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

=================================================================================================================================================================
=================================================================================================================================================================

// It's very important to understand the problem with 2 transactions, rest all problem can be solved if we understand that properly.
