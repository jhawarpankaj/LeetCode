/*

https://leetcode.com/problems/validate-binary-search-tree/

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/
// Recursive solution utilising the property of BST for root node, left line node, right line node and all other internal node.
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }
    
    boolean helper(TreeNode root, Integer lower, Integer upper) {
        if (root == null) return true;
        // If my lower == null, it means I am on the left line of the tree.
        if (lower != null && root.val <= lower) return false;
        // If my upper is null, it means I do not have any upper bound, 
        // only the noded on the right path will have this property. 
        if (upper != null && root.val >= upper) return false;
        // All my left child should be less than me, that is, I am their upper bound. 
        if (!helper(root.left, lower, root.val)) return false;
        // All my right child should be greater than me, that is, I am their lower bound.
        if (!helper(root.right, root.val, upper)) return false;
        return true;
        
    }
}

// Similar solution with a minor tweak.
class Solution {
    public boolean isValidBST(TreeNode root) {
        return helper(root, -Double.MAX_VALUE, Double.MAX_VALUE);
    }
    
    boolean helper(TreeNode root, Double lower, Double upper) {
        if (root == null) return true;
        return root.val < upper && root.val > lower && helper(root.right, 1D * root.val, upper) 
            && helper(root.left, lower, 1D * root.val);
    }
}

// Using the inorder traversal in a BST, which guarantees to return the sequence in ascending order.
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        double last = -Double.MAX_VALUE;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (curr.val <= last) return false;
            last = curr.val;
            curr = curr.right;
        }
        return true;
    }
}
