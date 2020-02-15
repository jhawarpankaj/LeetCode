/*
39. Combination Sum
https://leetcode.com/problems/combination-sum/

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
find all unique combinations in candidates where the candidate numbers sums to target.

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
*/

/*
Backtracking principle:
1) For every element, we consider the case when
  a) The element is part of the solution.
  b) The element is not part of the solution.  
So one by one we make every element of the array part of the solution and then beacktrack for the remaining elements. (this 
depends on problem to problem, if same elements are allowed, we iterate from beginning always and if not we pass the start
variable.
*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, candidates, target, new ArrayList<Integer>(), 0, 0);
        return result;
    }
    
    void helper(List<List<Integer>> result, int[] candidates, int target, List<Integer> temp, int sum, int start) {
        if(sum == target) result.add(new ArrayList<Integer>(temp));
        else if(sum > target) return;
        else{
            for(int i = start; i < candidates.length; i++) {
                temp.add(candidates[i]);
                sum += candidates[i];
                // we pass i as the start because repetition is allowed but we are not allowing going back in while 
                // backtracking. That will produce duplicate results. e.g., if [1, 2] is the answer then we will also get
                // [2, 1].
                helper(result, candidates, target, temp, sum, i);
                temp.remove(temp.size() - 1);
                sum -= candidates[i];
            }
        }        
    }
}


/*
40. Combination Sum II
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations 
in candidates where the candidate numbers sums to target.

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
*/

/*
Two things to note: 
1) The passing of start index in helper method.
2) And the strategy of avoiding same results. (sorting the array first and then doing the if check)

1) If the start index is not there then in any problem [1, 2 ,3] if [1, 3] is answer then [3, 1] will also be produced.
2) For second condition, if in the problem [1,2,2,3] => [2, 3] is an answer then it will be displayed twice.  
*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        helper(result, candidates, target, 0, new ArrayList<Integer>(), 0);
        return result;
    }
    
    void helper(List<List<Integer>> result, int[] candidates, int target, int start, List<Integer> temp, int sum) {
        if(sum == target) result.add(new ArrayList<Integer>(temp));
        else if(sum > target) return;
        else {
            for(int i = start; i < candidates.length; i++) {
                if(i > start && candidates[i] == candidates[i - 1]) continue;
                temp.add(candidates[i]);
                sum += candidates[i];
                helper(result, candidates, target, i + 1, temp, sum);
                sum -= candidates[i];
                temp.remove(temp.size() - 1);
            }
        }
    }
}

/*
46. Permutations

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

/*

Permutation principle: How is permutation different than a subset problem. In Permutation we need all the elements of the 
array unlike the subset. Idea is still the same, for each element, we fix their position and generate remaining elements 
permutation.

*/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        generate(nums, nums.length, result);
        return result;
    }
    
    void generate(int[] nums, int n, List<List<Integer>> result) {        
        if(n == 1) {
            List<Integer> temp = new ArrayList<Integer>();
            for(int num : nums) {
                temp.add(num);
            }
            result.add(temp);
        }
        else {
            for(int i = 0; i < n; i++) {
                swap(nums, i, n - 1); // Fixing the position of current element by moving it to end.
                generate(nums, n - 1, result);
                swap(nums, i, n - 1); // Unfixing the position back.
            }
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
Permutations II
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

/*
Duplicates will arise if we fix one element at the same position again. So while fixing elements one by one, we keep
track if it has already been fixed at the same position, i.e, if 1 is being at end twice, it will produce duplicate 
elements.
*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(nums, result, nums.length);
        return result;
    }
    
    void helper(int[] nums, List<List<Integer>> result, int n) {
        if(n == 1) {
            List<Integer> temp = new ArrayList<Integer>();
            for(int num : nums) temp.add(num);
            result.add(temp);
        }        
        else {
            Set<Integer> set = new HashSet<Integer>();
            for(int i = 0; i < n; i++) {
                // For this iteration, all elements are being fixed at the same position, i.e., at the end.
                // So we use a set to check if any element has already been fixed for this position then dont fix it again.
                if(set.contains(nums[i])) continue;
                set.add(nums[i]);
                swap(nums, i, n - 1);
                helper(nums, result, n - 1);
                swap(nums, i, n - 1);
            }
        }
    }
    
    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
