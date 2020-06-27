/*
https://leetcode.com/problems/reconstruct-itinerary/

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. 
All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
One must use all the tickets once and only once.
Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> path = new LinkedList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> travel : tickets) {
            graph.computeIfAbsent(travel.get(0), x -> new PriorityQueue<String>()).add(travel.get(1));
        }
        DFS(graph, "JFK", path);
        return path;
    }
    
    void DFS(Map<String, PriorityQueue<String>> graph, String curr, List<String> path) {
        // path.add(curr); note that this will produce an incorrect path.
        // Only once all the adjacent edges of a node are completely visited, then we can add the node at beginning.        
        while(graph.containsKey(curr) && !graph.get(curr).isEmpty()) {
            DFS(graph, graph.get(curr).remove(), path);
        }
        path.add(0, curr); // this is same as using a stack.
    }
}
