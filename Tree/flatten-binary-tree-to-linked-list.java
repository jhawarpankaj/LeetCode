/*
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
*/

// Brute force way of finding the leaf node. BAD WAY
class Solution {
    
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    TreeNode helper(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        if (left != null) {
            TreeNode temp = root.right;
            root.right = root.left;
            TreeNode end = root;
            while (end.right != null) end = end.right;
            end.right = temp;
            root.left = null;
        }
        // as we anyways, iterate to the end using the for loop, we will just return the current node.
        return root;
    }
}

// understand the importance of traversal here, as we move in postorder way,
// we move upwards by creating a linked list at every node, so that finally at root,
// the entire tree is converted to a linked list.
class Solution {
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    TreeNode helper(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        TreeNode left = helper(root.left);
        TreeNode right = helper(root.right);
        if (left != null) {
            left.right = root.right;
            root.right = root.left;
            root.left = null;            
        }
        // if  (right == null), it is guaranteed that left will not be null, otherwise the call would have 
        // returned in the second base case of recursion.
        // right is denoting the tail after shuffling the connection, if no right, means left is the tail.
        return right == null ? left : right;
    }
}

