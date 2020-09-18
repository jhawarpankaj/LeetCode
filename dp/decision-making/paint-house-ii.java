/*
https://leetcode.com/problems/paint-house-ii/

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; 
costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:
Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.              
*/

/* 
Idea is the next house will always add to the min of the previous row, if they are not the same index else the second min.That's why we just keep track of the min, 
second_min and index of minimum.
*/

class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int[] min = getMin(costs[0]); // TODO
        for (int i = 1; i < n; i++) {
            int[] newMin = {Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int j = 0; j < costs[i].length; j++) {
                if (min[0] == costs[i - 1][j]) costs[i][j] += min[1];
                else costs[i][j] += min[0];
                update(newMin, costs[i][j]); // TODO
            }
            min = newMin;
        }
        return min[0];        
    }
    
    int[] getMin(int[] cost) {
        int[] ans = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int x : cost) update(ans, x);
        return ans;
    }
    
    void update(int[] min, int val) {
        if (val <= min[0]) {
            min[1] = min[0];
            min[0] = val;
        } else if (val < min[1]) {
            min[1] = val;
        }
    }    
}
