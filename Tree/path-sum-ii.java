
// Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

// Note: A leaf is a node with no children.

// Example:

// Given the below binary tree and sum = 22,

//       5
//      / \
//     4   8
//    /   / \
//   11  13  4
//  /  \    / \
// 7    2  5   1
// Return:

// [
//    [5,4,11,2],
//    [5,8,4,5]
// ]


class Solution {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        getAllPathSum(root, 0, sum, new ArrayList<Integer>());
        return result;
    }
    
    void getAllPathSum(TreeNode root, int currVal, int target, List<Integer> temp){
        if(root == null) return;
        currVal = currVal + root.val;
        temp.add(root.val);
        getAllPathSum(root.left, currVal, target, temp);
        getAllPathSum(root.right, currVal, target, temp);
        if(currVal == target && root.left == null && root.right == null) result.add(new ArrayList<Integer>(temp));
        temp.remove(temp.size() - 1);
    }
}

// The strategy is when you go bottom up after exploring the leaves,
// undo the changes you made when going one level up.
