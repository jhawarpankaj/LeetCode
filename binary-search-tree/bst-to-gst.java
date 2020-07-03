/*
https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/

Given the root of a binary search tree with distinct values, modify it so that every node has a new value equal to the sum of the 
values of the original tree that are greater than or equal to node.val.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Input: [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 
Constraints:

The number of nodes in the tree is between 1 and 100.
Each node will have value between 0 and 100.
The given tree is a binary search tree.
*/

// The problem is simple, it just needs to identify the traversal type. We can see that it needs RDL, that's sort of revresed inorder.

// Recursive
class Solution {
    int temp = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        bstToGst(root.right);
        root.val += temp;
        temp = root.val;
        bstToGst(root.left);
        return root;
    }
}

// Iterative
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int temp = 0;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            curr.val += temp;
            temp = curr.val;
            curr = curr.left;
        }
        return root;
    }
}
