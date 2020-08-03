/*
https://leetcode.com/problems/combination-sum/

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the 
candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

Constraints:
1 <= candidates.length <= 30
1 <= candidates[i] <= 200
Each element of candidate is unique.
1 <= target <= 500
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates, 0, target, 0, new ArrayList<Integer>(), result);
        return result;
    }    
    void helper(int[] nums, int start, int target, int sum, List<Integer> temp, List<List<Integer>> result) {
        if (sum > target || start >= nums.length) return;
        if (sum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }
        sum += nums[start];
        temp.add(nums[start]);
        helper(nums, start, target, sum, temp, result); // as repetition allowed, calling with same start value.
        temp.remove(temp.size() - 1);
        sum -= nums[start];
        helper(nums, start + 1, target, sum, temp, result);
    }
}
