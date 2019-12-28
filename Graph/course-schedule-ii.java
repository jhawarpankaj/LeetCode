
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        int[] indegree = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++){
            ArrayList<Integer> temp = map.getOrDefault(prerequisites[i][1], new ArrayList<Integer>());
            temp.add(prerequisites[i][0]);
            map.put(prerequisites[i][1], temp);
            indegree[prerequisites[i][0]]++;
        }
        int[] result = new int[numCourses];
        int ind = 0;
        Queue<Integer> Q = new LinkedList<Integer>();
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 0) Q.add(i);
        }
        
        while(!Q.isEmpty()){
            int course = Q.remove();
            result[ind++] = course; 
            for(Integer neigh: map.getOrDefault(course, new ArrayList<Integer>())){
                indegree[neigh]--;
                if(indegree[neigh] == 0) Q.add(neigh);
            }
        }
        
        return ind == numCourses ? result : new int[0];
    }
}
