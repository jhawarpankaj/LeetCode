/*
https://leetcode.com/problems/largest-rectangle-in-histogram/

Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of 
largest rectangle in the histogram.

Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

The largest rectangle is shown in the shaded area, which has area = 10 unit.

Example:

Input: [2,1,5,6,2,3]
Output: 10
*/

class Solution {
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] segment = buildSegmentTree(heights);
        return divideConquer(heights, 0, n - 1, segment);        
    }
    
    private static int divideConquer(int[] height, int l, int h, int[] segment) {
        if(l <= h) {
            if(l == h) return height[l] * 1;
            int minIndex = query(segment, height, l, h);
            int currArea = height[minIndex] * (h - l + 1);
            int leftArea = divideConquer(height, l, minIndex - 1, segment);
            int rightArea = divideConquer(height, minIndex + 1, h, segment);
            return Math.max(Math.max(currArea, leftArea), rightArea);
        }
        return 0;
    }
    
    private static int[] buildSegmentTree(int[] heights) {
        int n = heights.length;
        int[] segment = new int[2 * n];
        for(int i = n - 1, j = 2 * n - 1; i >= 0; i--, j--) {
            segment[j] = i;
        }
        for(int i = n - 1; i > 0; i--) {
            if(heights[segment[2 * i]] < heights[segment[2 * i + 1]]) segment[i] = segment[2 * i];
            else segment[i] = segment[2 * i + 1];
        }
        return segment;
    }
    
    private static int query(int[] segment, int[] heights, int i, int j) {
        int n = heights.length;
        int p = i + n;
        int q = j + n;
        int min = Integer.MAX_VALUE;
        int index = -1;
        while(p <= q) {
            if(p % 2 == 1) {
                if(heights[segment[p]] < min) {
                    min = heights[segment[p]];
                    index = segment[p];
                }
                p++;
            }
            if(q % 2 == 0) {
                if(heights[segment[q]] < min) {
                    min = heights[segment[q]];
                    index = segment[q];
                }
                q--;
            }
            p = p >> 1;
            q = q >> 1;
        }
        return index;
    }
}
