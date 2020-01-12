// https://leetcode.com/problems/number-of-operations-to-make-network-connected/

// A naive solution...
class Solution {
    public int makeConnected(int n, int[][] connections) {
        
        int minWiresReq = n - 1;
        int givenTotalWires = connections.length;
        
        if(givenTotalWires < minWiresReq) return -1;
        Set<Integer> set = new HashSet<Integer>();
        
        int[] root = new int[n];
        int[] rank = new int[n];
        
        // initialize : make-set operation.
        for(int i = 0; i < n; i++) {
            root[i] = i;
        }
        
        // union
        for(int i = 0; i < connections.length; i++) {
            union(root, rank, connections[i][0], connections[i][1]);
            set.add(connections[i][0]);
            set.add(connections[i][1]);
        }
        
        int reqWiresForAlreadyConnected = set.size() - 1;
        int surplus = givenTotalWires - reqWiresForAlreadyConnected;
        
        int connComp = 0;
        for(int i = 0; i < root.length; i++) {
            if(root[i] == i) connComp++;
        }
        
        return connComp - 1 <= surplus ? connComp - 1 : -1;
    }
    
    // union by rank
    void union(int[] root, int[] rank, int a, int b) {
        int aRoot = find(root, a);
        int bRoot = find(root, b);
        if(aRoot == bRoot) return;
        if(rank[aRoot] <= rank[bRoot]) {
            root[bRoot] = root[aRoot];
            rank[bRoot] += rank[aRoot];
        }
        else {
            root[aRoot] = root[bRoot];
            rank[aRoot] += rank[bRoot];
        }
        // root[bRoot] = aRoot;
    }
    
    // find and compress path
    int find(int[] root, int i) {
        while(i != root[i]) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
}

// A better solution...

class Solution {
    public static int findParent(int[] par, int i) {
        while(i != par[i]) i = par[i];
        return i;
    }
    public int makeConnected(int n, int[][] connections) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) parent[i] = i;
        int m = connections.length;
        int components = 0;
        int extraEdge = 0;
        for(int i = 0; i < m; i++) {
            int p1 = findParent(parent, connections[i][0]);
            int p2 = findParent(parent, connections[i][1]);
            if(p1 == p2) extraEdge++;
            else parent[p1] = p2;
        }
        for(int i = 0; i < n; i++) if(parent[i] == i) components++;
        return (extraEdge >= components - 1) ? components - 1 : -1;
    }
}
