
// https://leetcode.com/problems/serialize-and-deserialize-bst/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder("");
        if(root == null) return sb.toString();
        postorder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    void postorder(TreeNode root, StringBuilder sb) {
        if(root == null) return;        
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(root.val + ",");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.isEmpty()) return null;
        String[] postorder = data.split(",");
        for(String s : postorder) {
            System.out.print(s + ", ");
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for(String val : postorder) {
            stack.push(Integer.valueOf(val));
        }
        
        return rdeserialize(stack, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode rdeserialize(Deque<Integer> stack, int lower, int upper) {
        if(stack.isEmpty()) return null;
        int val = stack.peek();
        if(val < lower || val > upper) return null;
        stack.pop();
        TreeNode root = new TreeNode(val);
        root.right = rdeserialize(stack, val, upper);
        root.left = rdeserialize(stack, lower, val);
        return root;
    }
}
