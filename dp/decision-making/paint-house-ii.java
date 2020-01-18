/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house 
with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, 
costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, 
and so on... Find the minimum cost to paint all houses.


Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.              
*/

/* 
Idea is the next house will always add to the min of the previous row, if they are not the same index else the second min.
That's why we just keep track of the min, second_min and index of minimum
*/

class Solution {
    public int minCostII(int[][] costs) {
        if(costs.length == 0) return 0;
        int k = costs[0].length;
        if(k == 0) return 0;
        int min1 = 0, min2 = 0, minIndex = -1;
        for(int i = 0; i < costs.length; i++) {
            int currMin1 = Integer.MAX_VALUE, currMin2 = Integer.MAX_VALUE, currIndex = -1;
            for(int j = 0; j < k; j++) {
                int cost = costs[i][j] + (j == minIndex ? min2 : min1);
                if(cost < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = cost;
                    currIndex = j;
                }
                else if(cost < currMin2) currMin2 = cost;
            }
            min1 = currMin1;
            min2 = currMin2;
            minIndex = currIndex;
        }
        return min1;
    }
}
