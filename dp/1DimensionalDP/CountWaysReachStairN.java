/*
https://www.geeksforgeeks.org/number-of-ways-to-reach-nth-floor-by-taking-at-most-k-leaps/?ref=rp
Note the permutation and combination here.
*/
public class CountToReachFloorN {

	public static void main(String args[]) 
	{ 
	    // N i the no of total stairs 
	    // K is the value of the greatest leap 
	    int N = 3;
	    int K = 2;	  
	    System.out.println(perm(N, K)); // {{1, 1, 1}, {1, 2}, {2, 1}}
	    System.out.println(comb(N, K)); // {{1, 1, 1}, {1, 2}}
	}
	
	// this can be optimized by using sliding window approach.
	private static int perm(int N, int K) {
		int[] dp = new int[N + 1];
		dp[0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = Math.max(0, i - K); j < i; j++) {
				dp[i] += dp[j]; 
			}
		}
		return dp[N];
	}
	
	private static int comb(int N, int K) {
		int[] dp = new int[N + 1];
		dp[0] = 1;
		// we can take at max K jumps.
		for (int j = 1; j <= K; j++) {
			// we will now fill all i in dp[i] for each jump size.
			// we can optimize the below loop like
			// for (int i = j; i <= N; i++) and remove the if clause, 
			// as all i less than j will not change (jump size is more).
			for (int i = 1; i <= N; i++) { 
				if (i >= j) dp[i] += dp[i - j];
			}
		}
		return dp[N];
	}
}
