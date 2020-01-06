/*

https://leetcode.com/problems/cheapest-flights-within-k-stops/

There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, 
your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 

The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.

*/

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        
        // build an adjacency list...        
        Map<Integer, HashMap<Integer, Integer>> map = new HashMap<Integer, HashMap<Integer, Integer>>();
        for(int i = 0; i < flights.length; i++){
            HashMap<Integer, Integer> temp = map.getOrDefault(flights[i][0], new HashMap<Integer, Integer>());
            temp.put(flights[i][1], flights[i][2]);
            map.put(flights[i][0], temp);
        }
        
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((a, b) -> {
            if(a[0] == b[0]) return 0;
            else if(a[0] < b[0]) return -1;
            else return 1;
        });
        
        PQ.add(new int[] {0, src, K + 1});
        
        while(!PQ.isEmpty()){
            int[] temp = PQ.remove();
            int dist = temp[0];
            int source = temp[1];
            int hops = temp[2];            
            if(source == dst) return dist;
            if(hops > 0){
                HashMap<Integer, Integer> adjList = map.getOrDefault(source, new HashMap<Integer, Integer>());
                for(Map.Entry<Integer, Integer> entry: adjList.entrySet()){
                    int adj = entry.getKey();
                    int distance = entry.getValue();
                    PQ.add(new int[] {dist + distance, adj, hops - 1});
                }
            }
        }
        return -1;
    }
}
