class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Queue<Node> Q = new LinkedList<Node>();
        if(root != null) Q.add(new Node(root, 0));
        Map<Integer, PriorityQueue<Integer>> hm = new TreeMap<Integer, PriorityQueue<Integer>>(); 
        while(!Q.isEmpty()){
            Node temp = Q.remove();
            PriorityQueue<Integer> pq = hm.getOrDefault(temp.dist, new PriorityQueue<Integer>());
            pq.add(temp.node.val);
            hm.put(temp.dist, pq);
            if(temp.node.left != null) Q.add(new Node(temp.node.left, temp.dist - 1));
            if(temp.node.right != null) Q.add(new Node(temp.node.right, temp.dist + 1));
        }        
        for(Map.Entry<Integer, PriorityQueue<Integer>> entry: hm.entrySet()){
            result.add(new ArrayList<Integer>(entry.getValue()));            
        }        
        return result;
    }
}

class Node{
    TreeNode node;
    int dist;
    Node(TreeNode x, int y){
        node = x;
        dist = y;
    }
}
