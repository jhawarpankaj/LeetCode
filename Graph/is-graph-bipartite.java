/*
https://leetcode.com/problems/is-graph-bipartite/

Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A 
and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 
and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
*/

// A graph is bipartite if it can be colored using only 2 colors, such that no two adacent nodes have the same color.
// DFS...
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        // maintain 3 colors:
        // 0 : uncolored, 1: represent any color (blue or red), -1: opposite color of 1.
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !isValidColor(graph, colors, i, 1)) return false;
        }
        return true;
    }
    
    boolean isValidColor(int[][] graph, int[] colors, int i, int color) {
        if (colors[i] != 0) return colors[i] == color;
        colors[i] = color;
        for (int adj : graph[i]) {
            if (!isValidColor(graph, colors, adj, -color)) return false;
        }
        return true;
    }
}

// BFS...
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0) {
                Q.add(i);
                colors[i] = 1;
                while (!Q.isEmpty()) {
                    int curr = Q.remove();
                    for (int adj : graph[curr]) {
                        if (colors[adj] == 0) {
                            Q.add(adj);
                            colors[adj] = -colors[curr];
                        } 
                        // if the adj vertex is already colored and it's color 
                        // is equal to the color of the current vertex, not a bipartite graph.
                        else if (colors[adj] == colors[curr]) return false;
                    }
                }
            }
        }
        return true;
    }
}
