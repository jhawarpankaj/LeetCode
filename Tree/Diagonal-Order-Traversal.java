/*
https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/
Print a binary tree in diagonal traversal.
*/
// Idea is: All the right child of a node belong to the same diagonal.
// The left child will be on the next diagonal.

public class Solution {
    public ArrayList<Integer> solve(TreeNode A) {
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        helper(A, 0, map);
        ArrayList<Integer> result = new ArrayList<>();
        for (int keys : map.keySet()) {
            result.addAll(map.get(keys));
        }
        return result;
    }
    
    void helper(TreeNode root, int d, TreeMap<Integer, ArrayList<Integer>> map) {
        if (root == null) return;
        map.computeIfAbsent(d, x -> new ArrayList<Integer>()).add(root.val);
        helper(root.left, d + 1, map);
        helper(root.right, d, map);
    }
}
