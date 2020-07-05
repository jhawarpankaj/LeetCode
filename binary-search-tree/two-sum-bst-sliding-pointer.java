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
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode curr1 = root1, curr2 = root2;
        while ((curr1 != null || !stack1.isEmpty()) && (curr2 != null || !stack2.isEmpty())) {
            while (curr1 != null) {
                stack1.push(curr1);
                curr1 = curr1.left;
            }
            while (curr2 != null) {
                stack2.push(curr2);
                curr2 = curr2.right;
            }
            curr1 = stack1.peek();
            curr2 = stack2.peek();
            int sum = curr1.val + curr2.val;
            if (sum == target) return true;
            else if (sum > target) {
                stack2.pop();
                curr2 = curr2.left;
                curr1 = null; // make sure that we make the curr1 = null again, as it was changes because of peek, otherwise infinte loop.
            } else {
                stack1.pop();
                curr1 = curr1.right;
                curr2 = null; // make sure that we make the curr1 = null again, as it was changes because of peek, otherwise infinte loop.
            }
        }
        return false;
    }
}

// Take 2 pointers one at the extreme left of one tree and another at extreme right of another.
// Move them accordingly depending on their sum with target >, =, <.
