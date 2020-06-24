/*
https://leetcode.com/problems/shortest-distance-from-all-buildings/

You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. 
You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/* 
An alternate way would be to maintain two more 2D array:
int[][] distance; // maintain distance from each building
int[][] reach; // count from how many buildings is this cell reachable.
We can BFS from each building and fill the above 2D array and at the end we can get the cell with min distance and reachable from all buildings.
*/
class Solution {
    public int shortestDistance(int[][] grid) {
        int total = getBuilding(grid);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    ans = Math.min(ans, getDistance(grid, i, j, total));
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    
    int getDistance(int[][] grid, int i, int j, int total) {
        int[][] adj = {{-1, 0}, {0, -1}, {0, 1}, {1 ,0}};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> Q = new LinkedList<>();
        visited[i][j] = true;
        Q.add(new int[] {i, j});
        int dist = 0;
        int ans = 0;
        while (!Q.isEmpty()) {
            for (int len = Q.size(); len > 0; len--) {
                int[] curr = Q.remove();
                if (grid[curr[0]][curr[1]] == 1) {
                    ans += dist;
                    total--;
                    continue;
                }
                for (int k = 0; k < adj.length; k++) {
                    int X = curr[0] + adj[k][0];
                    int Y = curr[1] + adj[k][1];
                    if (isValid(grid, X, Y) && !visited[X][Y]) {
                        Q.add(new int[] {X, Y});
                        visited[X][Y] = true;
                    }
                }
            }
            dist++;
        }
        return total == 0 ? ans : Integer.MAX_VALUE;
    }
    
    boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] == 2) return false;
        return true;
    }
    
    int getBuilding(int[][] grid) {
        int count = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 1) count++;
            }
        }
        return count;
    }
    
}
