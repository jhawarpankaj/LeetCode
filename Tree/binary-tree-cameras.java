/*
https://leetcode.com/problems/binary-tree-cameras/

Given a binary tree, we install cameras on the nodes of the tree. 
Each camera at a node can monitor its parent, itself, and its immediate children.
Calculate the minimum number of cameras needed to monitor all nodes of the tree.
*/

/*
Idea is to go bottom up and never adding leaf nodes. We always choose to add parent over the leaf.
*/

class Solution {
    int ans;
    Set<TreeNode> covered;
    public int minCameraCover(TreeNode root) {
        ans = 0;
        covered = new HashSet();
        covered.add(null);
        dfs(root, null);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            dfs(node.left, node);
            dfs(node.right, node);
            
            // why (par == null) is required? If we don't use that all leaf nodes will also get added.
            // why just not checking for left and right nodes? It may be that left and right are added and not this node.
            // why only root node is a special case? Because, we want to check the condition "!covered.contains(node)",
            // and we dont check par == null, all leaf nodes will also get added.
            if (par == null && !covered.contains(node) ||
                    !covered.contains(node.left) ||
                    !covered.contains(node.right)) {
                ans++;
                covered.add(node);
                covered.add(par);
                covered.add(node.left);
                covered.add(node.right);
            }
        }
    }
}
