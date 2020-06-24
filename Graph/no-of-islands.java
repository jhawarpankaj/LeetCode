/*
https://leetcode.com/problems/number-of-islands/

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands 
horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
*/

// DFS to find no of connected components in a graph.
class Solution {
    int[][] adj = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }    
    void dfs(char[][] grid, int i, int j) {
        grid[i][j] = '0'; // marking 0 to represent a visited cell instead of maintaining a visited hashset.
        for (int k = 0; k < adj.length; k++) {
            int x = i + adj[k][0];
            int y = j + adj[k][1];
            if (isValid(grid, x, y) && grid[x][y] == '1') dfs(grid, x, y);
        }
    }    
    boolean isValid(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) return false;
        return true;
    }   
}
