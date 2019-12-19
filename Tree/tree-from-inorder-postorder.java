
class Solution {
    Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
    int idx = -1;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i = 0; i < inorder.length; i++){
            hm.put(inorder[i], i);
        }
        idx = postorder.length - 1;
        return helper(0, inorder.length - 1, inorder, postorder);
    }
    
    TreeNode helper(int l, int h, int[] inorder, int[] postorder){
        if(l > h) return null;
        int rootVal = postorder[idx--];
        if(l == h) return new TreeNode(rootVal);
        TreeNode root = new TreeNode(rootVal);
        int index = hm.get(rootVal);
        root.right = helper(index + 1, h, inorder, postorder);
        root.left = helper(l, index - 1, inorder, postorder);
        return root;
    }
}
