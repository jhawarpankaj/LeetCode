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
At the start each entry points to its own parent. Now, while iterating if an email is already found in the map, it means it was present in some other group as well. Lets merge the two. At the end, all connected components will be pointing to the same parent.
*/
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int[] root = new int[accounts.size()];
        for(int i = 0; i < root.length; i++) root[i] = i;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < accounts.size(); i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String currEmail = accounts.get(i).get(j);
                if(!map.containsKey(currEmail)) map.put(currEmail, i);
                else {
                    int root1 = find(root, map.get(currEmail));
                    int root2 = find(root, i);
                    root[root2] = root1;
                }
            }
        }
        
        Map<Integer, TreeSet<String>> mergedMap = new HashMap<Integer, TreeSet<String>>();
        for(int i = 0; i < root.length; i++) {
            int parent = find(root, i);
            TreeSet<String> temp = new TreeSet<String>();
            for(int j = 1; j < accounts.get(i).size(); j++) {
                temp.add(accounts.get(i).get(j));
            }
            if(!mergedMap.containsKey(parent)) mergedMap.put(parent, temp);
            else {
                TreeSet<String> present = mergedMap.get(parent);
                present.addAll(temp);
                mergedMap.put(parent, present);
            }
        }
        
        List<List<String>> result = new ArrayList<List<String>>();
        for(Map.Entry<Integer, TreeSet<String>> entry : mergedMap.entrySet()) {
            int index = entry.getKey();
            String name = accounts.get(index).get(0);
            List<String> temp = new ArrayList<String>(entry.getValue());
            temp.add(0, name);
            result.add(temp);
        }
        return result;        
    }
    
    int find(int[] root, int i) {
        while(i != root[i]) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
}
