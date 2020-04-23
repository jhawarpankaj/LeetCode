import java.util.*;

public class BellmanFord {
	int V, E;
	List<Edge> edges;

	BellmanFord(int V, int E) {
		this.V = V;
		this.E = 8;
		edges = new ArrayList<>();
	}

	class Edge {
		int u, v;
		int wt;
		Edge(int u, int v, int weight) {
			this.u = u;
			this.v = v;
			this.wt = weight;
		}
	}

	private void addEdge(int u, int v, int wt) {
		edges.add(new Edge(u, v, wt));
	}

	// Returns false if it has negative weight cycle.
	private boolean calculateShortestDist(int s, int[] dist, int[] parent) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[s] = 0;
		parent[s] = -1;

		boolean flag = false; // to check if we found all minimum distance even before iterating V -
								// 1 times.

		// iterate V - 1 times and keep on updating distance.
		for(int i = 0; i < V - 1; i++) {
			flag = false;
			for(Edge e : edges) {
				if(dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.wt < dist[e.v]) {
					dist[e.v] = dist[e.u] + e.wt;
					parent[e.v] = e.u;
					flag = true;
				}
			}
			if(!flag) return false;
		}

		// Run it Vth time to check if there is a cycle.
		for(Edge e : edges) {
			if(dist[e.u] != Integer.MAX_VALUE && dist[e.u] + e.wt < dist[e.v]) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		int V = 5;
		int E = 8;

		// initialize...
		BellmanFord graph = new BellmanFord(V, E);
		graph.addEdge(0, 1, -1);
		graph.addEdge(0, 2, 4);
		graph.addEdge(1, 2, 3);
		graph.addEdge(1, 3, 2);
		graph.addEdge(1, 4, 2);
		graph.addEdge(3, 2, 5);
		graph.addEdge(3, 1, 1);
		graph.addEdge(4, 3, -3);

		// calculate...
		int[] dist = new int[V];
		int[] parent = new int[V];
		int s = 0;
		boolean hasNegativeCycle = graph.calculateShortestDist(s, dist, parent);

		// prints output...
		if(hasNegativeCycle) {
			System.out.println(
					"Graph has a negative weight cycle. Hence no shortest path exists between any node.");
			return;
		}
		for(int i = 0; i < dist.length; i++) {
			System.out.println("Minimum distance from " + s + " -> " + i + ": " + dist[i]);
		}

		// Sample path from source to target.
		int t = 4;
		System.out.println("Sample path from " + s + " -> " + t);
		StringBuilder sb = new StringBuilder();
		sb.append(t);
		for(int i = t; i != s; i = parent[i]) {
			int dad = parent[i];
			sb.insert(0, dad + "->");
		}
		sb.substring(2, sb.length());
		System.out.println(sb.toString());
	}

}

/*
Output:
Minimum distance from 0 -> 0: 0
Minimum distance from 0 -> 1: -1
Minimum distance from 0 -> 2: 2
Minimum distance from 0 -> 3: -2
Minimum distance from 0 -> 4: 1
Sample path from 0 -> 4
0->1->4
*/
