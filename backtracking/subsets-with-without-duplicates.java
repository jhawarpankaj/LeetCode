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

/*

The idea is to use backtracking for generating all possible solution. In backtracking, for every candidate we generate 
all possible subsets by INCLUDING / NOT INCLUDING that candidate.

We can imagine the iteration as a tree where we pick all the neighbours (siblings) one by one and doing a DFS on each child.
                                                      []
                                                     /  \
                                                    1, [2,3,4] 
                                                   /   /
                                             [2,3,4] [3,4]

*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtrack(nums, result, 0, new ArrayList<Integer>());
        return result;
    }
    
    void backtrack(int[] nums, List<List<Integer>> result, int index, List<Integer> temp) {
        result.add(new ArrayList<Integer>(temp));
        for(int i = index; i < nums.length; i++) {
            temp.add(nums[i]); // adding one element.
            backtrack(nums, result, i + 1, temp); // doing a DFS for the added element.
            temp.remove(temp.size() - 1); // removing that element and considering the sibling.
        }
    }
}

// Similar solution by the logic of including/not-including each element.
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int N = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        recurse(0, temp, nums, result);
        return result;
    }
    
    void recurse(int ind, List<Integer> temp, int[] nums, List<List<Integer>> result) {
        if(ind == nums.length) {
            result.add(new ArrayList<Integer>(temp));
            return;
        }        
        // Note: we can also recurse first for empty and then the element. 
        // How the contents of temp variable get modified during recursion?
        // All calls to recursion adds an element to temp and then remove it.
        // So when the first call to recursion returns temp will only contain that one added element.
        temp.add(nums[ind]);       
        recurse(ind + 1, temp, nums, result);
        temp.remove(temp.size() - 1);        
        recurse(ind + 1, temp, nums, result);
    }
    
}

// Another approach is generating all binary sequences.

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int N = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // 2 ^ N is the total solution space and can be represented using N bits.
        // Now we need to toggle these n-bits for all possible solutions.
        // The inner loop toggles the bits one by one and doing an & with all the numbers (0 to 2^N) will produce all solutions.
        for(int i = 0; i < (1 << N); i++) {
            List<Integer> temp = new ArrayList<Integer>();
            for(int j = 0; j < N; j++) {
                if((i & (1 << j)) != 0) temp.add(nums[j]);
            }
            result.add(temp);
        }
        return result;
    }    
}

/*
Subsets II

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
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        backtrack(result, nums, 0, new ArrayList<Integer>());
        return result;
    }
    
    // start to avoid duplicate result like [1, 2] and [2, 1], i.e., not going back.
    void backtrack(List<List<Integer>> result, int[] nums, int start, List<Integer> temp) {
        result.add(new ArrayList<Integer>(temp));
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) continue; // to avoid duplicate result like [1, 2] and [1, 2]
            temp.add(nums[i]);
            backtrack(result, nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }    
}
