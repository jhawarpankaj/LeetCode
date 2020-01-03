/*
In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, 
then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.

Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: 
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.
*/

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int[] V = new int[days[n - 1] + 1];
        int[] pass = {1, 7, 30};
        int prev = 0;
        for(int i = 0; i < n; i++) {
            int current = days[i];
            int temp = V[prev];
            while(++prev < current) V[prev] = temp;
            int min = Integer.MAX_VALUE;            
            for(int j = 0; j < pass.length; j++){
                int val = current - pass[j] + 1;
                min = Math.min(min, (val - 1 < 0 ? costs[j] : V[val - 1] + costs[j]));
            }
            V[days[i]] = min;
        }
        return V[V.length - 1];
    }
}

// this is also similar to the staircase problem.
// We have to reach target (days[days.length - 1]) from the source (0) with the constraint that
// we can have a ticket for 1-day/7-days/30-days. We have to find a minimum of all these values.
