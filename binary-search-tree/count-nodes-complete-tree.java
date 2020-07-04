/*
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level 
are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
*/

// Recursive.
class Solution {    
    int height(TreeNode root) {
        if (root == null) return 0;
        return 1 + height(root.left);
    }    
    public int countNodes(TreeNode root) {
        int h1 = height(root);
        if (h1 == 0) return 0;
        int h2 = 1 + height(root.right);
        // it means that the right tree with height h2 - 1 is a complete tree we need to count the nodes of left tree.
        if (h1 - 1 == h2) return (1 << (h2 - 1)) + countNodes(root.left);
        // else left is a complete tree and right need to be counted.
        else return (1 << (h1 - 1)) + countNodes(root.right);        
    }
}

// Using the property of complete tree and doing a BST for the last level.
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int level = 0;
        TreeNode temp = root;
        while (temp != null) {
            temp = temp.left;
            level++;
        }
        int upper = (1 << (level - 1)) - 1;
        int last = 0;
        int l = 1, h = (1 << (level - 1));
        while (l <= h) {
            int m = (l + h) >>> 1;
            if (exists(root, m, level)) {
                last = m;
                l = m + 1;
            } else h = m - 1;
        }
        return upper + last;
    }
    
    boolean exists(TreeNode root, int mid, int level) {
        for (int l = 1, h = (1 << (level - 1)), d = 1; d < level; d++) {
            int m = (l + h) >>> 1;
            if (mid <= m) {
                root = root.left;
                h = m;
            } else {
                root = root.right;
                l = m + 1;
            }
        }
        return root != null;
    }
}

// In a complete tree, the last level is filled from the left. Before the last level all levels have full nodes.
// So we just need to know how many nodes are there in the last level.
// We can do a binary search for the last level's (0 to 2^d nodes). Sort of quickselect search.
// [0, 1, 2, 3, 4, 5, 6, 7] In this array a node is on the left side of the tree if it belongs to the first half
// or on the right side if it belongs to the right half.
// We move left or right depending on the position of the node in the array and thus finally reach the last level.
// If that node is a null element then return false and we half our search. It's a Binary search within another binary search.
// One binary search for the last nodes of the leaf and another for moving left or right in the tree.
