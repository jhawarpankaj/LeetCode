/*
https://leetcode.com/problems/subtree-of-another-tree/

Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. 
A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
 

Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
*/

// Approach1
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return preorder(s, t);
    }
    
    boolean preorder(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (equal(s, t)) return true;
        if (preorder(s.left, t)) return true;
        if (preorder(s.right, t)) return true;
        return false;
    }
    
    boolean equal(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        else if ((s == null && t != null) || (s != null && t == null) || (s.val != t.val)) return false;
        if (!equal(s.left, t.left) || !equal(s.right, t.right)) return false;
        return true;
    }
}

// Approach 2
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree1 = serialize(s);
        String tree2 = serialize(t);
        return tree1.indexOf(tree2) >= 0;
    }
    
    String serialize(TreeNode s) {
        if (s == null) return "null";
        String left = serialize(s.left);
        if (left.equals("null")) left = "lnull";
        String right = serialize(s.right);
        if (right.equals("null")) right = "rnull";
        String curr = "#" + s.val + "#" + left + "#" + right;
        return curr;
    }
}
