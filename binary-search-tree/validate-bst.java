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

class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        if(root == null) return true;
        TreeNode curr = root;
        double oldNo = - Double.MAX_VALUE;
        while(curr != null || !stack1.isEmpty()){
            while(curr != null){
                stack1.push(curr);
                curr = curr.left;
            }
            curr = stack1.pop();
            int newNo = curr.val;
            if(newNo <= oldNo) return false;
            oldNo = newNo;
            curr = curr.right;
        }
        return true;
    }
}

// Idea is to do a preorder traversal as the pre-order traversal 
// should produce a sorted array.
