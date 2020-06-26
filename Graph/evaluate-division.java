/*
https://leetcode.com/problems/evaluate-division/

Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). 
Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), 
and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
*/

class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, HashMap<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            graph.computeIfAbsent(eq.get(0), x -> new HashMap<String, Double>()).put(eq.get(1), values[i]);
            graph.computeIfAbsent(eq.get(1), x -> new HashMap<String, Double>()).put(eq.get(0), 1 / values[i]);
        }
        double[] result = new double[queries.size()];
        int ind = 0;
        for (List<String> query : queries) {
            String start = query.get(0);
            String goal = query.get(1);
            if (!graph.containsKey(start)) result[ind++] = -1D;
            else result[ind++] = dfs(graph, start, goal, 1D, new HashSet<String>());
        }
        return result;
    }
    
    double dfs(Map<String, HashMap<String, Double>> graph, String source, String goal, double val, HashSet<String> visited) {
        visited.add(source);
        if (source.equals(goal)) return val;
        for (String adj : graph.getOrDefault(source, new HashMap<String, Double>()).keySet()) {
            if (!visited.contains(adj)) {
                double ans = dfs(graph, adj, goal, val * graph.get(source).get(adj), visited);
                if (ans != -1D) return ans; 
            }
        }
        // all adjacent vertices of the source vertex are explored but the goal was not found.
        return -1D;
    }
}
