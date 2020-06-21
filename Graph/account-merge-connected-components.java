/*
https://leetcode.com/problems/accounts-merge/

Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]

Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
*/

/* 
Idea is to form a graph from the given emails and then simply perform DFS to get all connected components.
The first question which comes naturally is how to form a graph from the emails.
It's simple, remember that a HashMap can be used to represent adjacency list for an undirected graph.
Put the given emails to HashMap and we will get the adjacency list, rest is just DFS.
*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        Map<String, String> map = new HashMap<>();        
        // build graph.
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                map.putIfAbsent(email, name);                
                /* 
                computeIfAbsent: the inner computation will execute if the key is not there,
                and then the add statement executes, and a (key, value) pairs will be created in the map.
                In short, it merges the below steps into one:
                
                ArrayList<String> temp1 = graph.getOrDefault(account.get(1), new ArrayList<String>());
                temp1.add(email);
                graph.put(account.get(1), temp1);                
                */
                graph.computeIfAbsent(email, x -> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x -> new ArrayList<>()).add(email);
            }
        }
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        Set<String> sorted = new TreeSet<>();
        // note that we cannot use Priority Queue, as it may contain duplicates.
        // Also recall that a PQ is not sorted, only on calling the PQ.remove() it will give the correct answer and then rearranges the
        // PQ in logn time.
        // PriorityQueue<String> PQ = new PriorityQueue<>(); 
        List<List<String>> result = new ArrayList<>();
        
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                stack.push(email);
                while (!stack.isEmpty()) {
                    String curr = stack.pop();
                    visited.add(curr);
                    for (String adj : graph.get(curr)) {
                        if(!visited.contains(adj)) stack.push(adj);
                    }
                    sorted.add(curr);
                }
                // ArrayList will shift all the element hence O(n) operation.
                LinkedList<String> temp = new LinkedList<>(sorted); 
                temp.addFirst(map.get(email));
                result.add(temp);
                sorted.clear();
            }
        }
        return result;
    }
}
