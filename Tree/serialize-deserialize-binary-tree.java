

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder s = new StringBuilder("");
        reserialize(root, s);
        return s.toString();
    }
    
    public String easyToRemSerialize(TreeNode root) {
        if(root == null) return "#";
        return root.val + "," + easyToRemSerialize(root.left) + "," + easyToRemSerialize(root.right);
    }
    
    public void reserialize(TreeNode root, StringBuilder s) {
        // if(root == null) return "#";
        // return root.val + "," + reserialize(root.left) + "," + rserialize(root.right);
        if(root == null) {
            s.append("#");
            return;
        }
        s.append(root.val + ",");
        reserialize(root.left, s);
        s.append(",");
        reserialize(root.right, s);        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        return rdeserialize(new LinkedList<String>(Arrays.asList(split)));
    }
    
    public TreeNode rdeserialize(Deque<String> Q) {
        // Actually we dont need to do the Q.isEmpty() check here because
        // the way the tree is serialized will fit into the exact no of recursive calls.
        String val = Q.remove();
        if(val.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = rdeserialize(Q);
        root.right = rdeserialize(Q);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
