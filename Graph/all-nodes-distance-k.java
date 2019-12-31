/*

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.

Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.

*/

class Solution {
    Map<TreeNode, ArrayList<TreeNode>> map = new HashMap<TreeNode, ArrayList<TreeNode>>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        convertTreeToGraph(root, null);
        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        Q.add(target);
        int dist = 0;
        List<Integer> result = new ArrayList<Integer>();
        Set<TreeNode> visited = new HashSet<TreeNode>();
        visited.add(target);
        while(!Q.isEmpty()){
            if(dist == K){
                while(!Q.isEmpty()){
                    result.add(Q.remove().val);
                }
                break;
            }
            else{
                for(int i = Q.size(); i > 0; i--){
                    TreeNode curr = Q.remove();
                    for(TreeNode node: map.get(curr)){
                        if(!visited.contains(node)) {
                            Q.add(node);
                            visited.add(node);
                        }
                    }                
                }
                dist++;
            }
        }
        return result;
    }
    
    void convertTreeToGraph(TreeNode root, TreeNode parent){
        if(!map.containsKey(root)) map.put(root, new ArrayList<TreeNode>());
        if(parent != null) map.get(root).add(parent);
        if(root.left != null) {
            map.get(root).add(root.left);
            convertTreeToGraph(root.left, root);
        }
        if(root.right != null){
            map.get(root).add(root.right);
            convertTreeToGraph(root.right, root);
        }
    }
}


/* 
A tree can be converted to a GRAPH. An adjacency list can be built for each node.
Root's neighbour are it's left and right child. It's left child neighbour are it's left and right chid plust 
the root and same for it's right child.
*/
