/*
https://leetcode.com/problems/subsets/

Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.
Example:
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, temp, result);
        return result;
    }
    
    void helper(int[] nums, int curr, List<Integer> temp, List<List<Integer>> result) {                
        if (curr == nums.length) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }
        temp.add(nums[curr]);
        helper(nums, curr + 1, temp, result); // considering the current one.
        temp.remove(temp.size() - 1);
        helper(nums, curr + 1, temp, result); // not considering the current one.
    }
}
