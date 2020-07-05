/*
https://leetcode.com/problems/path-sum/

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, 0, sum);
    }
    
    boolean helper(TreeNode root, int sum, int target) {
        if (root == null) return false;        
        sum = sum + root.val; // preorder, i.e., process current node first.
        if (root.left == null && root.right == null) return sum == target;
        boolean left = helper(root.left, sum, target);
        if (left) return true; // inorder, i.e., check what left child has to say and take action.
        boolean right = helper(root.right, sum, target);
        if (right) return true; // postorder, i.e., At this point we are back at the current node after processing left and right child.
        return false; // Here, we are sure, we din't find the target.
    }
}

// Experts after some pratise, do the same thing in a cooler way.
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, 0, sum);
    }
    
    boolean helper(TreeNode root, int sum, int target) {
        if (root == null) return false;
        sum += root.val;
        if (root.left == null && root.right == null) return sum == target;
        return helper(root.left, sum, target) || helper(root.right, sum, target);
    }
}
