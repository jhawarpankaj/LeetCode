/*
https://leetcode.com/problems/combinations/

Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
Example:
Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
*/

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(1, k, n, temp, result);
        return result;
    }
    
    void helper(int curr, int k, int n, List<Integer> temp, List<List<Integer>> result) {        
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (curr > n) return;
        temp.add(curr);
        helper(curr + 1, k, n, temp, result);
        temp.remove(temp.size() - 1);
        helper(curr + 1, k, n, temp, result);
    }
}
