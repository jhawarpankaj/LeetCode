
class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        height(root);
        // note that this extra 1 is subtracted because 
        // we need diameter (which is 1 less) and not the height.
        return max - 1; 
    }
    int height(TreeNode root){
        if(root == null) return 0;
        int l = height(root.left);
        int r = height(root.right);
        max = Math.max(max, l + r + 1);
        // this actually will give us the depth of the tree.
        // subtract 1 later to get diameter.
        return 1 + Math.max(l, r); 
    }
}
