/*

https://leetcode.com/problems/minimum-path-sum/

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
Note: You can only move either down or right at any point in time.

Example:
Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

/* 
To come up with solution for such problems:
1. Always think that you are in the cell (i, j) and you have to find an answer for only that and not the original problem. 
For example: assume that you are in the cell (2, 2) and you have to find an answer for that and not the given input (say 10 X 10).
The above thinking will help build the recurrence relation. 
2. Now just iterate through the 2D array taking care of base cases.
*/

class Solution {
    public int minPathSum(int[][] grid) {
        int r = grid.length;
        if (r == 0) return 0;
        int c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];
        for (int i = 0, j = 1; j < c; j++) dp[i][j] += grid[i][j] + dp[i][j - 1];
        for (int i = 1, j = 0; i < r; i++) dp[i][j] += grid[i][j] + dp[i - 1][j];
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] += grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[r - 1][c - 1];
    }    
}

// As we can see from the above solution that anytime we need values from the previous rows.
// So, we can reduce the usage of space by using a 1D array.

class Solution {
    public int minPathSum(int[][] grid) {
        int r = grid.length;
        if (r == 0) return 0;
        int c = grid[0].length;
        int[] dp = new int[c];
        dp[0] = grid[0][0];
        // Initializing values for the first row. 
        for (int i = 0, j = 1; j < c; j++) dp[j] = dp[j - 1] + grid[i][j];
        
        // Iterating for the entire grid.
        for (int i = 1; i < r; i++) {
            dp[0] += grid[i][0]; // this is for the first cell of each row.
            for (int j = 1; j < c; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[c - 1];
    }
}

// We can further reduce the space by modifying the given input.
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        for (int i = 0, j = 1; j < n; j++) grid[i][j] += grid[i][j - 1];
        for (int j = 0, i = 1; i < m; i++) grid[i][j] += grid[i - 1][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
