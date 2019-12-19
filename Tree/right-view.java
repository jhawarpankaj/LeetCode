

class Solution {
    List<Integer> result = new ArrayList<Integer>();
    int maxLevel = -1;
    public List<Integer> rightSideView(TreeNode root) {        
        bfs(root);
        dfs(root, 0);
        return result;
    }
    
    void bfs(TreeNode root){
        Queue<TreeNode> Q = new LinkedList<TreeNode>();
        if(root != null) Q.add(root);        
        while(!Q.isEmpty()){
            int len = Q.size();
            TreeNode curr = null;
            for(int i = 0; i < len - 1; i++){
                curr = Q.remove();
                if(curr.left != null) Q.add(curr.left);
                if(curr.right != null) Q.add(curr.right);
            }
            curr = Q.remove(); 
            result.add(curr.val);
            if(curr.left != null) Q.add(curr.left);
            if(curr.right != null) Q.add(curr.right);
        }
    }
    
    void dfs(TreeNode root, int currLevel){
        if(root == null) return;
        if(currLevel > maxLevel) {
            result.add(root.val);
            maxLevel = currLevel;
        }
        dfs(root.right, currLevel + 1);
        dfs(root.left, currLevel + 1);
    }
}

