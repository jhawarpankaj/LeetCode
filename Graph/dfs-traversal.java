/*
https://leetcode.com/problems/time-needed-to-inform-all-employees/

A company has n employees with a unique ID for each employee from 0 to n - 1. The head of the company has is the 
one with headID.

Each employee has one direct manager given in the manager array where manager[i] is the direct manager of the i-th 
employee, manager[headID] = -1. Also it's guaranteed that the subordination relationships have a tree structure.

The head of the company wants to inform all the employees of the company of an urgent piece of news. He will 
inform his direct subordinates and they will inform their subordinates and so on until all employees know about the 
urgent news.

The i-th employee needs informTime[i] minutes to inform all of his direct subordinates (i.e After informTime[i] minutes,
all his direct subordinates can start spreading the news).

Return the number of minutes needed to inform all the employees about the urgent news.

Example 1:

Input: n = 1, headID = 0, manager = [-1], informTime = [0]
Output: 0
Explanation: The head of the company is the only employee in the company.
Example 2:

Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
Output: 1
Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 
1 minute to inform them all.
The tree structure of the employees in the company is shown.
*/

/*
We will form a map as an adjacency list and then do a DFS do find the maximum time taken along the path from the root to leaf.
Note that doing a BFS will not help, as we do not want to find the maximum at each level, instead we want to find the
maximum path from root to leaf
*/

// Iterative DFS. Note that how we do a DFS iteratively.

class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, ArrayList<Integer>> sub = new HashMap<Integer, ArrayList<Integer>>();
        for(int emp = 0; emp < manager.length; emp++) {
            int boss = manager[emp];
            if(boss == -1) continue;
            ArrayList<Integer> temp = sub.getOrDefault(boss, new ArrayList<Integer>());
            temp.add(emp);
            sub.put(boss, temp);
        }
        Stack<int[]> stack = new Stack<int[]>();
        stack.push(new int[]{headID, 0});
        int max = 0;
        while(!stack.isEmpty()) {
            int[] emp = stack.pop(); // important step to remember, we pop one child every depth.
            if(!sub.containsKey(emp[0])) max = Math.max(max, emp[1]);
            else {
                for(Integer subordinate : sub.getOrDefault(emp[0], new ArrayList<Integer>())) {
                    stack.push(new int[] {subordinate, emp[1] + informTime[emp[0]]});
                }
            }
        }
        return max;
    }
}

// Recursive
class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) continue;
            int p = manager[i];
            adj.get(p).add(i);
        }
        return dfs(adj, headID, informTime);
    }
    
    int dfs(List<List<Integer>> adj, int u, int[] inform) {
        int time = 0;
        // Recursively call DFS on each child of current node.
        for (int v : adj.get(u)) {
            time = Math.max(time, dfs(adj, v, inform));
        }
        return time + inform[u];
    }
}
