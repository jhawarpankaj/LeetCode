
class Solution {    
    final static int WHITE = -1;
    final static int GRAY = 0;
    final static int BLACK = 1;
    int[] tSort;
    Map<Integer, ArrayList<Integer>> adjList;
    Map<Integer, Integer> color;
    int ind;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        color = new HashMap<Integer, Integer>();
        adjList = new HashMap<Integer, ArrayList<Integer>>();
        tSort = new int[numCourses];
        ind = numCourses - 1;
        
        for(int i = 0; i < prerequisites.length; i++){
            ArrayList<Integer> temp = adjList.getOrDefault(prerequisites[i][1], new ArrayList<Integer>());
            temp.add(prerequisites[i][0]);
            adjList.put(prerequisites[i][1], temp);
        }
        
        for(int i = 0; i < numCourses; i++){
            color.put(i, WHITE);
        }
        
        for(int i = 0; i < numCourses; i++){
            if(color.get(i) == WHITE && isCyclic(i)) return new int[0]; 
        }
        
        return tSort;
    }
    
    // this will do a dfs traversal and check for a cycles.
    boolean isCyclic(int course){
        color.put(course, GRAY);
        for(Integer adj: adjList.getOrDefault(course, new ArrayList<Integer>())){
            if(color.get(adj) == GRAY) return true;
            else if(color.get(adj) == WHITE && isCyclic(adj)) return true;
        }
        color.put(course, BLACK);
        tSort[ind--] = course;
        return false;
    }
}
