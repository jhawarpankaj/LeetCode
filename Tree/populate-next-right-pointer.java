
/*
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

*/
class Solution {
    public Node connect(Node root) {
        // return approach1_bfs(root);
        // return approach2_dfs_itr(root);
        return approach3_dfs_rec(root);
    }
    
    Node approach1_bfs(Node root){
        Queue<Node> Q = new LinkedList<Node>();
        if(root != null) Q.add(root);
        Node right;
        while(!Q.isEmpty()){
            right = null;
            for(int i = Q.size() - 1; i >= 0; i--){
                Node current = Q.remove();
                current.next = right;
                right = current;
                if(current.right != null) Q.add(current.right);
                if(current.left != null) Q.add(current.left);
            }
        }
        return root;
    }
    
    Node approach2_dfs_itr(Node root){
        if(root == null) return null;
        Node leftPtr = root;
        Node curr = null;
        while(leftPtr.left != null){
            curr = leftPtr;
            while(curr != null){
                curr.left.next = curr.right;
                if(curr.next != null) curr.right.next = curr.next.left;
                curr = curr.next;
            }
            leftPtr = leftPtr.left;            
        }
        return root;
    }
    
    Node approach3_dfs_rec(Node root){
        if(root == null) return null;
        dfs(root);
        return root;
    }
    
    void dfs(Node root){
        if(root.left == null) return;
        root.left.next = root.right;
        if(root.next != null) root.right.next = root.next.left;
        dfs(root.left);
        dfs(root.right);
    }
}

// Idea is simple but little tricky. As this is a complete tree, 
// we change the next pointer of nodes which are one level down. Direct children of a parent node can be changed easily.
// Pointer of the nodes of different children is taken care by checking the curr.next != null condition.
