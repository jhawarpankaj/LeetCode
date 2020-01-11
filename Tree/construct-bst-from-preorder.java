/*
Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

*/

// Approach 1:

class Solution {
    public TreeNode bstFromPreorder(int[] preorder) {
        return getBST(preorder, 0, preorder.length - 1);
    }
    
    TreeNode getBST(int[] preorder, int l, int h) {
        if(l > h) return null;
        TreeNode root = new TreeNode(preorder[l]);
        if(l == h) return root;
        int p = l;
        while(p + 1 <= h && preorder[p + 1] < root.val) p++;
        root.left = getBST(preorder, l + 1, p);
        root.right = getBST(preorder, p + 1, h);
        return root;
    }
}

// Approach 2:

class Solution {
    int idx = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return bst(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    TreeNode bst(int[] preorder, int lower, int upper) {
        if(idx == preorder.length) return null;
        int val = preorder[idx];
        
        // comparison is always valid id >lower && <upper
        if(val < lower || val > upper) return null;
        idx++;
        TreeNode root = new TreeNode(val);
        
        // To the left goes: lesser than the current node's value
        // so current node's value is passed as upper.
        root.left = bst(preorder, lower, val);
        
        // To the right goes, greater than the current node's value
        // hence current node's val is passed as lower.
        root.right = bst(preorder, val, upper);
        return root;
    }
}
