/*
https://www.spoj.com/problems/MST/

Find the minimum spanning tree of the graph.

Input
On the first line there will be two integers N - the number of nodes and M - the number of edges. (1 <= N <= 10000), 
(1 <= M <= 100000)
M lines follow with three integers i j k on each line representing an edge between node i and j with weight k. 
The IDs of the nodes are between 1 and n inclusive. The weight of each edge will be <= 1000000.

Output
Single number representing the total weight of the minimum spanning tree on this graph. There will be only one possible MST.

Example
Input:
4 5
1 2 10
2 3 15
1 3 5
4 2 2
4 3 40

Output:
17
*/

/*
An MST of a graph is a tree in which all the vertices of the graph(n) are included and only n - 1 edges are there.
Prims Algo:
1. Start with any vertex.
2. Add all of its incident edges to a Priority Queue.
3. Pick the least from the PQ, and add its neighbouring edges.
4. Repeat until PQ is empty.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
	
	static class Vertex {
		List<Edge> neighbours;
		Vertex() {
			neighbours = new ArrayList<Edge>();
		}
	}
	
	static class Edge {
		int end, cost;
		Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws java.lang.Exception {
		Scanner sc = new Scanner(System.in);
		
		int vertices = sc.nextInt(), edges = sc.nextInt();
		Vertex[] graph = new Vertex[vertices + 1];
		
		for(int i = 0; i < graph.length; i++) {
			graph[i] = new Vertex();
		}
	
		for(int i = 1; i <= edges; i++) {
			int src = sc.nextInt();
			int dest = sc.nextInt();
			int weight = sc.nextInt();
			graph[src].neighbours.add(new Edge(dest, weight));
			graph[dest].neighbours.add(new Edge(src, weight));
		}
		sc.close();
		MSTPrims(graph, 1);
	}

	private static void MSTPrims(Vertex[] graph, int src) {		
		
		// Create a priority queue to add the edges sorted on ascending order of weights.
		PriorityQueue<Edge> PQ = new PriorityQueue<Edge>((a, b) -> {
			return a.cost - b.cost;
		});
		// Add the edges of source.
		for(Edge e : graph[src].neighbours) PQ.add(e); 
		
		// Keep a track of which all vertices has been visited.
		boolean[] visited = new boolean[graph.length];
		visited[src] = true;		
		int sum = 0;
		
		while(!PQ.isEmpty()) {
			Edge min = PQ.remove();
			// If a node is already visited, we don't consider it again.
			if(visited[min.end]) continue;
			sum += min.cost;
			visited[min.end] = true;
			for(Edge e : graph[min.end].neighbours) {
				if(!visited[e.end]) PQ.add(e);
			}
		}
		System.out.println("Sum: " + sum);
	}
}
