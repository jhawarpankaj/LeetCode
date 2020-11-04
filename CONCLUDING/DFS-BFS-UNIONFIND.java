/*
https://leetcode.com/problems/number-of-islands/
*/

// DFS
class Solution {        
    
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        if (!isValid(grid, i, j)) return; // todo
        if (grid[i][j] == '0' || visited[i][j]) return;
        visited[i][j] = true;
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i, j - 1);
        dfs(grid, visited, i, j + 1);
    }
    
    boolean isValid(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return false;
        return true;
    }
    
}

// BFS
class Solution {
    
    final static int[][] adj = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, visited, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    void bfs(char[][] grid, boolean[][] visited, int i, int j) {
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[] {i, j});
        while (!Q.isEmpty()) {
            int[] cell = Q.remove();
            for (int k = 0; k < adj.length; k++) {
                int x = cell[0] + adj[k][0];
                int y = cell[1] + adj[k][1];
                if (isValid(grid, x, y) && !visited[x][y] && grid[x][y] == '1') {
                    Q.add(new int[] {x, y});
                    visited[x][y] = true;
                }
            }
        }
    }
    
    boolean isValid(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return false;
        return true;
    }
}

// Union Find
class UnionFind {
    int connectedComp;
    int[] root;
    int[] rank;
    
    UnionFind(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        root = new int[m * n];
        rank = new int[m * n];
        connectedComp = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    root[i * n + j] = i * n + j;
                    rank[i * n + j] = 1;
                    connectedComp++;
                }
            }
        }
    }
    
    // Here height of the tree is used as rank. 
    // Size of the tree can also be used for rank.
    // Read: https://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
    void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI == rootJ) return;
        if (rank[rootI] > rank[rootJ]) root[rootJ] = root[rootI];
        else if (rank[rootJ] > rank[rootI]) root[rootI] = root[rootJ];
        else {
            root[rootI] = root[rootJ];
            rank[rootJ]++;
        }
        connectedComp--;
    }
    
    int find(int i) {
        if (i != root[i]) root[i] = find(root[i]);
        return root[i];
    }
    
    int getConnectedComponents() {
        return connectedComp;
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        final int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        UnionFind UF = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                for (int k = 0; k < adj.length; k++) {
                    int x = i + adj[k][0];
                    int y = j + adj[k][1];
                    if (isValid(grid, x, y) && grid[x][y] == '1') {
                        UF.union(i * n + j, x * n + y);
                    }
                }
            }
        }
        return UF.getConnectedComponents();
    }
    
    boolean isValid(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length) return false;
        return true;
    }
}
