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

// Stack based O(n) solution...

/* Idea is:
    1. For every height we need to know:
        a) its right range (i.e., how many boxes it can go to its right).
        b) its left range (i.e., how many boxes it can cover on its left).
    2. But as we need an O(n) solution, while we are at a box i, we cannot look forward or backward.
    3. Giving a thought, the right range of any box is when it encounters a box smaller than it's own height.
    4. If we iterate the array and keep adding heights as long as we see a bigger height, and on finding a height 
       smaller or EQUAL than the last height, we pop the last height, and calculate it's left and right ranges and update 
       the global max.
    5. Repeat the above process for entire array.
    6. Getting right range is simple: (current height index - the stack.peek()'s height)
    7. Getting left range: peek after a stack pop (i.e., one before).
    8. An example: Say current height index is 5 and stack.peek() is 3 and stack.peek.peek() is 1. The range for stack.peek 
        is 3 boxes, i.e., index 2, index 3 and index 4. So the formula is (5 - 3) + (3 - 1) - 1. 
        [Notice the minus 1 because 3 is counted twice].
    9. A special case when there is no stack.peek.peek if we want to use the above generic formula always. 
        (5 - 3) + (3 - 0) - 1. which gives 4 but the answer should be 5. If stack is empty, it means we need to consider 
        the elem 0 as well, hence a -1 for empty stack.
    
    Once the array iteration is complete.
    1. The element at the top of stack will always be the last element. If it was the greatest, it stays at the top or 
    if it was <=, it will remove all the previous once and stays at top. So the right index is fixed, i.e., height.length.
    
    Note: Even after removing the equal height element we will not have any problem as the other box of equal height 
    will take care of its range.
*/

class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        int max = Integer.MIN_VALUE;
        for(int i = 1; i < heights.length; i++) {
            // Remove as long as we keep seeing a greater or equal element than the current element.
            // This i is the end of the right range for all such heights.
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int curr = stack.pop();
                int rightRange = i - curr;
                int leftRange = curr - (stack.isEmpty() ? -1 : stack.peek()); // minus 1 to consider the 0th element also in case stack is empty.
                max = Math.max(max, heights[curr] * (rightRange + leftRange - 1));
            }
            // After pushing this, it is always ensured that stack is always monotonically increasing.
            stack.push(i);
        }
        // This is a monotonically increasing stack.
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            int rightRange = heights.length - curr; // as the top will always be the last element here.
            int leftRange = curr - (stack.isEmpty() ? -1 : stack.peek());
            max = Math.max(max, heights[curr] * (rightRange + leftRange - 1));
        }
        return max;
    }
}
