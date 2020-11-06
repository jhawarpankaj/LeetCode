/*
https://leetcode.com/problems/path-with-minimum-effort/
*/

// Generate all paths (Backtracking).
class Solution {    
    int min = Integer.MAX_VALUE;
    final int[][] adj = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int minimumEffortPath(int[][] heights) {
        backtrack(heights, 0, 0, 0, heights[0][0]);
        return min;
    }
    
    void backtrack(int[][] heights, int i, int j, int maxTillNow, int parVal) {
        int m = heights.length;
        int n = heights[0].length;
        maxTillNow = Math.max(maxTillNow, Math.abs(parVal - heights[i][j]));        
        if (i == m - 1 && j == n - 1) {
            min = Math.min(min, maxTillNow);
            return;
        }
        int temp = heights[i][j];
        heights[i][j] = -1;
        for (int k = 0; k < adj.length; k++) {
            int x = adj[k][0] + i;
            int y = adj[k][1] + j;
            if (isValid(heights, x, y) && heights[x][y] != -1) {
                backtrack(heights, x, y, maxTillNow, temp);
            }
        }
        heights[i][j] = temp;
    }
    
    boolean isValid(int[][] heights, int i, int j) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[i].length) return false;
        return true;
    }
}

// Optimization (Dijkstra)
class Solution {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        if (m == 0) return 0;
        int n = heights[0].length;
        if (n == 0) return 0;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        PriorityQueue<Cell> PQ = new PriorityQueue<>((a, b) -> {
            return a.dist - b.dist;
        });
        PQ.add(new Cell(0, 0, 0));
        final int[][] adj = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        while (!PQ.isEmpty()) {
            Cell curr = PQ.remove();
            if (curr.i == m - 1 && curr.j == n - 1) return curr.dist;
            for (int k = 0; k < adj.length; k++) {
                int x = curr.i + adj[k][0];
                int y = curr.j + adj[k][1];
                if (isValid(x, y, m, n)) {
                    int max = Math.max(curr.dist, Math.abs(heights[curr.i][curr.j] - heights[x][y]));
                    if (max < dist[x][y]) {
                        dist[x][y] = max;
                        PQ.add(new Cell(x, y, max));
                    }
                }                
            }
        }
        return 0;
    }
    
    boolean isValid(int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n) return false;
        return true;
    }
}

class Cell {
    int i, j;
    int dist;
    Cell(int i, int j, int dist) {
        this.i = i;
        this.j = j;
        this.dist = dist;
    }
}

/* 
Another problem.
https://leetcode.com/problems/path-with-maximum-probability/
*/

// Backtrack.
class Solution {
    double max = 0;
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {        
        boolean[] visited = new boolean[n];
        Map<Integer, List<Vertex>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0], to = edge[1];
            double wt = succProb[i];
            graph.computeIfAbsent(from, x -> new ArrayList<>()).add(new Vertex(to, wt));
            graph.computeIfAbsent(to, x -> new ArrayList<>()).add(new Vertex(from, wt));
        }
        backtrack(graph, start, end, 1, visited);
        return max;
    }
    
    void backtrack(Map<Integer, List<Vertex>> graph, int curr, int target, double prob, boolean[] visited) {
        if (curr == target) {
            max = Math.max(max, prob);
            return;
        }
        visited[curr] = true;
        for (Vertex adj : graph.getOrDefault(curr, new ArrayList<>())) {
            if (!visited[adj.no]) backtrack(graph, adj.no, target, prob * adj.wt, visited);
        }
        visited[curr] = false;
    }
}

class Vertex {
    int no;
    double wt;
    Vertex(int no, double wt) {
        this.no = no;
        this.wt = wt;
    }
}

// Dijkstra.
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] dist = new double[n];
        dist[start] = 1D; // start node of Dijkstra.
        Map<Integer, List<Vertex>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0], to = edge[1];
            double wt = succProb[i];
            graph.computeIfAbsent(from, x -> new ArrayList<>()).add(new Vertex(to, wt));
            graph.computeIfAbsent(to, x -> new ArrayList<>()).add(new Vertex(from, wt));
        }
        
        // note that we cannot return a - b. Comprator needs to return integer value always.
        PriorityQueue<Node> PQ = new PriorityQueue<>((a, b) -> {
            if (a.prob > b.prob) return -1;
            else if (b.prob == a.prob) return 0;
            else return 1;
        });
        PQ.add(new Node(start, 1));
        while (!PQ.isEmpty()) {
            Node curr = PQ.remove();
            if (curr.no == end) return curr.prob;          
            for (Vertex adj : graph.getOrDefault(curr.no, new ArrayList<>())) {
                if (curr.prob * adj.wt > dist[adj.no]) {
                    dist[adj.no] = curr.prob * adj.wt;
                    PQ.add(new Node(adj.no, dist[adj.no]));
                }
            }
        }
        return 0;
    }
}

class Vertex {
    int no;
    double wt;
    Vertex(int no, double wt) {
        this.no = no;
        this.wt = wt;
    }
}

class Node {
    int no;
    double prob;
    Node(int no, double prob) {
        this.no = no;
        this.prob = prob;
    }
}
