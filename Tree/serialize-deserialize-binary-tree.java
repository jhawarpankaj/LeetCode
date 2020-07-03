

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);
        return sb.toString();
    }
    
    void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb = sb.append("null,");
            return;
        }
        sb.append(root.val + ",");
        helper(root.left, sb);
        helper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        Queue<String> Q = new LinkedList<>(Arrays.asList(split));
        return helper2(Q);
    }
    
    // we don't need to check if Q.isEmpty() as it will always
    // have element and will terminate properly.
    TreeNode helper2(Queue<String> Q) {
        String s = Q.remove();
        if (s.equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = helper2(Q);
        root.right = helper2(Q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
