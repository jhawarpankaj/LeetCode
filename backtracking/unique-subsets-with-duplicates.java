/*
https://leetcode.com/problems/subsets-ii/

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.
Example:
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, new ArrayList<Integer>(), result, true);
        return result;
    }
    
    void helper(int[] nums, int curr, List<Integer> temp, List<List<Integer>> result, boolean isLastTaken) {
        if (curr == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }
        helper(nums, curr + 1, temp, result, false);
        // if curr == last && last was not taken, then don't take this also. It will result in duplicates.
        // this is little tricky to come up with but think what if arr = [1, 1, 1].
        if (curr > 0 && nums[curr] == nums[curr - 1] && !isLastTaken) return;
        temp.add(nums[curr]);
        helper(nums, curr + 1, temp, result, true);
        temp.remove(temp.size() - 1);        
    }
}


class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(nums, 0, temp, result);
        return result;
    }
    
    void helper(int[] nums, int start, List<Integer> temp, List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]); // choosing current.
            helper(nums, i + 1, temp, result);
            temp.remove(temp.size() - 1); // not choosing current.
        }
    }
}
