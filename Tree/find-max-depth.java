/*
https://leetcode.com/problems/maximum-depth-of-binary-tree/

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.
*/

// A template for postorder strategy.
class Solution {
    public int maxDepth(TreeNode root) {
        return helper(root);
    }
    
    int helper(TreeNode root) {
        // safety check before recursing.
        if (root == null) return 0; 
        // below is postorder strategy.
        int left = helper(root.left);
        int right = helper(root.right);
        int curr = 1 + Math.max(left, right);
        return curr;
    }
}
