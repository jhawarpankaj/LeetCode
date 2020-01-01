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

class Solution {
    public int countNodes(TreeNode root) {
        int d = 0;
        TreeNode temp = root;
        if(temp == null) return 0;
        while(temp.left != null){
            temp = temp.left;
            d++;
        }
        if(d == 0) return 1;
        int upper = (int) Math.pow(2, d) - 1;
        int l = 0, r = (int) Math.pow(2, d) - 1;
        while(l <= r){
            int pivot = l + (r - l) / 2;
            if(exists(root, d, pivot)) l = pivot + 1;
            else r = pivot - 1;            
        }
        int lower = l;
        return upper + lower;
    }
    
    boolean exists(TreeNode root, int d, int idx){
        int l = 0, r = (int) Math.pow(2, d) - 1;
        for(int i = 0; i < d; i++){
            int half = l + (r - l) / 2;
            if(idx <= half) {
                root = root.left;
                r = half;
            }
            else{
                root = root.right;
                l = half;
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
