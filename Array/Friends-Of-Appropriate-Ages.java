/*
https://leetcode.com/problems/friends-of-appropriate-ages/

Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person. 
Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.
How many total friend requests are made?

Example 1:
Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
Notes:
1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
*/

// (A / 2 + 7) < B <= A
class Solution {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int count = 0;
        for (int i = 0; i < ages.length; i++) {
            int A = ages[i];
            int l = firstElemGreaterThanKey(ages, A / 2 + 7);
            int h = lastElemLessThanOrEqToKey(ages, A);
            if (l == -1 || h == -1 || h < l) continue;
            count += h - l; // note that h - l considers to not count the curr val.
                                        // the eq is = (h - l + 1 - 1)
        }
        return count;
    }
    
    // also note that below 2 functions have the same code and we can use only one.
    int firstElemGreaterThanKey(int[] arr, int key) {
        int l = 0, h = arr.length - 1;
        int index = -1;
        while (l <= h) {
            int curr = (l + h) >>> 1;
            if (arr[curr] > key) {
                index = curr;
                h = curr - 1;
            } else l = curr + 1;
        }
        return index;
    }
    
    int lastElemLessThanOrEqToKey(int[] arr, int key) {
        int l = 0, h = arr.length - 1;
        int index = -1;
        while (l <= h) {
            int curr = (l + h) >>> 1;
            if (arr[curr] <= key) {
                index = curr;
                l = curr + 1;
            } else h = curr - 1;
        }
        return index;
    }
}
