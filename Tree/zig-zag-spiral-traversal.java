/*
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and 
alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

*/

// Using two stacks, bad solution.
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root == null) return result;
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        stack1.push(root);
        while(!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> temp = new ArrayList<Integer>();
            while(!stack1.isEmpty()) {
                TreeNode curr = stack1.pop();
                temp.add(curr.val);
                if(curr.left != null) stack2.push(curr.left);
                if(curr.right != null) stack2.push(curr.right);
            }
            result.add(new ArrayList<Integer>(temp));
            temp.clear();
            while(!stack2.isEmpty()) {
                TreeNode curr = stack2.pop();
                temp.add(curr.val);
                if(curr.right != null) stack1.push(curr.right);
                if(curr.left != null) stack1.push(curr.left);
            }
            if(!temp.isEmpty()) result.add(new ArrayList<Integer>(temp));
        }
        return result;
    }
}

// Using a Queue and LinkedList for odd/even level.
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        if(root != null) Q.add(root);
        boolean even = false;
        while(!Q.isEmpty()){
            int len = Q.size();
            LinkedList<Integer> temp = new LinkedList<Integer>();            
            for(int i = 0; i < len; i++){
                TreeNode curr = Q.remove();
                if(even) temp.addFirst(curr.val);
                else temp.addLast(curr.val);
                if(curr.left != null) Q.add(curr.left);
                if(curr.right != null) Q.add(curr.right);
            }
            result.add(new ArrayList<Integer>(temp));
            even = !even;
        }
        return result;
    }
}

// Using Deque
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> DQ = new ArrayDeque<>();
        boolean odd = true;
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        DQ.add(root);
        while (!DQ.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = DQ.size(); i > 0; i--) {
                if (odd) {
                    TreeNode curr = DQ.removeFirst();
                    if (curr.left != null) DQ.addLast(curr.left);
                    if (curr.right != null) DQ.addLast(curr.right);
                    temp.add(curr.val);
                } else {
                    TreeNode curr = DQ.removeLast();
                    if (curr.right != null) DQ.addFirst(curr.right);
                    if (curr.left != null) DQ.addFirst(curr.left);
                    temp.add(curr.val);
                }
            }
            result.add(temp);
            odd = !odd;
        }
        return result;
    }
}
