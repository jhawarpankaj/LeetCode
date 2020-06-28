/*
https://leetcode.com/problems/cracking-the-safe/
There is a box protected by a password. The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.

While entering a password, the last n digits entered will automatically be matched against the correct password.

For example, assuming the correct password is "345", if you type "012345", the box will open because the correct password matches the suffix of the entered password.

Return any password of minimum length that is guaranteed to open the box at some point of entering it.

 

Example 1:

Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:

Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
 

Note:

n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.

*/

/*
This is a difficult problem to understand.
Say n = 3, k = {0, 1}.
All password combination: 
000, 001
010, 011
100, 101
110, 111

We can see that if n = 3, all combinations with n = 2, i.e., 00, 01, 10, 11 transits to 3 digit by picking a 0 or 1.
If we draw that on a paper, we see that the graph forms an Euler cycle, and building an Euler path gives the solution.

An image given here will help: https://ingako.gitbooks.io/algorithms/graph/euler.html

Finding the Euler circuit is the same, once all the outgoing edges of a node are explored, we will add that edge 
(note that this time we don't need the vertex, but edge is required to construct the Euler path)

*/

class Solution {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        StringBuilder ans = new StringBuilder();
        Set<String> visited = new HashSet<>();
        for (int i = 1; i <= n - 1; i++) sb.append("0");
        dfs(sb.toString(), k, ans, visited);
        ans.append(sb);
        return ans.toString();
    }
    
    void dfs(String node, int k, StringBuilder ans, Set<String> visited) {
        for (int i = 0; i < k; i++) {
            String temp = node + i;
            if (visited.contains(temp)) continue;
            visited.add(temp);
            dfs(temp.substring(1), k, ans, visited);
            ans.append(i); // we can also append to beginning, it will also produce an answer.
        }
    }
}
