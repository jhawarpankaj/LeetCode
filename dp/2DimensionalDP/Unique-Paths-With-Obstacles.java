/*
https://leetcode.com/problems/unique-paths-ii/

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.
Note: m and n will be at most 100.

Example 1:
Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
*/

// Note that space can be optimized by using a 1D array.
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        int[][] count = new int[m][n];
        if (obstacleGrid[0][0] != 1) count[0][0] = 1;
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] != 1) count[0][j] = count[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] != 1) count[i][0] = count[i - 1][0];
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                count[i][j] = count[i - 1][j] + count[i][j - 1];
            }
        }
        return count[m - 1][n - 1];
    }
}
