
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    
    int height(TreeNode root){
        if(root == null) return 0;
        int l = height(root.left);
        if(l == -1) return -1;
        int r = height(root.right);
        if(r == -1 || Math.abs(r - l) > 1) return -1;
        return 1 + Math.max(l, r);
    }
}


// Once we identified that the solution needs bottom up traversal, 
// we go to the left and right subtree and store their values,
// and take actions post recursion.
