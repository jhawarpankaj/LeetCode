/*
You are driving a vehicle that has capacity empty seats initially available for passengers. 
The vehicle only drives east (ie. it cannot turn around and drive west.)

Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip:
the number of passengers that must be picked up, and the locations to pick them up and drop them off. 
The locations are given as the number of kilometers due east from your vehicle's initial location.

Return true if and only if it is possible to pick up and drop off all passengers for all the given trips. 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
Example 3:

Input: trips = [[2,1,5],[3,5,7]], capacity = 3
Output: true
*/

/*
The problem is similar to the Meeting Rooms II problem:

1. As we can only go from east to west, events happening before should be considered first. So we sort the array using the start time.
2. For any event i, if before starting this event if the previos trips are over, then we again increase the capacity.
3. Else, before starting this event, no other event has ended then again reduce the capacity for this event as well.
4. Return false anytime if we overshoot the capacity.
*/

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Arrays.sort(trips, (a, b) -> {
           return a[1] - b[1];
        });
        PriorityQueue<int[]> PQ = new PriorityQueue<int[]>((a, b) -> {
            return a[1] - b[1];
        });
        if(trips.length == 0) return true;
        int count = 0;
        if(capacity < trips[0][0]) return false;
        count += trips[0][0];
        PQ.add(new int[]{trips[0][0], trips[0][2]});
        
        for(int i = 1; i < trips.length; i++) {            
            while(!PQ.isEmpty()) {
                int[] temp = PQ.peek();
                if(trips[i][1] >= temp[1]) {
                    PQ.poll();
                    count -= temp[0];
                }
                else break;
            }
            count += trips[i][0];
            if(count > capacity) return false;
            PQ.add(new int[] {trips[i][0], trips[i][2]});
        }
        
        return true;
    }
}

/*
1. As we can only go from east to west, events happening before should be considered first. TreeMap helps us in
getting the sorted order of events based on their early occurence.
2. At the start of the journey, the capacity should be reduced and at the end of the journey the capacity increases.
3. If at any point the capacity goes below 0, means we have overshooted the capacity.
*/


class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for(int[] trip : trips) {
            map.put(trip[1], map.getOrDefault(trip[1], 0) - trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0) + trip[0]);
        }
        for(int val : map.values()) {
            capacity += val;
            if(capacity < 0) return false;
        }
        return true;
    }
}
