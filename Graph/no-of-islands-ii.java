/*
https://leetcode.com/problems/number-of-islands-ii/

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

// The brute force method: where we count no of connected components each position.
class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[] root = new int[m * n];
        Arrays.fill(root, -1);
        int total = 0;
        int[][] adj = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        List<Integer> result = new ArrayList<>();        
        
        for (int[] pos : positions) {
            int count = 0;
            int currRel = pos[0] * n + pos[1];
            if(root[currRel] != -1) {
                result.add(total);
                continue;
            }
            root[currRel] = currRel;
            for (int k = 0; k < adj.length; k++) {
                int x = pos[0] + adj[k][0];
                int y = pos[1] + adj[k][1];
                int adjRel = x * n + y;
                if (!isValid(x, y, m, n) || root[adjRel] == -1) continue;
                int rootAdj = find(root, adjRel);
                int rootCurr = find(root, currRel);
                if (rootAdj == rootCurr) continue;
                union(root, rootAdj, rootCurr);
                count++; // no. of disconnected components it's joining. 
            }
            // if it joined 2 disconnected components, then reduce that and is itself part of 1 big component.
            total = total - count + 1;  
            result.add(total);
        }
        return result;
    }
    
    int find(int[] root, int i) {
        while (i != root[i]) i = root[i];
        return i;
    }
    
    void union(int[] root, int a, int b) {
        root[a] = root[b];
    }
    
    boolean isValid(int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n) return false;
        return true;
    }
}
