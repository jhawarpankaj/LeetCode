/*

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. Each time the sliding window moves right by one position. 
Return the max sliding window.

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
 
 */
 
 /*
 
 Idea is to use a Deque. 
 1. At any given point in time the Deque will always hold the latest k elements.
 2. If the incoming element is greater than the last element in Deque, then it means that this element will be the part of
    the answer and not the previous one. So remove the last element. Keep doing this for all the <k elements in the Deque.

This way the Deque ensures that the maximum of the current k size array is at the top of the Deque always.

*/

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0 || n - k + 1 < 0) return new int[0];
        int[] result = new int[n - k + 1];
        Deque<Integer> DQ = new ArrayDeque<Integer>();
        for(int i = 0; i < n; i++) {
            if(!DQ.isEmpty() && DQ.peekFirst() < i - k + 1) DQ.removeFirst();
            while(!DQ.isEmpty() && nums[i] > nums[DQ.peekLast()]) DQ.removeLast();
            DQ.add(i);
            if(i - k + 1 >= 0) result[i - k + 1] = nums[DQ.peekFirst()];
        }
        return result;
    }
}


 
 
