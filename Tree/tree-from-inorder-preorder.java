
class Solution {
    Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
    int idx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i = 0; i < inorder.length; i++){
            hm.put(inorder[i], i);
        }
        return helper(0, inorder.length - 1, inorder, preorder);
    }
    
    TreeNode helper(int l, int h, int[] inorder, int[] preorder){
        if(l > h) return null;
        int rootVal = preorder[idx++];
        TreeNode root = new TreeNode(rootVal);
        if(l == h) return root;
        int index = hm.get(rootVal);
        root.left = helper(l, index - 1, inorder, preorder);
        root.right = helper(index + 1, h, inorder, preorder);
        return root;
    }
}
