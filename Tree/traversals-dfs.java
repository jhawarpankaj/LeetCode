// Preorder

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root == null) return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode curr = stack.pop();
            result.add(curr.val);
            if(curr.right != null) stack.push(curr.right);
            if(curr.left != null) stack.push(curr.left);
        }
        return result;
    }
}

// Inorder

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        TreeNode curr = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(curr != null || !stack.isEmpty()){
            
            // Exhaust the LEFT
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            
            // Once left exhausted, print DATA
            curr = stack.pop();
            result.add(curr.val);
            
            // Left exhausted and data printed, go to RIGHT.
            curr = curr.right;
        }
        return result;
    }
}

// Postorder

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()){
            
            // Exhaust LEFT
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            
            // IF RIGHTs there, go to RIGHT
            TreeNode temp = stack.peek().right;
            if(temp != null) curr = temp;
            
            // ELSE print DATA
            else{
                temp = stack.pop();
                result.add(temp.val);
                
                // If the recent popped was the right child of parent, then
                // time for the parent also to get popped.
                while(!stack.isEmpty() && stack.peek().right == temp){
                    temp = stack.pop();
                    result.add(temp.val);
                }
            }
        }
        return result;
    }
}