/*
https://leetcode.com/problems/closest-leaf-in-a-binary-tree/

Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest 
leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. 
Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree 
given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
*/
// Solution 1: Building an entire graph out of tree.
class Solution {
    Map<Integer, ArrayList<TreeNode>> map = new HashMap<Integer, ArrayList<TreeNode>>();
    public int findClosestLeaf(TreeNode root, int k) {
        convertToGraph(root, null);
        Queue<Integer> Q = new LinkedList<Integer>();
        Q.add(k);
        
        // to pass the test cases where the target itself is the only node in the tree.
        // Except root all other node in the tree will have 1 parent, so if any node's adj (except root) 
        // is 1 it means its a leaf node.
        if(map.get(k).isEmpty() || (map.get(k).size() == 1 && k != root.val)) return k;
        Set<Integer> visited = new HashSet<Integer>();        
        while(!Q.isEmpty()){
            int val = Q.remove();
            visited.add(val);
            for(TreeNode temp: map.get(val)){
                if(temp.left == null && temp.right == null) return temp.val;
                if(!visited.contains(temp.val)) Q.add(temp.val);
            }
        }
        return -1;
    }
    
    void convertToGraph(TreeNode node, TreeNode parent){
        map.putIfAbsent(node.val, new ArrayList<TreeNode>());
        if(parent != null) map.get(node.val).add(parent);
        if(node.left != null){
            map.get(node.val).add(node.left);
            convertToGraph(node.left, node);
        }
        if(node.right != null) {
            map.get(node.val).add(node.right);
            convertToGraph(node.right, node);
        }
    }
}
// Soln 2: Building only a parent path from target to root.

class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        TreeNode goal = helper(root, k, parent);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> Q = new LinkedList<>();
        visited.add(goal);
        Q.add(goal);
        visited.add(null);
        while (!Q.isEmpty()) {
            for (int i = Q.size(); i > 0; i--) {
                TreeNode curr = Q.remove();
                if (curr.left == null && curr.right == null) return curr.val;
                if (!visited.contains(curr.left)) {
                    visited.add(curr.left);
                    Q.add(curr.left);
                }
                if (!visited.contains(curr.right)) {
                    visited.add(curr.right);
                    Q.add(curr.right);
                }
                TreeNode anc = parent.get(curr);
                if (!visited.contains(anc)) {
                    visited.add(anc);
                    Q.add(anc);
                }
            }
        }
        return -1;
    }
    
    TreeNode helper(TreeNode root, int k, Map<TreeNode, TreeNode> parent) {
        if (root == null) return null;
        if (root.val == k) return root;
        TreeNode left = helper(root.left, k, parent); 
        if (left != null) {
            parent.put(root.left, root);
            return left;
        }
        TreeNode right = helper(root.right, k, parent);
        if (right != null) {
            parent.put(root.right, root);
            return right;
        }
        return null;
    }
}

// Path from root to target can be stored in an ArrayList or an Stack instead of hashmap.

class Solution {
    public int findClosestLeaf(TreeNode root, int k) {
        Stack<TreeNode> parent = new Stack<>();
        TreeNode target = buildParentPath(root, parent, k);
        return bfs(target, parent);        
    }
    
    TreeNode buildParentPath(TreeNode root, Stack<TreeNode> parent, int k) {
        if (root == null) return null;
        if (root.val == k) return root;
        parent.push(root);
        TreeNode left = buildParentPath(root.left, parent, k);
        // note that the we need to return the final target node to the main function. Otherwise, we could also return any non-null value (root node).
        if (left != null) return left; 
        TreeNode right = buildParentPath(root.right, parent, k);
        if (right != null) return right;
        parent.pop();
        return null;
    }
    
    int bfs(TreeNode target, Stack<TreeNode> parent) {
        if (target == null) return -1;
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(target);
        Set<TreeNode> visited = new HashSet<>();
        while (!Q.isEmpty()) {
            for (int i = Q.size(); i > 0; i--) {
                TreeNode curr = Q.remove();
                if (curr.left == null && curr.right == null) return curr.val;
                if (curr.left != null && !visited.contains(curr.left)) {
                    Q.add(curr.left);
                    visited.add(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    Q.add(curr.right);
                    visited.add(curr.right);
                }
                if (!parent.isEmpty()) {
                    TreeNode top = parent.pop();
                    Q.add(top);
                    visited.add(top);
                }
            }
        }
        return -1;
    }
}
