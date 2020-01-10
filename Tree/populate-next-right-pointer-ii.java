// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

class Solution {
    public Node connect(Node root) {
        Node curr = root;
        while(curr != null){
            Node dummy = new Node(0);
            Node movingChild = dummy;
            while(curr != null){
                if(curr.left != null) {movingChild.next = curr.left; movingChild = movingChild.next;}
                if(curr.right != null) {movingChild.next = curr.right; movingChild = movingChild.next;}
                curr = curr.next;
            }
            curr = dummy.next;
        }
        return root;
    }    
}

// The main point for this problem is to keep track of the leftMost node of the next level.
// For that, we use a dummy node.
