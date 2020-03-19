/*
https://leetcode.com/problems/diameter-of-binary-tree/

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length 
of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
Note: The length of path between two nodes is represented by the number of edges between them.
*/

// Idea is out of my left and right children, I'll choose the one which is maximum.
// But I will also need to check if there is a max found through the current node as root.

class Solution {
    int max;    
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        getDiameter(root);
        return max;
    }    
    public int getDiameter(TreeNode root) {
        if(root == null) return 0;
        int l = getDiameter(root.left);
        int r = getDiameter(root.right);
        max = Math.max(max, l + r);
        return 1 + Math.max(l, r);
    }
}
