/*
https://leetcode.com/problems/shortest-path-in-binary-matrix/

In an N by N square grid, each cell is either empty (0) or blocked (1).
A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

Example 1:

Input: [[0,1],[1,0]]
Output: 2

Example 2:
Input: [[0,0,0],[1,1,0],[1,1,0]]

Note:
1 <= grid.length == grid[0].length <= 100
grid[r][c] is 0 or 1
Output: 4
*/

// Using standard BFS template to find the shortest distance to goal without passing the distance alongwith each node.

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1) return -1;        
        // all 8 directional adjacent cells.
        int[][] adj = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        Queue<int[]> Q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        Q.add(new int[] {0, 0});
        visited[0][0] = true;
        int dist = 1;
        while (!Q.isEmpty()) {
        // this will ensure that we exhaust all the adjacent cells of a cell in one shot. 
        // This way we can know the distance of each node, it will be just dist + 1.
            for (int len = Q.size(); len > 0; len--) {
                int[] curr = Q.remove();
                if (curr[0] == m - 1 && curr[1] == n - 1) return dist;
                for (int k = 0; k < adj.length; k++) {
                    int x = curr[0] + adj[k][0];
                    int y = curr[1] + adj[k][1];
                    if (isValid(grid, x, y) && !visited[x][y]) {
                        Q.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            dist++;
        }
        return -1;
    }
    
    boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1) return false;
        return true;
    }
}

// by passing along the extra information about their distance.
// Note: this is not the optimal way of solving the problem, the above is.

class Solution {
    class Node {
        int i, j;
        int level;
        Node(int i, int j, int level) {
            this.i = i;
            this.j = j;
            this.level = level;
        }
    }
    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<Node> Q = new LinkedList<>();
        int[][] adj = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        if (grid[0][0] == 0) {
            Q.add(new Node(0, 0, 1));
            visited[0][0] = true;
        }
        while (!Q.isEmpty()) {
            Node curr = Q.remove();
            if (curr.i == grid.length - 1 && curr.j == grid[0].length - 1) return curr.level;
            for (int k = 0; k < adj.length; k++) {
                int x = curr.i + adj[k][0];
                int y = curr.j + adj[k][1];
                if (isValid(grid, x, y) && !visited[x][y]) {
                    Q.add(new Node(x, y, curr.level + 1));
                    visited[x][y] = true;
                }
            }
        }
        return -1;        
    }
    
    boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1) return false;
        return true;
    }
}

// The below is a very bad solution and should not be used. Just showing it here to emphasize how BACTRACK differs from a BFS solution.
// We will explore all the path from source to goal and pick the minimum of all of them. This is the BRUTE FORCE way of solving the problem.
// Also note that we cannot use DFS to solve the problem.

class Solution {
    int result;
    int[][] adj;
    public int shortestPathBinaryMatrix(int[][] grid) {
        result = Integer.MAX_VALUE;
        adj = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};        
        traverse(grid, 0, 0, 0);
        return result == Integer.MAX_VALUE ? -1 : result;
    }
    
    void traverse(int[][] grid, int i, int j, int count) {
        if (!isValid(grid, i, j) || grid[i][j] == 1 || grid[i][j] == -1) return;        
        if (i == grid.length - 1 && j == grid[0].length - 1 && grid[i][j] == 0) {
            result = Math.min(result, count + 1);
            return;
        }
        grid[i][j] = -1; // to mark the node visited.
        count++;
        for (int k = 0; k < adj.length; k++) {
            int x = adj[k][0];
            int y = adj[k][1];
            traverse(grid, i + x, j + y, count);
        }
        grid[i][j] = 0; 
        count--;
    }
    
    boolean isValid(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return false;
        return true;
    }
}
