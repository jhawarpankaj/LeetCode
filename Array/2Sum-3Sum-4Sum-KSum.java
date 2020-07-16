/*
1) 2Sum: https://leetcode.com/problems/two-sum/
2) Sorted 2Sum: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
3) 3Sum: https://leetcode.com/problems/3sum/
4) 3Sum Smaller: https://leetcode.com/problems/3sum-smaller/
5) 3Sum Closest: https://leetcode.com/problems/3sum-closest/
6) 4Sum: https://leetcode.com/problems/4sum/

An important point to learn from here which is used as a concept in lot of other problems:
Look at the solution of 3 Sum and understand how do we handle to remove duplicates in a sorted array. It's really simple but a powerful technique to ignore duplicate solutions.
Same concept is applied in case of combination and permutation problems to ignore repeated combinations/permutations.
*/

/*
Problem 1
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,
Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) return new int[] {i, map.get(target - nums[i])};
            map.put(nums[i], i);
        }
        return new int[] {0, 0};
    }
}

/*
Problem 2
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.

Note:
Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.
Example:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
*/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        for (int l = 0, h = numbers.length - 1; l < h; ) {
            if (numbers[l] + numbers[h] < target) l++;
            else if (numbers[l] + numbers[h] > target) h--;
            else return new int[] {l + 1, h + 1};
        }
        throw new IllegalArgumentException();
    }
}

/*
Problem 3
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note:
The solution set must not contain duplicate triplets.
Example:
Given array nums = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, h = n - 1;
            int sum = -nums[i]; 
            while (l < h) {
                if (nums[l] + nums[h] < sum) l++;
                else if (nums[l] + nums[h] > sum) h--;
                else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[h]));
                    while (l < h && nums[l + 1] == nums[l]) l++;
                    while (l < h && nums[h - 1] == nums[h]) h--;
                    l++; h--;
                }
            }
            while (i + 1 < n && nums[i + 1] == nums[i]) i++;
        }
        return result;
    }
}

/*
Problem 4
Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition 
nums[i] + nums[j] + nums[k] < target.

Example:
Input: nums = [-2,0,1,3], and target = 2
Output: 2 
Explanation: Because there are two triplets which sums are less than 2:
             [-2,0,1]
             [-2,0,3]
*/
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, h = n - 1;
            while (l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (sum >= target) h--;
                else {
                    ans += h - l;
                    l++;
                }
            }
        }
        return ans;
    }
}

/*
Problem 5
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example 1:
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Constraints:
3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
*/
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        int ans = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, h = n - 1;
            while (l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (Math.abs(sum - target) < Math.abs(ans - target)) ans = sum;
                if (sum >= target) h--;
                else l++;
            }
        }
        return ans;
    }
}

/*
Problem 6

Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? 
Find all unique quadruplets in the array which gives the sum of target.

Note:
The solution set must not contain duplicate quadruplets.

Example:
Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return new ArrayList<>(kSum(4, nums, 0, target));
    }
    
    List<LinkedList<Integer>> kSum(int k, int[] nums, int index, int target) {
        if (k == 2) return twoSum(nums, index, target);
        List<LinkedList<Integer>> result = new ArrayList<>();
        int n = nums.length;
        for (int i = index; i <= n - k; i++) {
            List<LinkedList<Integer>> sub = kSum(k - 1, nums, i + 1, target - nums[i]);
            for (LinkedList<Integer> list : sub) {
                list.addFirst(nums[i]); // using LinkedList to do the insertion at beginning efficiently.
                result.add(new LinkedList<Integer>(list));
            }
            while (i < n - 1 && nums[i] == nums[i + 1]) i++; // to remove duplicates
        }
        return result;
    }
    
    List<LinkedList<Integer>> twoSum(int[] nums, int index, int target) {
        int l = index, h = nums.length - 1;
        List<LinkedList<Integer>> result = new ArrayList<>();
        while (l < h) {
            int sum = nums[l] + nums[h];
            if (sum < target) l++;
            else if (sum > target) h--;
            else {
                result.add(new LinkedList<Integer>(Arrays.asList(nums[l], nums[h])));
                while (l < h && nums[l] == nums[l + 1]) l++; // to remove duplicates
                while (l < h && nums[h] == nums[h - 1]) h--; // to remove duplicates
                l++; h--;
            }
        }
        return result;
    }
}
