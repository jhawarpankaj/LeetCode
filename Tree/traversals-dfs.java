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
        if(root == null) return result;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.peek();
            // If right is there, we have to go there, otherwise,
            // we pop the current element, but we also need to check if the popped one was the right child
            // of the top of the stack.
            if(curr.right != null) curr = curr.right;
            else {
                curr = stack.pop();
                result.add(curr.val);
                while(!stack.isEmpty() && stack.peek().right == curr) {
                    curr = stack.pop();
                    result.add(curr.val);
                }
                curr = null; // as we wanted to pop the next element from stack. 
                            // If not made null, it will infinte loop.
            }
        }
        return result;
    }
}
