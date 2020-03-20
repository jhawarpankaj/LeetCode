
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/* This problem is same as the prefix sum. Look at the path in the tree as a continuous array. It's same as the continous
subarray problem.
*/

class Solution {
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        return getSum(root, 0, sum, map);
    }
    
    int getSum(TreeNode root, int currSum, int target, Map<Integer, Integer> map){
        if(root == null) return 0;
        currSum = currSum + root.val;
        int count = map.getOrDefault(currSum - target, 0);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        int res = count + getSum(root.left, currSum, target, map) + getSum(root.right, currSum, target, map);        
        // once we are done exploring the subtree and going back bottom up,
        // we want to forget the sum added to map because of the current node.
        map.put(currSum, map.get(currSum) - 1);
        return res;
    }
}
