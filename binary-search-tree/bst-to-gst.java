// Recursive

class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        helper(root);
        return root;
    }
    
    void helper(TreeNode root){
        if(root == null) return;
        helper(root.right);
        sum = sum + root.val;
        root.val = sum;
        helper(root.left);
    }
}

// Iterative

class Solution {
    public TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        int sum = 0;
        while(!stack.isEmpty() || curr != null){
            while(curr != null){
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();
            sum = sum + curr.val;
            curr.val = sum;
            curr = curr.left;
        }
        return root;
    }
}
