/*
https://leetcode.com/problems/find-duplicate-subtrees/

Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root 
node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
      
The following are two duplicate subtrees:

      2
     /
    4
    
and

    4
 */
 
class Solution {
    Map<String, Integer> map;
    List<TreeNode> result;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<String, Integer>();
        result = new ArrayList<TreeNode>();
        serialize(root);
        return result;
    }
    
    String serialize(TreeNode root){
        if(root == null) return "#";
        String serial = root.val + "," + serialize(root.left) + "," + serialize(root.right);
        if(map.getOrDefault(serial, 0) == 1) result.add(root);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        return serial;
    }
}
