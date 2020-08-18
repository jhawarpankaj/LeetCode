/*
https://leetcode.com/problems/connecting-cities-with-minimum-cost/

There are N cities numbered from 1 to N.
You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1 and city2 together.  
(A connection is bidirectional: connecting city1 and city2 is the same as connecting city2 and city1.)

Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1) that connects those two cities together.  
The cost is the sum of the connection costs used. If the task is impossible, return -1.

Example 1:
Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2:
Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.
 
Note:
1 <= N <= 10000
1 <= connections.length <= 10000
1 <= connections[i][0], connections[i][1] <= N
0 <= connections[i][2] <= 10^5
connections[i][0] != connections[i][1]
*/

/*
Using Prims.
Note that in Prims Algorithm, the termination condition is reached when all the vertices are visited.
*/

class Solution {
    public int minimumCost(int N, int[][] connections) {
        PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> {
            if (a[1] > b[1]) return 1;
            else if (a[1] == b[1]) return 0;
            else return -1;
        });
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] conn : connections) {
            graph.computeIfAbsent(conn[0], x -> new ArrayList<>()).add(new int[] {conn[1], conn[2]});
            graph.computeIfAbsent(conn[1], x -> new ArrayList<>()).add(new int[] {conn[0], conn[2]});
        }
        PQ.add(new int[] {1, 0}); // start by adding any vertex to the PQ. (notice that we added cost as 0).
        int ans = 0;
        Set<Integer> visited = new HashSet<>();
        while (!PQ.isEmpty()) {
            int[] curr = PQ.remove();
            if (visited.contains(curr[0])) continue;
            visited.add(curr[0]);
            ans += curr[1];
            for (int[] adj : graph.getOrDefault(curr[0], new ArrayList<>())) {
                PQ.add(adj);
            }
        }
        return visited.size() == N ? ans : -1;
    }
}

/*
Using Kruskal.
Note that in Kruskal, the termination condition is reached when N - 1 (N = number of vertices) edges are visited.
*/

class Solution {
    public int minimumCost(int N, int[][] connections) {
        int[] root = new int[N + 1];
        Arrays.sort(connections, (a, b) -> {
            if (a[2] > b[2]) return 1;
            else if (a[2] == b[2]) return 0;
            else return -1;
        });
        for (int i = 1; i < N; i++) root[i] = i;
        int ans = 0;
        int count = 0;
        for (int[] conn : connections) {
            int findA = find(root, conn[0]);
            int findB = find(root, conn[1]);
            if (findA == findB) continue;
            union(root, findA, findB);
            ans += conn[2];
            count++;
        }
        return count == N - 1 ? ans : -1;
    }
    
    int find(int[] root, int i) {
        while (i != root[i]) i = root[i];
        return i;
    }
    
    void union(int[] root, int a, int b) {
        root[a] = b;
    }
    
}
