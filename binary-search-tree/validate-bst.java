

class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        if(root == null) return true;
        TreeNode curr = root;
        double oldNo = - Double.MAX_VALUE;
        while(curr != null || !stack1.isEmpty()){
            while(curr != null){
                stack1.push(curr);
                curr = curr.left;
            }
            curr = stack1.pop();
            int newNo = curr.val;
            if(newNo <= oldNo) return false;
            oldNo = newNo;
            curr = curr.right;
        }
        return true;
    }
}
