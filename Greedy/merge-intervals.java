/*
https://leetcode.com/problems/merge-intervals/

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length == 0) return new int[][]{};
        
        //Sort the array using start time.
        Arrays.sort(intervals, (a, b) -> {
           return a[0] - b[0]; 
        });
        int maxEndTime = intervals[0][1];
        List<int[]> result = new ArrayList<int[]>();
        result.add(intervals[0]);
        
        // Find total overlapping events. If the current element is greater than the last elem end time, then no overlap.
        // Else, its an overlap and we need the min start and max end time to get the total overlap. (as start time is alreay sorted, we just check the finish time).
        
        for(int i = 1; i < intervals.length; i++) {
            int currStartTime = intervals[i][0];
            int currEndTime = intervals[i][1];
            int[] lastInterval = result.get(result.size() - 1);
            if(currStartTime > lastInterval[1]) result.add(intervals[i]);
            else lastInterval[1] = Math.max(lastInterval[1], currEndTime);
        }
        
        int[][] ans = new int[result.size()][2];
        for(int i = 0; i < ans.length; i++) {
            ans[i] = result.get(i);            
        }
        return ans;
    }
}
