/*

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

/*
We want to use the distance of each node to get all the nodes at distance K from target.
Steps:
1. Iterate the tree and look for target.
2. Once the target is found, there are only 2 places where the distance K nodes will be found:
  a. Get all the nodes at distance K BELOW the current node. (See comments below, 2.a)
  b. Get all the nodes at distance K ABOVE the current node.
  
  Getting nodes below is straight forward. Just recurse from the current node downwards.
  Getting nodes above is tricky:
    i) When we found the target, return the parent call a level value 1 (See comment below, 2.b), 
       which indicates that the parent of this node is at distance 1 from the target node.
    ii) Also, the parernt call then checks where was the target value found (on the left subtree or right subtree).
    iii) If it was found on left, then look for in your right subtree for a level + 1 and also return
         a value of level + 1 to its own parent. Same logic if the target was found on the right side.
         
Note: The major trouble in this approach was how to get the node ABOVE at distance K. So we somehow wanted to
reach back to the parent from the child and also knowing the distance, for which the recursion was used which first
goes to the leaves and then percolate back to the parent with the value of the distance from the target.
*/
 

class Solution {
    List<Integer> ans = new ArrayList<Integer>();
    TreeNode target;
    int K;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        this.target = target;
        this.K = K;
        dfs(root);
        return ans;
    }
    
    int dfs(TreeNode node){
        if(node == null) return -1;
        if(node == target) {
            // 2.a
            add_subtree(node, 0);
            // 2.b
            return 1;
        }
        else{
            int L = dfs(node.left);
            int R = dfs(node.right);
            if(L != -1 && L <= K){
                if(L == K) ans.add(node.val);
                // the distance K node can be both its parent or on the right subtree. For parent return L + 1.
                add_subtree(node.right, L + 1);
                return L + 1;
            }
            else if(R != -1 && R <= K){
                if(R == K) ans.add(node.val);
                add_subtree(node.left, R + 1);
                return R + 1;
            }
            else return -1;            
        }
    }
    
    void add_subtree(TreeNode node, int level){
        // the condition level > K is used to stop if we 
        // have already passed the distance from the target.
        if(node == null || level > K) return; 
        if(K == level) ans.add(node.val);
        add_subtree(node.left, level + 1);
        add_subtree(node.right, level + 1);        
    }
}
