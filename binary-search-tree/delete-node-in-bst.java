/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root 
node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
    
*/

/*
A node when found in the tree, should be replaced by the inorder successor of the node, if the inorder successor doesn't 
exist, then it's parent or may be parent's parent which is just greater than the current node. In this approach we need to 
keep track of all parent's.

Anothe approach would be replace the node with the inorder successor, if present, otherwise with inorder predecessor. 
If the predecessor is also not there, it means it's a leaf node.
*/

class Solution {
    public TreeNode deleteNode(TreeNode root, int k) {
        if(root == null) return null;
        else if(root.val > k) root.left = deleteNode(root.left, k);
        else if(root.val < k) root.right = deleteNode(root.right, k);
        else {
            Integer suc = successor(root.right);
            if(suc == null) {
                Integer pred = predecessor(root.left);
                if(pred == null) return null;
                else {                    
                    root.left = deleteNode(root.left, pred);                    
                    root.val = pred;
                }
            }
            else {                
                root.right = deleteNode(root.right, suc);                
                root.val = suc;
            }
        }
        return root;
    }
    
    Integer successor(TreeNode root) {
        if(root == null) return null;
        while(root.left != null) root = root.left;
        return root.val;
    }
    
    Integer predecessor(TreeNode root) {
        if(root == null) return null;
        while(root.right != null) root = root.right;
        return root.val;
    }
}

// 
