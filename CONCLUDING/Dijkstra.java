// https://leetcode.com/problems/network-delay-time/

/*
Note that below is the O(ElogV) implementation of the Dijkstra using TreeSet. Realize that the time complexity goes to O(ElogE) when we use PQ as in that the no 
of time a vertex can get added to the PQ is equal to O(E). Hence, O(ElogE). 
*/

class Edge {
    int to;
    int wt;
    Edge(int to, int wt) {
        this.to = to;
        this.wt = wt;
    }
}

class Solution {
    
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>(); // important to note if it is a weighted graph.
        Map<Integer, Integer> dist = new HashMap<>();
        buildGraph(graph, times, dist);                
        Set<Integer> visited = new HashSet<>();
        dijkstra(graph, dist, K, visited);
        if (visited.size() != N) return -1;
        int max = Integer.MIN_VALUE;
        for (int node : dist.keySet()) {
            max = Math.max(max, dist.get(node));
        }
        return max;
    }
    
    void buildGraph(Map<Integer, List<Edge>> graph, int[][] times, Map<Integer, Integer> dist) {
        for (int[] time : times) {
            int u = time[0], v = time[1];
            int wt = time[2];
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(new Edge(v, wt));
            dist.put(u, Integer.MAX_VALUE);
            dist.put(v, Integer.MAX_VALUE);
        }
    }
    
    void dijkstra(Map<Integer, List<Edge>> graph, Map<Integer, Integer> dist, int src, Set<Integer> visited) {
        // important to note how the constructor is defined when the dist of 2 vertices becomes equal.
        TreeSet<Integer> treeSet = new TreeSet<>((a, b) -> {
            if (dist.get(a) < dist.get(b)) return -1;
            else if (dist.get(a).equals(dist.get(b))) return a - b;
            else return 1;
        });        
        dist.put(src, 0);
        treeSet.add(src);
        while (!treeSet.isEmpty()) {
            int from = treeSet.pollFirst();
            visited.add(from);
            for (Edge adj : graph.getOrDefault(from, new ArrayList<>())) {
                if (!visited.contains(adj.to) && dist.get(from) + adj.wt < dist.get(adj.to)) {
                    treeSet.remove(adj.to);
                    dist.put(adj.to, dist.get(from) + adj.wt);
                    treeSet.add(adj.to);
                }
            }
        }
    }
}
