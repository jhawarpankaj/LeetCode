/*

Given two binary search trees, return True if and only if there is a node in the first tree and a node in the second tree
whose values sum up to a given integer target.

Example 1:

Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
Output: true
Explanation: 2 and 3 sum up to 5.

*/

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
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        while(true){
            while(root1 != null){
                stack1.add(root1);
                root1 = root1.left;
            }
            while(root2 != null){
                stack2.add(root2);
                root2 = root2.right;
            }
            if(stack1.isEmpty() || stack2.isEmpty()) return false;
            TreeNode temp1 = stack1.peek();
            TreeNode temp2 = stack2.peek();
            int sum = temp1.val + temp2.val;
            if(sum == target) return true;
            else if(sum < target){
                stack1.pop();
                root1 = temp1.right;
            }
            else{
                stack2.pop();
                root2 = temp2.left;
            }
        }
    }
}

// Take 2 pointers one at the extreme left of one tree and another at extreme right of another.
// Move them accordingly depending on their sum with target >, =, <.
