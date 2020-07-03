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
 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Note that to calculate the serialization of the full tree, we go Preorder/
// but here we need the serialization of each subtree, that's why we go PostOrder.
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        helper(root, map, result);
        return result;
    }
    
    String helper(TreeNode root, Map<String, Integer> map, List<TreeNode> result) {
        if (root == null) return "#";
        String left = helper(root.left, map, result);
        String right = helper(root.right, map, result);
        String curr = root.val + "," + left + "," + right;
        map.put(curr, map.getOrDefault(curr, 0) + 1);
        if (map.get(curr) == 2) result.add(root);
        return curr;
    }
}
