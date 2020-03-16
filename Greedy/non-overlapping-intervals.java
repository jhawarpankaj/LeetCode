/*
https://leetcode.com/problems/non-overlapping-intervals/

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals 
non-overlapping.

Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
*/

/*
This is similar to the arrow burst problem. If we can group the overlapping intervals, we can get to know the minimum
number of non-overlapping intervals.
*/

// SORTING ON START TIME:

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> {
           return a[0] - b[0]; 
        });
        int minEndTime = intervals[0][1], count = 0;
        for(int i = 0; i < intervals.length; i++) {
            int currStartTime = intervals[i][0];
            int currEndTime = intervals[i][1];
            if(currStartTime >= minEndTime) minEndTime = currEndTime;
            else {
                count++;
                minEndTime = Math.min(minEndTime, currEndTime);
            }
        }
        return count - 1;
    }
}

// SORTING ON END TIME:

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> {
           return a[1] - b[1]; 
        });
        int prevEndTime = intervals[0][1], count = 0;
        for(int i = 0; i < intervals.length; i++) {
            int currStartTime = intervals[i][0];
            int currEndTime = intervals[i][1];
            if(currStartTime >= prevEndTime) prevEndTime = currEndTime;
            else count++;
        }
        return count - 1;
    }
}
