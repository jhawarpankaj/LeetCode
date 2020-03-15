/*
https://leetcode.com/problems/meeting-rooms-ii/

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1
*/

/*
1. Sort both the start time and end times.
2. Have two pointers to point to the start of both start and end time array (i  and j).
3. Sorting with end time help us know when a meeting is over. An over meeting gives us a room to be used and if the start time falls after that end time, we do not need an extra room.
4. Iterate through the start time, if the current start time >= current end time, it means that it can use that room.
5. So dont increase the room count. Otherwise, if the start time is less than the end time, it means one more room needs to be used, so increase the room count.
*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int rooms = 0;
        for(int i = 0, j = 0; i < start.length; i++) {
            if(start[i] >= end[j]) j++;
            else rooms++;
        }
        return rooms;
    }
}

/*
1. Sort the array using the start time.
2. Use a priority queue as a min heap to store the end time. Each element in the PQ actually represents a meeting room.
3. Simply, if the current start time >= min of the end times (the top elem of the PQ), it means we don't need a new room, as we will use that room. So poll that element out of the PQ. Otherwise, add the current end time also in the PQ.
4. This way, at the end, the PQ will hold only as much element as the no of rooms required.
*/

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0) return 0;
        PriorityQueue<Integer> PQ = new PriorityQueue<Integer>();        
        Arrays.sort(intervals, (a, b) -> {
           return a[0] - b[0]; 
        });
        PQ.add(intervals[0][1]);
        for(int i = 1; i < intervals.length; i++) {
            int lastFinishTime = PQ.peek();
            if(intervals[i][0] >= lastFinishTime) PQ.poll();
            PQ.add(intervals[i][1]);
        }
        return PQ.size();
    }
}
