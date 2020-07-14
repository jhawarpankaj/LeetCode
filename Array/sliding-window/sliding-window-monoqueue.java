// Comparatively see the Problem1 and Problem2. Problem1 is based on the plain sliding-window and Problem2 is based on Monoq. 

/*
Problem1 : https://leetcode.com/problems/minimum-size-subarray-sum/

Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
Example: 
Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int l = 0, h = 0; h < nums.length; h++) {
            sum += nums[h];
            while (sum >= s) { // move the left pointer whenever the constraint breaks.
                min = Math.min(min, h - l + 1);
                sum -= nums[l++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

/*
Problem2 : https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/

Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
If there is no non-empty subarray with sum at least K, return -1.

Example 1:
Input: A = [1], K = 1
Output: 1

Example 2:
Input: A = [1,2], K = 4
Output: -1

Example 3:
Input: A = [2,-1,2], K = 3
Output: 3

Note:
1 <= A.length <= 50000
-10 ^ 5 <= A[i] <= 10 ^ 5
1 <= K <= 10 ^ 9
*/

// Because of negative nu,bers being allowed, we may loose a shortest subarray, if we just move the left pointer.
// For example: Given A = [-2, 4, 3, -1, 3], and K = 7, we will miss the subarray [4, 3] if we just move the left pointer sequentially.
// Because of negative nu,bers being allowed, we may loose a shortest subarray, if we just move the left pointer.
// For example: Given A = [-2, 4, 3, -1, 3], and K = 7, we will miss the subarray [4, 3] if we just move the left pointer sequentially.

class Solution {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] P = new int[n + 1];
        // Getting cumulative sum. With cumulative sum, we can easily check if a contiguous subarray sum is >= K. 
        // And if we don't maintain that we have to iterate the array all the time to get the sum. 
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + A[i];
        }
        int min = Integer.MAX_VALUE;
        Deque<Integer> DQ = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            // Assume that the Queue has elements (lets call them OLD) which are greater than the current element (lets call it CURR): 
            // Now for a sec imagine that in the future we will find a number (lets call it FUT) whose difference with the OLD, i.e., FUT - OLD >= K.
            // Then we can conclude that FUT - CURR will also be >= K, also with the guarantee that this subarray will be smaller than the one formed by FUT - OLD.
            while (!DQ.isEmpty() && P[i] <= P[DQ.peekLast()]) DQ.removeLast();
            // With the same logic as above, we can remove the elements from the beginning of DEQUE, i.e., if with the current element, if we have already
            // found a min subarray, the numbers which will be coming in the future cannot give a better minimum subarray. 
            while (!DQ.isEmpty() && P[i] - P[DQ.peekFirst()] >= K) min = Math.min(min, i - DQ.removeFirst());
            DQ.add(i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

/*
Problem3: https://leetcode.com/problems/daily-temperatures/
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. 
If there is no future day for which this is possible, put 0 instead.
For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[T.length];
        // Logic is at the current element, I always want the next greater element.
        // So, if we maintain a decreasing monoq (the greatest one at the top always) and start from reverse
        // The elements seen in future will always need to search for the temp in the queue which are greater than that.
        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            if (stack.isEmpty()) ans[i] = 0; // no greater temperatiure avaiable,
            else ans[i] = stack.peek() - i;
            stack.push(i); // add the current temperature for the upcoming temperatures.
        }
        return ans;
    }
}

/*
Problem4: https://leetcode.com/problems/next-greater-element-i/
You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements 
in the corresponding places of nums2.
The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.

Example 1:
Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
    
Example 2:
Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
    
Note:
All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.
*/

// Similar to Problem3.
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums1.length];
        // Preprocess num2.
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) stack.pop();
            if (!stack.isEmpty()) map.put(nums2[i], stack.peek());
            else map.put(nums2[i], -1);
            stack.push(nums2[i]);
        }
        // Solve for nums1
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}

/*
Problem5: https://leetcode.com/problems/sliding-window-maximum/
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. Return the max sliding window.
Follow up:
Could you solve it in linear time?

Example:
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 
Constraints:
1 <= nums.length <= 10^5
-10^4 <= nums[i] <= 10^4
1 <= k <= nums.length
*/

// This is classic Monoq problem. Idea is if the incoming next element is greater than all the previous elements in the K window,
// it's guaranteed that the incoming one will be choosen as the max element always and those lesser elements in the queue are of no use.
// But what if the incoming element is lesser, we don't know anything about this. When the current greater (at the top of Queue) will move out
// of the K window, this can be the next greatest, so keep it.

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1]; // Given 3 elements and K = 2, we will have 3 - 2 + 1 windows.
        Deque<Integer> DQ = new ArrayDeque<>();
        int maxIndex = 0;
        // Add the first K elements.
        for (int i = 0; i < k; i++) {
            process(DQ, nums, i, k);
            DQ.add(i);
        }
        int ind = 0;
        result[ind++] = nums[DQ.getFirst()]; // add result for first window.        
        // Iterate for the rest of the window. 
        for (int i = k; i < n; i++) {
            process(DQ, nums, i, k);
            DQ.add(i);
            result[ind++] = nums[DQ.getFirst()];
        }
        return result;
    }    
    void process(Deque<Integer> DQ, int[] nums, int index, int k) {
        if (!DQ.isEmpty() && DQ.getFirst() == index - k) DQ.removeFirst();
        while (!DQ.isEmpty() && nums[index] > nums[DQ.getLast()]) DQ.removeLast();
    }
}

/*
Problem6: https://leetcode.com/problems/largest-rectangle-in-histogram/
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
Example:
Input: [2,1,5,6,2,3]
Output: 10
*/

// Another classic example of Monoq.
// Idea is: to get the area of the largest rectangle, we need to cal area of every bar and get the max (area of any bar can be determined after knowing how far it 
// can extend on it's right and left) [Both the right and left extension can be determined by finding a bar smaller than it's own height on the left and right continuous 
// subarray].
// So the problem again asks something to look for in the continuous subarray: at the current position, go left and right and find the bar smaller than current height.
// If we maintain a monoq, increasing from end, on finding a smaller bar, we will know the left and right limits of the bar on top of the stack.

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(n); // adding the end of array, for getting the right width of the bar.
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            // if the current bar's width is smaller than the stack top, 
            // we got both the left and right boundaries for the bar on the top.
            while (stack.peek() != n && heights[i] <= heights[stack.peek()]) {
                max = Math.max(max, heights[stack.pop()] * (stack.peek() - i - 1));
            }
            stack.push(i);
        }
        
        // whatever left in the stack are the bars with decreasing height.
        while (stack.peek() != n) {
            max = Math.max(max, heights[stack.pop()] * (stack.peek() - 0));
        }
        return max;
    }
}

/*
Problem7: https://leetcode.com/problems/maximal-rectangle/
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
Example:
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

// This problem can be reduced to the max histogram.
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;
        int max = 0;
        int[] top = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i][j];
                top[j] = c == '0' ? 0 : top[j] + 1;
            }
            max = Math.max(max, getMaxHistogram(top));            
        }
        return max;
    }    
    int getMaxHistogram(int[] top) {
        int n = top.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(n);
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (stack.peek() != n && top[i] <= top[stack.peek()]) {
                max = Math.max(max, top[stack.pop()] * (stack.peek() - i - 1));
            }
            stack.push(i);
        }        
        while (stack.peek() != n) {
            max = Math.max(max, top[stack.pop()] * stack.peek());
        }        
        return max;
    }
}
