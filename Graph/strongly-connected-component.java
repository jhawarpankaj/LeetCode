/*
HackerEarth: https://www.hackerearth.com/zh/practice/algorithms/graphs/strongly-connected-components/tutorial/
Strongly Connected Component
You are given a graph with N nodes and M directed edges. Find C - D.

Where,
C Sum of number of nodes in all Strongly Connected Components with odd number of nodes.
D Sum of number of nodes in all Strongly Connected Components with even number of nodes.

Input:

First line contains 2 integers, N and M, denoting the number of nodes and the number of edges. 
Next M lines contain 2 integers A and B, meaning that there is a directed edge from A to B.

Output:
Output the number C - D.
*/

// The idea is to use the Kosaraju algorithm...
// The algorithm first do a DFS and keeps the last finished element at the top of the stack.
// Then reverses the edges of the graph.
// It then perform a DFS on the reversed graph by removing elements from the top of the stack.
// Why this works? https://www.geeksforgeeks.org/strongly-connected-components/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class StronglyConnectedComponentsKosaraju {
    public static void main(String args[] ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();                // Reading input from STDIN
        
        // First create an adjacency list.
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        int edge = Integer.parseInt(line.split(" ")[1]);
        while (edge-- > 0) {
        	line = br.readLine();  
            int source = Integer.parseInt(line.split(" ")[0]);
            int dest = Integer.parseInt(line.split(" ")[1]);
            ArrayList<Integer> temp = map.getOrDefault(source, new ArrayList<Integer>());
            temp.add(dest);
            map.put(source, temp);
        }
        
        // Step 1:
        // Do a DFS first but push the vertices to the stack at the end.
        // This way, the nodes finishing at the end will be at the top of the stack. 
        Deque<Integer> stack = new ArrayDeque<Integer>();
        List<Integer> nodes = new ArrayList<Integer>(map.keySet());
        Set<Integer> visited = new HashSet<Integer>();
        for(Integer v : nodes) {
        	if(!visited.contains(v)) {
        		DFS(v, visited, map, stack);
        	}            
        }
        
        // Step 2: 
        // Reverse the edges of the graph.
        Map<Integer, ArrayList<Integer>> newMap = reverseGraph(map);
        
        // Step 3:
        // Now do a DFS again starting from the top of the stack build earlier.
        visited.clear();
        int C = 0, D = 0;
        while(!stack.isEmpty()) {
            int top = stack.pop();
            if(!visited.contains(top)) {
                int size = DFSUtil(top, newMap, visited);    
                if(size % 2 == 1) C += size;
                else D += size;
            }
        }        
        System.out.println(C - D);
    }
    
    
    static int DFSUtil(Integer node, Map<Integer, ArrayList<Integer>> newMap, Set<Integer> visited) {
        int count = 0;
        visited.add(node);
        for(Integer adj : newMap.getOrDefault(node, new ArrayList<Integer>())) {
            if(!visited.contains(adj)) {
                count = count + DFSUtil(adj, newMap, visited);    
            }
        }
        return 1 + count;
    }
    
    static void DFS(Integer node, Set<Integer> visited, Map<Integer, ArrayList<Integer>> map, Deque<Integer> stack) {
        visited.add(node);
        for(int neigh : map.getOrDefault(node, new ArrayList<Integer>())) {
            if(!visited.contains(neigh)){
                DFS(neigh, visited, map, stack);
            }
        }
        stack.push(node);
    }
    
    static Map<Integer, ArrayList<Integer>> reverseGraph(Map<Integer, ArrayList<Integer>> map) {
        Map<Integer, ArrayList<Integer>> newMap = new HashMap<Integer, ArrayList<Integer>>();
        for(Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            int src = entry.getKey();
            ArrayList<Integer> dest = entry.getValue();
            for(Integer node : dest) {
                ArrayList<Integer> temp = newMap.getOrDefault(node, new ArrayList<Integer>());
                temp.add(src);
                newMap.put(node, temp);
            }
        }
        return newMap;
    }
}
