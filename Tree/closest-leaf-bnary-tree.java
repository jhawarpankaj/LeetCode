/*

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
