/*
https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.

Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.

Could you do this in O(n) runtime?

Example:

Input: [3, 10, 5, 25, 2, 8]

Output: 28

Explanation: The maximum result is 5 ^ 25 = 28.
*/

class TrieNode {
    TrieNode[] child;
    TrieNode() {
        child = new TrieNode[2];
    }
}

class Solution {
    public int findMaximumXOR(int[] nums) {
        int max = 0, L = 0, res = 0;
        for(int i = 0; i < nums.length; i++) max = Math.max(max, nums[i]);
        
        // max length of the binary string.
        L = (Integer.toBinaryString(max)).length();
        
        // note that the bitmask will be of length L + 1.
        int bitmask = 1 << L;
        
        // Converting all given integers to same size(L) binary numbers. 
        // Otherwise, we could have a problem and we might end up doing wrong XOR, e.g, 0001 will xor with 1 always. 
        String[] strNums = new String[nums.length];
        for(int i = 0; i < nums.length; i++) {
            strNums[i] = (Integer.toBinaryString(nums[i] | bitmask)).substring(1);
        }
        
        // We can calculate the max XOR in one pass starting from 0 index. 
        // But won't it be incorrect in case a maximum pair is at end? No, because it will again get a chance to XOR  
        // with the element at beginning.
        // Steps:
        // 1. We maintain two pointer: curr and xor. 
        //    - curr points to the path of the current integer and xor to the which will lead to max xor.
        TrieNode root = new TrieNode();
        for(String s : strNums) {
            TrieNode curr = root, xor = root;
            int val = 0;
            for(char bit : s.toCharArray()) {
                int index = bit - '0';
                if(curr.child[index] == null) curr.child[index] = new TrieNode();
                curr = curr.child[index];
                int toggledIndex = 1 - index;
                if(xor.child[toggledIndex] != null) {
                    val = (val << 1) | 1;
                    xor = xor.child[toggledIndex];
                } else {
                    val = (val << 1);
                    xor = xor.child[index];
                }
            }
            res = Math.max(res, val);
        }
        return res;
    }
}
