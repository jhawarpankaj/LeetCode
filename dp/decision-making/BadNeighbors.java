/* 

https://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009

Problem Statement:
    The old song declares "Go ahead and hate your neighbor", and the residents of Onetinville have taken those words to heart. 
    Every resident hates his next-door neighbors on both sides. Nobody is willing to live farther away from the town's well 
    than his neighbors, so the town has been arranged in a big circle around the well. Unfortunately, the town's well is in 
    disrepair and needs to be restored. You have been hired to collect donations for the Save Our Well fund.

    Each of the town's residents is willing to donate a certain amount, as specified in the donations, which is listed in clockwise 
    order around the well. However, nobody is willing to contribute to a fund to which his neighbor has also contributed. 
    Next-door neighbors are always listed consecutively in donations, except that the first and last entries in donations are 
    also for next-door neighbors. You must calculate and return the maximum amount of donations that can be collected.

    Definition
    Class: BadNeighbors
    Method: maxDonations
    Parameters: int[]
    Returns: int
    Method signature: int maxDonations(int[] donations)
    (be sure your method is public)
    Limits
    Time limit (s): 840.000
    Memory limit (MB): 64
    Constraints
    - donations contains between 2 and 40 elements, inclusive.
    - Each element in donations is between 1 and 1000, inclusive.
    Examples
    0)
    { 10, 3, 2, 5, 7, 8 }
    Returns: 19
    The maximum donation is 19, achieved by 10+2+7. It would be better to take 10+5+8 except that the 10 and 8 donations are from 
    neighbors.
    1)
    { 11, 15 }
    Returns: 15
    2)
    { 7, 7, 7, 7, 7, 7, 7 }
    Returns: 21
    3)
    { 1, 2, 3, 4, 5, 1, 2, 3, 4, 5 }
    Returns: 16
    4)
    { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 
    81, 45, 435, 7, 36, 57, 86, 81, 72 }
    Returns: 2926
*/
public class BadNeighbors {
    public int maxDonations(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        return Math.max(getMax(arr, 0, n - 1), getMax(arr, 1, n));
    }
    public int getMax(int[] arr, int l, int h) {
        int in = 0, ex = 0;
        for (int i = l; i < h; i++) {
            int temp = ex;
            ex = Math.max(in, ex);
            in = temp + arr[i];            
        }
        return Math.max(in, ex);        
    }
}
