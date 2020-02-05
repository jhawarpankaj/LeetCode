/*
https://leetcode.com/problems/binary-number-with-alternating-bits/

Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.

Example 1:
Input: 5
Output: True
Explanation:
The binary representation of 5 is: 101
*/

/*
We know that if we shift the number by 1 to the right, all the ones will become zeros and vice versa. 
Now if we AND those two numbers, we can get the whole thing as zero but that won't work for numbers like 2, 4, 8... 
So we will take another approach. Instead of AND we will do an XOR . This will make all bits 1. 
Now we need to check if all the bits are 1. The best way to do that is AND the number by (number+1) . It'll give you zero.
*/

class Solution {
    public boolean hasAlternatingBits(int n) {    
        /*
        n =         1 0 1 0 1 0 1 0
        n >> 1      0 1 0 1 0 1 0 1
        n ^ n>>1    1 1 1 1 1 1 1 1
        n           1 1 1 1 1 1 1 1
        n + 1     1 0 0 0 0 0 0 0 0
        n & (n+1)   0 0 0 0 0 0 0 0
        */
        n = n ^ (n >> 1);
        return (n & (n + 1)) == 0;
    }
}
