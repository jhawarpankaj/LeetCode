/*
In this problem, a tree is an undirected graph that is connected and has no cycles.

The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3
*/

// If 2 incoming edges belong to the same component, it means that they form a cycle. It will be the last one in the sequence.


class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] root = new int[n];
        for(int i = 0; i < n; i++) root[i] = i;
        
        for(int i = 0; i < edges.length; i++) {
            int u = edges[i][0] - 1;
            int v = edges[i][1] - 1;
            
            int rootU = find(root, u);
            int rootV = find(root, v);
            
            if(rootU == rootV) return edges[i];
            else root[rootU] = rootV;
        }
        return new int[]{};
    }
    
    int find(int[] root, int i) {
        while(i != root[i]) i = root[i];
        return i;
    }
}
