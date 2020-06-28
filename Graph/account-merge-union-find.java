/*
https://leetcode.com/problems/accounts-merge/

Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails 
representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. 
Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, 
but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements 
are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], 
["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
*/

/*
At the start each entry points to its own parent. Now, while iterating if an email is already found in the map, it means it was present in some other group as well. 
Lets merge the two. At the end, all connected components will be pointing to the same parent.
*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int[] root = new int[accounts.size()];
        for (int i = 0; i < accounts.size(); i++) root[i] = i;
        Map<String, Integer> parent = new HashMap<>();
        // build the root array.
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (!parent.containsKey(email)) parent.put(email, i);
                else union(root, parent.get(email), i); // merge my box with the parent of email's box.
            }
        }
        // at this point, the root array is build.
        // rest is just to collect all connected components.
        Map<Integer, TreeSet<String>> result = new HashMap<>();
        for (int i = 0; i < root.length; i++) {
            // Get all the emails of root[i]
            TreeSet<String> temp = new TreeSet<>();
            for (int j = 1; j < accounts.get(i).size(); j++) {
                temp.add(accounts.get(i).get(j));
            }            
            int rootI = find(root, i); // get the parent of root[i].
            // add all current emails to the existing emails for parent of i.
            result.computeIfAbsent(rootI, x -> new TreeSet<String>()).addAll(temp); 
        }
        
        // now only adding the name is pending.
        List<List<String>> ans = new ArrayList<>();
        for (Integer i : result.keySet()) {            
            LinkedList<String> temp = new LinkedList<>(result.get(i));
            String name = accounts.get(i).get(0);
            temp.addFirst(name);
            ans.add(temp);
        }
        return ans;
    }
    
    int find(int[] root, int i) {
        while (i != root[i]) {
            root[i] = root[root[i]]; // reduce the path of root[i] by 1, path compression.
            i = root[i];
        }
        return i;
    }
    
    void union(int[] root, int a, int b) {
        int rootA = find(root, a); // find the root of the email.
        int rootB = find(root, b); // find the root of my current box i.
        root[rootB] = root[rootA]; // or simply root[b] = a, as a == root[a];
    }
}
