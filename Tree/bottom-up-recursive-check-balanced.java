/*
https://leetcode.com/problems/balanced-binary-tree/

Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as:

a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

 

Example 1:

Given the following tree [3,9,20,null,null,15,7]:

    3
   / \
  9  20
    /  \
   15   7
Return true.

Example 2:

Given the following tree [1,2,2,3,3,null,null,4,4]:

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
Return false.

*/
// We do a postorder traversal and terminate earlier if found the tree is not balanced at any node.
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    int height(TreeNode root) {
        if (root == null) return 0;
        int l = height(root.left);
        if (l == -1) return -1;
        int r = height(root.right);
        if (r == -1 || Math.abs(r - l) > 1) return -1;
        return 1 + Math.max(l, r);
    }
}


// Once we identified that the solution needs bottom up traversal, 
// we go to the left and right subtree and store their values,
// and take actions post recursion.
