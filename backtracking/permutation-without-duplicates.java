/*
https://leetcode.com/problems/permutations/

Given a collection of distinct integers, return all possible permutations.
Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }
    
    void helper(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) temp.add(num);
            result.add(new ArrayList<>(temp));
            return;
        }
        // every element gets a chance to take the first place 
        // and then recursively solving the problem.
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i); // swap
            helper(start + 1, nums, result); // swap each element to first place.
            swap(nums, start, i); // unswap
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
