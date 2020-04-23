import java.util.Stack;

/*
 * 1. The algorithm incrementally builds the solution by adding one node at a time.
 * 2. For each vertex in the graph (the outer K loop), we calculate the shortest distance between 
 * 	  all pair of vertices(the inner i and j loop) considering the current K as the latest addition to the set
 * 	  of allowed intermediate nodes. 
 * 	  a) Note that when we are at any value of k = m (say), it means that the algorithm has
 * 	  	 calculated the shortest distance between all pair of vertices till now but considering only the previous k - 1 as the 
 * 	  	 only allowed intermediate nodes (only those k nodes, one lesser than the current one are the considered intermediate nodes).
 * 	  b) This way the original solution is when we allow all vertices of the graph as the intermediate nodes.
 * 	  c) The order in which the vertices are added one by one is not important. In the below implementation, the outer loop
 * 	  	 conincidentally adding vertices in increasing order but that's not required.
 * 	  d) We just want to add one vertex at a time (in any order is fine). The value of d[i][j] during the current iteration of k,
 * 	  	 holds the shortest path between all pairs considering the NUMBER of allowed nodes = k - 1 (one lesser after the current addition).
 * 
 * Algorithm:
 * 1. When it's the turn of current node K, for each pair of vertices, there can be two cases:
 * 	  a) the current node is not an intermediate node: then current d[i][j] will be same as old d[i][j].
 * 	  b) the current node k is an intermediate node: then split the path (i, j) = (i, k) and (k, j). The current value of dist[i][k] and dist[k][j] 
 * 		 will give us the shortest distances between i -> k and k -> j considering only allowed intermediate k - 1 nodes.
 * 		 As per the lemma, the subpaths of the shortest path are also shortest. 
 * 		 Hence, this way we can get add one more node to the allowed intermediate nodes and build the solution gradually.
 * 	 Merging these two cases, the value of current d[i][j] = MINIMUM OF [d[i][j] (which is atually the d[i][j] considering only k - 1 intermediate nodes) and d[i][k] + d[k][j])].
 * 
 * 2. Path can be reconstructed using a 2D matrix where if i -> j shortest path, path[i][j] = i.
 * 3. The algorithm can help detect any negative cycle as well. Imagine a negative cycle of 3 nodes. When any of the node which is part of the cycle is considered as the current allowed
 * 	  intermediate node (current k) and the remaining 2 nodes of the cycle already considered, then distance from i to i (the same node) will always produce a lesser distance. Hence, 
 *    it will update the cell dist[i][i] with a negative value. Hence, if any of the diagonal elem is less than 0, then there's a negative cycle. 
 */

public class FloydWarshall {

	final static int INF = Integer.MAX_VALUE, V = 4;

	private static void calShortestPaths(int[][] graph, int[][] dist, int[][] path) {

		// Initializing, equivalent to calulating the min distance between all pair of
		// nodes
		// considering 0 intermediate nodes. Hence, it's just the original edges, as
		// they don't have any intermedite edges.
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				dist[i][j] = graph[i][j];

				if(dist[i][j] == INF) path[i][j] = -1;
				else path[i][j] = i;
			}
		}

		for(int k = 0; k < V; k++) { // adding one vertex at a time.
										// note that the order is not important.

			// Calculate min distance bet all pair of vertices considering the k
			// intermediate nodes (including the current one).
			for(int i = 0; i < V; i++) {
				for(int j = 0; j < V; j++) {
					if(dist[i][k] != INF && dist[k][j] != INF
							&& dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}
	}

	private static void printPath(int u, int v, int[][] path) {
		if(u == v || path[u][v] == -1) return;
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		int src = path[u][v];
		while(u != src) {
			stack.push(src);
			src = path[u][src];
		}
		stack.push(u);
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	public static void main(String[] args) {
		int V = 4;
		int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 },
				{ INF, INF, INF, 0 } };
		int[][] dist = new int[V][V];
		int[][] path = new int[V][V];
		calShortestPaths(graph, dist, path);

		// Print all pair shortest path values...
		for(int i = 0; i < V; i++) {
			for(int j = 0; j < V; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}

		// Reconstruct path...
		printPath(0, 3, path);

		// Check if a negative cycle exists...
		for(int i = 0; i < V; i++) {
			if(dist[i][i] < 0) {
				System.out.println("Negative cycle exists in the graph.");
				break;
			}
		}
	}

}
