class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        if(root != null) Q.add(root);
        while(!Q.isEmpty()){
            List<Integer> temp = new ArrayList<Integer>();
            int len = Q.size();
            for(int i = 0; i < len; i++){
                TreeNode curr = Q.remove();
                temp.add(curr.val);
                if(curr.left != null) Q.add(curr.left);
                if(curr.right != null) Q.add(curr.right);
            }
            result.add(temp);
        }        
        return result;
    }
}
