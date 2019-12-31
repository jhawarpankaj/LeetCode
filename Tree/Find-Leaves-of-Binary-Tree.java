/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
*/

class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(root, result);
        return result;
    }
    
    int helper(TreeNode root, List<List<Integer>> result){
        if(root == null) return -1;
        int depth = 1 + Math.max(helper(root.left, result), helper(root.right, result));
        if(result.size() == depth) result.add(new ArrayList<Integer>());
        result.get(depth).add(root.val);
        return depth;
    }
}

// This was just an implementation of calculating height of a binary tree.
// All nodes at same height will be part of a list.
