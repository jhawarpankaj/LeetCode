/*
https://leetcode.com/problems/combination-sum-ii/

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
Each number in candidates may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, 0, 0, target, temp, result);
        return result;
    }
    
    void helper(int[] nums, int curr, int sum, int target, List<Integer> temp, List<List<Integer>> result) {
        if (sum == target) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        if (sum > target) return;
        for (int i = curr; i < nums.length; i++) {
            if (i > curr && nums[i] == nums[i - 1]) continue;
            sum += nums[i];
            temp.add(nums[i]);
            helper(nums, i + 1, sum, target, temp, result);
            temp.remove(temp.size() - 1);
            sum -= nums[i];
        }
    }
}
