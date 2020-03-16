/*
https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the 
start and end coordinates of the horizontal diameter. Since it's horizontal, y-coordinates don't matter and hence 
the x-coordinates of start and end of the diameter suffice. Start is always smaller than end. 
There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. 
A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. 
The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 
(bursting the other two balloons).
*/

// SORTING ON START TIME:

class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> {
           return a[0] - b[0]; 
        });
        int minEndTime = points[0][1], count = 1;
        for(int i = 0; i < points.length; i++) {
            int currStart = points[i][0];
            int currEnd = points[i][1];
            if(currStart <= minEndTime) minEndTime = Math.min(minEndTime, currEnd);
            else {
                count++;
                minEndTime = currEnd;
            }
        }
        return count;
    }
}

// SORTING ON END TIME:

/*
1. We actually have to group mutually overlapping intervals (kind of connected components in a graph).
2. To check if two events overlap or not, sort them by finish time and check:
    a) if next start_time > prevEndTime, then no overlap and update the prevEndTime.
    b) else its an overlap.
*/

class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> {
           return a[1] - b[1]; 
        });
        int maxEndTime = points[0][1], count = 1;
        for(int i = 0; i < points.length; i++) {
            int currStart = points[i][0];
            int currEnd = points[i][1];
            if(currStart > maxEndTime) { 
                count++;
                maxEndTime = currEnd;
            }
        }
        return count;
    }
}
