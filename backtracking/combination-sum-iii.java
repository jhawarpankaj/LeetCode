/*
https://leetcode.com/problems/combination-sum-iii/

Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
Note:
All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
*/

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(0, 0, 1, k, n, temp, result);
        return result;
    }
    void helper(int sum, int count, int curr, int k, int target, List<Integer> temp, List<List<Integer>> result) {
        if (sum == target && count == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (count >= k || sum >= target) return;
        for (int i = curr; i <= 9; i++) {
            sum += i;
            temp.add(i);
            count++;
            helper(sum, count, i + 1, k, target, temp, result);
            count--;
            temp.remove(temp.size() - 1);
            sum -= i;
        }
    }
}
