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
