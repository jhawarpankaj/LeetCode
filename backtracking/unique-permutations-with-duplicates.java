/*
https://leetcode.com/problems/permutations-ii/

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();        
        helper(nums, 0, result);
        return result;
    }
    
    void helper(int[] nums, int start, List<List<Integer>> result) {        
        if (start == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) temp.add(num);
            result.add(temp);
            return;
        }
        Set<Integer> set = new HashSet<>();
        
        // every element will get the chance to take the first place one by one.
        // But, if a duplicate element take the same place again in the future, 
        // it will result in duplicate permutations. So keep a check on that using the Set.
        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) continue;
            set.add(nums[i]);
            swap(nums, i, start);
            helper(nums, start + 1, result);
            swap(nums, i, start);
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
