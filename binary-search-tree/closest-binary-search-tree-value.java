// Recursive
class Solution {
    int res;
    double dist;
    public int closestValue(TreeNode root, double target) {
        res = root.val;
        dist = Math.abs(target - res);
        helper(root, target);
        return res;
    }
    
    void helper(TreeNode root, double target){
        if(root == null) return;
        if(target < root.val) helper(root.left, target);
        else helper(root.right, target);
        if(Math.abs(target - root.val) < dist){
            dist = Math.abs(target - root.val);
            res = root.val;
        }
    }
}

// Iterative: Exploiting the property that INORDER traversal of the tree produces a sorted array.
// We need not traverse the whole tree.

class Solution {
    public int closestValue(TreeNode root, double target) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        long prev = Long.MIN_VALUE;
        int pres;
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            pres = curr.val;
            if(target >= prev && target <= pres){
                return target - prev < pres - target ? (int) prev : pres;
            }
            prev = pres;
            curr = curr.right;
        }
        return (int) prev;
    }
}
