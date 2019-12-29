
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {        
        return getLCA(root, p.val < q.val ? p : q, p.val > q.val ? p : q);
    }
    
    TreeNode getLCA(TreeNode root, TreeNode p, TreeNode q){
        if(root == null) return null;
        if(root.val >= p.val && root.val <= q.val) return root;
        else if(root.val < p.val) return getLCA(root.right, p, q);
        else return getLCA(root.left, p, q);
    }
}

// Imagine a number line in case of a BST
// -----------|---------|------------
//            p         q
// and take action for going left or right accordingly.
