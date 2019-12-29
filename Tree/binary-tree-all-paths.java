
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<String> result = new ArrayList<String>();
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> temp = new ArrayList<String>();
        helper(root, temp);
        return result;
    }
    
    void helper(TreeNode root, List<String> temp){
        if(root == null) return;
        temp.add(String.valueOf(root.val));
        if(root.left == null && root.right == null) {
            String path = String.join("->", temp);
            result.add(path);
        }
        helper(root.left, temp);
        helper(root.right, temp);
        temp.remove(temp.size() - 1);
    }
}
