/*
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/

// This is a DP solution but is actually finding the length of the longest topological ordering in the graph.
// During DFS, we keep track of the length of the longest top sort starting from each cell.
class Solution {
    int max = 1;
    int[][] adj = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] == -1) DFS(matrix, dp, i, j);    
            }            
        }
        return max;
    }
    
    int DFS(int[][] matrix, int[][] dp, int i, int j) {
        dp[i][j] = 1;
        for (int k = 0; k < adj.length; k++) {
            int x = i + adj[k][0];
            int y = j + adj[k][1];
            if (isValid(matrix, x, y) && matrix[x][y] < matrix[i][j]) {
                if (dp[x][y] != -1) dp[i][j] = Math.max(dp[i][j], 1 + dp[x][y]);
                else dp[i][j] = Math.max(dp[i][j], 1 + DFS(matrix, dp, x, y));
            }
        }
        max = Math.max(max, dp[i][j]);
        return dp[i][j];
    }
    
    boolean isValid(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) return false;
        return true;
    }    
}

// Find the length of the longest top sort using BFS and using Kahn's indegree concept.
// If we are able to imagine how the graph is formed, writing code is just using the template.
// Converting matrix to graph, there will be a directed edge from a cell to its adjacent cell, if the adjacent cell
// is greater than the current cell. 1 -> 2 (2 is an adjacent cell of 1 and 2 > 1)
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int[][] indegree = new int[m][n];
        int[][] adj = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < adj.length; k++) {
                    int x = i + adj[k][0];
                    int y = j + adj[k][1];
                    if (isValid(matrix, x, y) && matrix[x][y] < matrix[i][j]) indegree[i][j]++;
                }
            }
        }
        Queue<int[]> Q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (indegree[i][j] == 0) Q.add(new int[] {i, j});
            }
        }
        int length = 0;
        while (!Q.isEmpty()) {
            for (int len = Q.size(); len > 0; len--) {
                int[] curr = Q.remove();
                for (int k = 0; k < adj.length; k++) {
                    int x = curr[0] + adj[k][0];
                    int y = curr[1] + adj[k][1];
                    if (isValid(matrix, x, y) && matrix[x][y] > matrix[curr[0]][curr[1]] && --indegree[x][y] == 0) {
                        Q.add(new int[] {x, y});
                    }
                }
            }
            length++;
        }
        return length;
    }
    
    boolean isValid(int[][] matrix, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) return false;
        return true;
    }
}
