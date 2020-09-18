/*
https://leetcode.com/problems/paint-house/

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color 
is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color
red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue. 
             Minimum cost: 2 + 5 + 3 = 10.
*/

// We can create a DP array, if we do not want to modify the given input.
// Also space can be optimized, as all the time we just need values from the previous cell.

class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        for (int i = 1; i < n; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return min(costs[n - 1][0], costs[n - 1][1], costs[n - 1][2]);
    }    
    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}

/*
https://leetcode.com/problems/paint-house-ii/

There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with 
color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 
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
