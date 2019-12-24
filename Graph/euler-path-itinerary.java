
class Solution {
    
    Map<String, PriorityQueue<String>> hm = new HashMap<String, PriorityQueue<String>>();
    Stack<String> stack = new Stack<String>();
    
    public List<String> findItinerary(List<List<String>> tickets) {
        
        for(List<String> list: tickets){
            String source = list.get(0);
            String dest = list.get(1);
            PriorityQueue<String> pq = hm.getOrDefault(source, new PriorityQueue<String>());
            pq.add(dest);
            hm.put(source, pq);
        }
        dfs("JFK");
        List<String> result = new ArrayList<String>();
        while(!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }
    
    void dfs(String origin){
        PriorityQueue<String> Q = hm.get(origin);
        while(Q != null && !Q.isEmpty()) dfs(Q.remove());
        stack.push(origin);        
    }
}
