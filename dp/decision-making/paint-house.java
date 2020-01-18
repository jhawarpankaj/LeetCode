/*
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
The cost of painting each house with a certain color is different. You have to paint all the houses such that 
no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, 
costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 
with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.
*/             

// Its about making decision, if choosing the current poses some restrictions on other selection.
// Look at the house-robber problem.

class Solution {
    public int minCost(int[][] costs) {
        return Math.min(helper(costs, costs.length - 1, 0), Math.min(helper(costs, costs.length - 1, 1), helper(costs, costs.length - 1, 2)));
    }
    
    int helper(int[][] costs, int i, int j) {
        if(i < 0) return 0;
        if(j == 0) return costs[i][j] + Math.min(helper(costs, i - 1, j + 1), helper(costs, i - 1, j + 2));
        else if(j == 1) return costs[i][j] + Math.min(helper(costs, i - 1, j - 1), helper(costs, i - 1, j + 1));
        else return costs[i][j] + Math.min(helper(costs, i - 1, j - 2), helper(costs, i - 1, j - 1));        
    }
}   


class Solution {
    int[][] dp;
    public int minCost(int[][] costs) {
        dp = new int[costs.length][3];
        for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);
        return Math.min(helper(costs, costs.length - 1, 0), Math.min(helper(costs, costs.length - 1, 1), helper(costs, costs.length - 1, 2)));
    }
    
    int helper(int[][] costs, int i, int j) {
        if(i < 0) return 0;
        if(dp[i][j] != -1) return dp[i][j];
        if(j == 0) dp[i][j] = costs[i][j] + Math.min(helper(costs, i - 1, j + 1), helper(costs, i - 1, j + 2));
        else if(j == 1) dp[i][j] = costs[i][j] + Math.min(helper(costs, i - 1, j - 1), helper(costs, i - 1, j + 1));
        else dp[i][j] = costs[i][j] + Math.min(helper(costs, i - 1, j - 2), helper(costs, i - 1, j - 1));
        return dp[i][j];
    }
}


class Solution {
    int[][] dp;
    public int minCost(int[][] costs) {
        dp = new int[costs.length + 1][3];
        dp[0][0] = 0; dp[0][1] = 0; dp[0][2] = 0;
        for(int i = 0; i < costs.length; i++) {
            dp[i + 1][0] = costs[i][0] + Math.min(dp[i][1], dp[i][2]);
            dp[i + 1][1] = costs[i][1] + Math.min(dp[i][0], dp[i][2]);
            dp[i + 1][2] = costs[i][2] + Math.min(dp[i][0], dp[i][1]);
        }
        return Math.min(dp[costs.length][0], Math.min(dp[costs.length][1], dp[costs.length][2]));
    }
}

class Solution {
    public int minCost(int[][] costs) {
        int colorZero = 0, colorOne = 0, colorTwo = 0;
        int currentZero = 0, currentOne = 0, currentTwo = 0;
        for(int i = 0; i < costs.length; i++) {
            currentZero = costs[i][0] + Math.min(colorOne, colorTwo);
            currentOne = costs[i][1] + Math.min(colorZero, colorTwo);
            currentTwo = costs[i][2] + Math.min(colorZero, colorOne);
            colorZero = currentZero; colorOne = currentOne; colorTwo = currentTwo;
        }
        return Math.min(colorZero, Math.min(colorOne, colorTwo));
    }
}
