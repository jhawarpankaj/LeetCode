
// https://leetcode.com/problems/serialize-and-deserialize-bst/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }
    
    // preorder to get comma separated string with node's values.
    void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb = sb.append(root.val + ",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        // refresh reading about split function in java, here: https://docs.google.com/document/d/1kddefrMmtwtIRGkUCH0UZ3zplT07Dnw-h1PPvYn4AuE/edit
        // because of the split function we have to specifically check the first empty string.
        String[] split = data.split(","); 
        Queue<Integer> Q = new LinkedList<>();
        for (String val : split) {
            Q.add(Integer.parseInt(val));
        }
        return helper(Q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    TreeNode helper(Queue<Integer> Q, int lower, int upper) {
        if (Q.isEmpty()) return null;
        int curr = Q.peek();
        if (curr < lower || curr > upper) return null;
        Q.remove();
        TreeNode root = new TreeNode(curr);
        root.left = helper(Q, lower, curr);
        root.right = helper(Q, curr, upper);
        return root;
    }
}
