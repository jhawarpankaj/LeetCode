/*
https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node. The answer can be returned in any order.

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
 
// Solution1: Building a parent map for the entire tree.
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        helper(root, parent);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(target);
        visited.add(target);
        visited.add(null);
        while (!Q.isEmpty()) {
            for (int i = Q.size(); i > 0; i--) {
                if (K == 0) {
                    while (!Q.isEmpty()) result.add(Q.remove().val);
                    break;
                }
                TreeNode curr = Q.remove();
                if (!visited.contains(curr.left)) {
                    visited.add(curr.left);
                    Q.add(curr.left);
                }
                if (!visited.contains(curr.right)) {
                    visited.add(curr.right);
                    Q.add(curr.right);
                }
                if (!visited.contains(parent.get(curr))) {
                    visited.add(parent.get(curr));
                    Q.add(parent.get(curr));
                }
            }
            K--;
        }
        return result;        
    }    
    void helper(TreeNode root, Map<TreeNode, TreeNode> parent) {
        if (root == null) return;
        if (root.left != null) parent.put(root.left, root);
        if (root.right != null) parent.put(root.right, root);
        helper(root.left, parent);
        helper(root.right, parent);
    }
}

// Solution2: Building parent path only from the target node to the root element.
// That is the only root, we would need to explore while returning from the goal node.
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        helper(root, target, parent);
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(target);
        visited.add(null);
        while (!Q.isEmpty()) {
            for (int i = Q.size(); i > 0; i--) {
                if (K == 0) {
                    while (!Q.isEmpty()) result.add(Q.remove().val);
                    break;
                }
                TreeNode curr = Q.remove();
                if (!visited.contains(curr.left)) {
                    visited.add(curr.left);
                    Q.add(curr.left);
                }
                if (!visited.contains(curr.right)) {
                    visited.add(curr.right);
                    Q.add(curr.right);
                }
                TreeNode father = parent.get(curr);
                if (!visited.contains(father)) {
                    visited.add(father);
                    Q.add(father);
                }
            }
            K--;
        }
        return result;
    }
    
    TreeNode helper(TreeNode root, TreeNode target, Map<TreeNode, TreeNode> parent) {
        if (root == null) return null;
        if (root == target) return target;
        TreeNode left = helper(root.left, target, parent);
        if (left != null) {
            parent.put(root.left, root);
            return left;            
        }
        TreeNode right = helper(root.right, target, parent);
        if (right != null) {
            parent.put(root.right, root);
            return right;
        }
        return null;        
    }    
}

// Solution3: Avoid using the parent map.
// Once we reach the target node, finding nodes at K distance below it is straightforward.
// For Finding nodes K distance above it: After reaching the target node, we can stop the traversal and move upwards, but let the upward call know that we are returning 
// after finding the target node (as we can return after hitting a null also, we need to differentiate this)
// We can use, 2 different types of return value for both the above cases, so that we can assess which return call it is.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        helper(root, target, result, K);
        return result;
    }
    
    int helper(TreeNode root, TreeNode target, List<Integer> result, int K) {
        if (root == null) return -1;
        if (root == target) {
            dfsDown(root, K, result);
            return 1;
        }
        int left = helper(root.left, target, result, K);
        if (left != -1) {
            if (left == K) result.add(root.val);
            else if (left < K) dfsDown(root.right, K - left - 1, result);
            return left + 1;
        }
        int right = helper(root.right, target, result, K);
        if (right != -1) {
            if (right == K) result.add(root.val);
            else if (right < K) dfsDown(root.left, K - right - 1, result);
            return right + 1;
        }
        return -1;
    }
    
    void dfsDown(TreeNode root, int K, List<Integer> result) {
        if (root == null) return;
        if (K == 0) result.add(root.val);
        dfsDown(root.left, K - 1, result);
        dfsDown(root.right, K - 1, result);
    }
}
