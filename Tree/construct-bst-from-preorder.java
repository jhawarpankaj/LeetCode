/*
https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]

*/

// Approach 1: Using the property of each node of BST.

class Solution {
    int ind = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE); // pass lower and upper bound.
    }
    
    TreeNode helper(int[] preorder, int lower, int upper) {
        if (ind >= preorder.length) return null;
        int curr = preorder[ind];
        if (curr < lower || curr > upper) return null;
        ind++;
        TreeNode root = new TreeNode(curr);
        // All elements on my left should be less than me, so I am their upper bound.
        // And their lower bound will be same as mine.
        root.left = helper(preorder, lower, curr);
        // All elems on my right should be greater than me, so I am their lower bound.
        // And their upper bound will be same as mine.
        root.right = helper(preorder, curr, upper);
        return root;
    }
}

// Approach 2: Using the property of BST, smaller elements goes to left and larger to right.

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
