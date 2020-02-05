/*

https://leetcode.com/problems/power-of-two/

Given an integer, write a function to determine if it is a power of two.

Example 1:

Input: 1
Output: true 
Explanation: 20 = 1

*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (-n)) == n);
    }
}

