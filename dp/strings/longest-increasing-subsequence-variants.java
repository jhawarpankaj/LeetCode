// General idea for all variants of LIS.
// Build the solution bottom up. For each i, calculate the best solution by iterating all solved subproblems (0 <= k < i).
// For printing the solution, keep a pointer of the best solution.

/*
https://leetcode.com/problems/longest-increasing-subsequence/
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:
Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:
There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/

// Finding the LIS and printing the solution.
import java.util.*;
public class LIS {

	public static void main(String args[]) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		lis(arr);
	}
	
	public static void lis(int[] arr) {
		int n = arr.length;
		if (n == 0) return;
		int[] dp = new int[n];
		int[] path = new int[n];
		int ans = 1;
		Arrays.fill(dp, 1);
		for (int i = 1; i < n; i++) {
			for (int k = 0; k < i; k++) {
				if (arr[i] > arr[k] && dp[k] + 1 > dp[i]) {
					path[i] = k;
					dp[i] = dp[k] + 1;
				}
			}
			if (dp[i] == 1) path[i] = i;
			if (dp[i] > dp[ans]) ans = i;
		}
		System.out.println("Length of lis is: " + dp[ans]);
		
		// Printing the solution. 
		// We kept a pointer of best solution above while updating any i.
		int index = ans;
		StringBuilder sb = new StringBuilder();
		while (path[index] != index) {
			sb.insert(0, arr[index] + " -> ");
			index = path[index];
		}		
		sb.insert(0, arr[index] + " ->");
		System.out.println("Sequence is: " + sb.substring(0, sb.length() - 3));
	}
}

/*
https://leetcode.com/problems/wiggle-subsequence/

Example:
Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
*/

class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int ans = 1;
        // dp[k][0] denotes that num k ended up being a down (like up -> down -> up -> down)
        // dp[k][1] will represent the num k ended up being an up (like up -> down -> up)
        int[][] dp = new int[n][2];         
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], 1);
        for (int i = 1; i < n; i++) {
            for (int k = 0; k < i; k++) {
                // if current num: nums[i] > nums[k], we will add 1 to the down sequence of k and vice versa for up. 
                if (nums[i] > nums[k] && dp[k][0] + 1 > dp[i][1]) dp[i][1] = dp[k][0] + 1;
                else if (nums[i] < nums[k] && dp[k][1] + 1 > dp[i][0]) dp[i][0] = dp[k][1] + 1;
            }
            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
        }
        return ans;
    }
}

// An O(n) solution.
// At every point, we will maintain the largest up, down sequence seen, with/without using the current num.
class Solution {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) up = down + 1; // the down doesn't change in this case as the no is greater than nums[k].
            else if (nums[i] < nums[i - 1]) down = up + 1; // similarly, the up does not change in this case.
            // in the case when the no is equal, nothing changes.
        }
        return Math.max(up, down);
    }
}

/*
Longest Bitonic Subsequence.
https://www.geeksforgeeks.org/longest-bitonic-subsequence-dp-15/
*/
import java.util.*;
// Idea is to calculate the LIS (longest increasing subsequence) and LDS (longest descreasing subsequence)
// at each element. Then the longest bitonic subsequence would be just: 
// max(LIS[k] + LDS[k] - 1) where (0 <= k <= n - 1).

public class BitonicSubsequence {

	public static void main(String[] args) {
		int arr[] = { 1, 11, 2, 10, 4, 5, 2, 1 };
		System.out.println("Length of LBS is " + lbs(arr));
	}
	
	public static int lbs(int[] arr) {
		int n = arr.length;
		int[] LIS = new int[n];
		int[] LDS = new int[n];
		int[] LISPath = new int[n];
		int[] LDSPath = new int[n];
		for (int i = 0; i < n; i++) {
			LISPath[i] = i;
			LDSPath[i] = i;
		}
		Arrays.fill(LIS, 1);
		Arrays.fill(LDS, 1);
		
		// Calculate LIS in forward direction.
		for (int i = 1; i < n; i++) {
			for (int k = 0; k < i; k++) {
				if (arr[i] > arr[k] && LIS[k] + 1 > LIS[i]) {
					LIS[i] = LIS[k] + 1;
					LISPath[i] = k;
				}
			}
		}
		
		// Calculate LDS in reverse direction.
		for (int i = n - 2; i >= 0; i--) {
			for (int k = n - 1; k > i; k--) {
				if (arr[i] > arr[k] && LDS[i] < LDS[k] + 1) {
					LDS[i] = LDS[k] + 1;
					LDSPath[i] = k;
				}
			}
		}
		
		// Finding the length of max subsequence.
		int index = -1;
		int ans = 1;
		for (int i = 0; i < n; i++) {
			if (LIS[i] + LDS[i] - 1 > ans) {
				ans = LIS[i] + LDS[i] - 1; // max subsequence.
				index = i; // keeping a pointer to that.
			}
		}
		
		// Printing the solution for LIS.
		int[] path = new int[ans];
		int ind = LIS[index] - 1;
		int temp = index;
		while (temp != LISPath[temp]) {
			path[ind--] = arr[temp];
			temp = LISPath[temp];
		}
		path[ind] = arr[temp]; // updating the last element when loop break.
		
		// Printing the solution for LDS.
		ind = path.length - LDS[index];
		temp = index;
		while (temp != LDSPath[temp]) {
			path[ind++] = arr[temp];
			temp = LDSPath[temp];
		}
		path[ind] = arr[temp]; // updating the last element when loop break.
		
		System.out.println(Arrays.toString(path));
		return ans;
	}
}

// An O(nlogn) solution to LIS using Patience sort.
// To understand patience sort, https://www.youtube.com/watch?v=22s1xxRvy28&t=2s
// The rule of patience game:
// We are given a sequence of cards (any randome order, the given array in the problem can be thought of that)
// Rules:
// 1. We cannot place a bigger number on a pile of card. If the incoming card is smaller than the top of current card, it goes to the as left possible pile.
// 2. If it greater, a new pile will be created. 
// 3. At the end, we can see that a pointer from the end to beg is the longest increasing subsequence.
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;        
        int[] sort = new int[n];
        sort[0] = nums[0];
        int len = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > sort[len - 1]) sort[len++] = nums[i];
            else if (nums[i] < sort[0]) sort[0] = nums[i];
            else {
                int l = 0, h = len - 1;
                while (l != h) {
                    int m = (l + h) >>> 1;
                    if (nums[i] > sort[m]) l = m + 1;
                    else if (nums[i] <= sort[m]) h = m;
                }
                sort[l] = nums[i];
            }
        }
        return len;
    }
}
