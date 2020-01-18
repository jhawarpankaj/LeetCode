/*

https://leetcode.com/problems/distribute-coins-in-binary-tree/

Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  
(The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

Example 1:

Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.

*/

class Solution {  
    int res = 0;
    public int distributeCoins(TreeNode root) {
        helper(root);
        return res;
    }
    
    int helper(TreeNode root) {
        if(root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        
        // as all nodes just need one coin, surplus is the extra coin with this node which has to be 
        // moved across the edges. It can be postive or negative. But the absolute value is
        // the no of coins to be moved across the edge.
        int surplus = (left + right + root.val) - 1;
        res = res + Math.abs(surplus);
        return surplus;
    }
}

