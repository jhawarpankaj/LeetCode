
class Solution {
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        recurse(root);
        return max;
    }
    
    int recurse(TreeNode root){
        if(root == null) return 0;
        int l = recurse(root.left);
        int r = recurse(root.right);
        int max1 = Math.max(l, r);
        int max2 = Math.max(root.val, root.val + max1);
        int max3 = Math.max(l + r + root.val, max2);
        max = Math.max(max, max3);
        return max2;
    }
}
