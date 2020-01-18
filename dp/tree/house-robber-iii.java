
// Naive recursive solution ...

public int rob(TreeNode root) {
    if (root == null) return 0;    
    int val = 0;    
    if (root.left != null) {
        val += rob(root.left.left) + rob(root.left.right);
    }    
    if (root.right != null) {
        val += rob(root.right.left) + rob(root.right.right);
    }    
    return Math.max(val + root.val, rob(root.left) + rob(root.right));
}

// In the above solution lot of subproblems solved again and again.
// We can use DP here.

class Solution {
    Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
    public int rob(TreeNode root) {
        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root);
        int val = root.val;
        if(root.left != null) val += rob(root.left.left) + rob(root.left.right); 
        if(root.right != null) val += rob(root.right.left) + rob(root.right.right);
        int max = Math.max(val, rob(root.left) + rob(root.right));
        map.put(root, max);
        return max;
    }
    
    // Another approach...
    public int rob(TreeNode root) {
        int[] res = getMaxLoot(root);
        return Math.max(res[0], res[1]);
    }
    
    int[] getMaxLoot(TreeNode root) {
        if(root == null) return new int[2];
        int[] left = getMaxLoot(root.left);
        int[] right = getMaxLoot(root.right);
        int[] res = new int[2];
        
        // res[0] will store if the current node is not selected.
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        
        // and res[1] will store if the current node is selected.
        // as res[0] had stored if the current node was not selected then left[0] will
        // be storing the value when the left child was not selected.
        res[1] = root.val + left[0] + right[0];
        return res;        
    }
}
