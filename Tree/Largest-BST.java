/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree 
with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

          10 
          / \ 
         5  15 
        / \   \ 
       1   8   7
=> (min_for_right_subtree, max_for_left_subtree, max_size)
Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
*/             


class Solution {
    // return array for each node: 
    //     [0] --> min
    //     [1] --> max
    //     [2] --> largest BST in its subtree(inclusive)
    public int largestBSTSubtree(TreeNode root) {
        if(root == null) return 0;
        int[] result = getMaxBST(root);
        return result[2];
    }
    
    int[] getMaxBST(TreeNode root){
        if(root == null) return new int[] {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] left = getMaxBST(root.left);
        int[] right = getMaxBST(root.right);
        // if greater than maximum of left subtree and less than minimum of right subtree, then it 
        // is a valid binary search tree. Compare this node's value with the min and max of left and right and return.
        if(root.val > left[1] && root.val < right[0])
            return new int[] {Math.min(root.val, left[0]), Math.max(root.val, right[1]), left[2] + right[2] + 1};
        else
            return new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left[2], right[2])};
    }
}

/* 
For a node, we want to know:
i) the maximum element seen in its left subtree,
ii) the minimum element seen in its right subtree.
We can then compare the current node's value with the value (i) & (ii), i.e. 
root.val > maximum_of_left && root.val < minimum_of_right.

As for left, we want to know the max and for right we want to know the min, we need an array to store these 2 requirements.
The 3 element just contain the maximum size BST seen in the left and right.
*/
