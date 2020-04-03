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
Kruskal's Algo:
1. Sort the edges in ascending order.
2. If the two vertices are in two different DISJOINT SETS, the current edge is a safe edge. (as edges are sorted in ascending order, 
its guaranteed to be part of MST). Else, discard the current edge.
3. Repeat until n - 1 vertices are covered or iterate over all edges.
*/

import java.util.Arrays;
import java.util.Scanner;

public class Kruskal {
	
	static class Edge {
		int src;
		int dest;
		int wt;
		Edge(int src, int dst, int wt) {
			this.src = src;
			this.dest = dst;
			this.wt = wt;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertices = sc.nextInt();
		int edges = sc.nextInt();
		int[] root = new int[vertices + 1];
		for(int i = 1; i < root.length; i++) root[i] = i;
		Edge[] graph = new Edge[edges];
		for(int i = 0; i < edges; i++) {
			int src = sc.nextInt();
			int dst = sc.nextInt();
			int wt = sc.nextInt();
			graph[i] = new Edge(src, dst, wt);
		}
		sc.close();
		Arrays.sort(graph, (a, b) -> {
			return a.wt - b.wt;
		});
		MSTKruskal(graph, root);
	}

	private static void MSTKruskal(Edge[] graph, int[] root) {
		int sum = 0;
		for(int i = 0; i < graph.length; i++) {
			Edge edge = graph[i];
			int src = edge.src, dest = edge.dest;
			int wt = edge.wt;
			int aRoot = find(root, src);
			int bRoot = find(root, dest);
			if(aRoot == bRoot) continue;
			root[aRoot] = bRoot;
			sum += wt;
		}
		System.out.println("Sum: " + sum);
	}
	
	private static int find(int[] root, int i) {
		while(i != root[i]) i = root[i];
		return i;
	}
}
