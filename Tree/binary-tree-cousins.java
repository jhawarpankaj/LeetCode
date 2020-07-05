/*
https://leetcode.com/problems/cousins-in-binary-tree/
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.
Return true if and only if the nodes corresponding to the values x and y are cousins.
*/

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> Q = new LinkedList<>();
        if (root != null) Q.add(root);
        while (!Q.isEmpty()) {
            int count = 0;
            for (int i = Q.size(); i > 0; i--) {
                TreeNode curr = Q.remove();
                if (curr.val == x || curr.val == y) count++;
                if (curr.left != null && curr.right != null) {
                    if ((curr.left.val == x && curr.right.val == y) || (curr.left.val == y && curr.right.val == x)) return false;
                }
                if (curr.left != null) Q.add(curr.left);
                if (curr.right != null) Q.add(curr.right);
            }
            // means cousins were found and they were not siblings otherwise they would have been identified on line 19.
            if (count == 2) return true; 
        }
        return false;        
    }
}
