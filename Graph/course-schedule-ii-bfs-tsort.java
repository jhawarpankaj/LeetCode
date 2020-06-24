/*
https://leetcode.com/problems/course-schedule-ii/

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/

// TopSort Using Kahn's Algorithm.
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];        
        for (int[] course : prerequisites) {
            graph.computeIfAbsent(course[0], x -> new ArrayList<Integer>()).add(course[1]);
            indegree[course[1]]++;
        }
        int count = 0;
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) Q.add(i);
        }
        int[] result = new int[numCourses];
        int ind = numCourses - 1;
        while (!Q.isEmpty()) {
            int curr = Q.remove();
            result[ind--] = curr;
            for (int adj : graph.getOrDefault(curr, new ArrayList<Integer>())) {
                if (--indegree[adj] == 0) Q.add(adj);
            }
            count++;
        }        
        return count == numCourses ? result : new int[0]; //// if count != numCourses, there is a cycle.
    }
}

// Topsort using DFS.
class Solution {
    int WHITE = 0;
    int GRAY = -1;
    int BLACK = 1;
    int count = 0;
    int[] color;
    int[] result;
    int ind;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();        
        result = new int[numCourses];
        ind = 0;
        color = new int[numCourses];
        for (int[] course : prerequisites) {
            graph.computeIfAbsent(course[0], x -> new ArrayList<Integer>()).add(course[1]);
        }
        for (int i = 0; i < numCourses; i++) color[i] = WHITE;
        for (int i = 0; i < numCourses; i++) {
            if (color[i] == WHITE && isCyclicDFS(graph, i)) return new int[0];
        }        
        return numCourses == count ? result : new int[0];
    }
    
    boolean isCyclicDFS(Map<Integer, ArrayList<Integer>> graph, int course) {
        color[course] = GRAY;
        for (int adj : graph.getOrDefault(course, new ArrayList<Integer>())) {
            if (color[adj] == GRAY || color[adj] == WHITE && isCyclicDFS(graph, adj)) return true;
        }
        color[course] = BLACK;
        result[ind++] = course;
        count++;
        return false;
    }
}
