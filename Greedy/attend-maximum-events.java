/*

https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.

You can attend an event i at any day d where startTimei <= d <= endTimei. 
Notice that you can only attend one event at any time d.

Return the maximum number of events you can attend.

Example 1:

Input: events = [[1,2],[2,3],[3,4]]
Output: 3
Explanation: You can attend all the three events.
One way to attend them all is as shown.
Attend the first event on day 1.
Attend the second event on day 2.
Attend the third event on day 3.

*/

/*
The idea is to think of the situation in a real life when we have a lot of meetings to attend in a span of days. What is that
we would be doing. Identify all the events which can be attended today and out of all those events attend the one having the 
earliest start time.
*/

class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            // Push the latest finishing events to the end.
            if(a[1] - b[1] != 0) return a[1] - b[1];
            // If two events are finishing on the same day, then push the earliest starting event to the beginning.
            return a[0] - b[0];            
        });
        Set<Integer> set = new HashSet<Integer>();
        int count = 0;        
        for(int i = 0; i < events.length; i++) {
            int start = events[i][0];
            int end = events[i][1];
            for(int j = start; j <= end; j++) {
                if(!set.contains(j)) {
                    count++;
                    set.add(j);
                    break;
                }
            }
        }
        return count;
    }
}
