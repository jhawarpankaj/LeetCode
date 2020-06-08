// COUNT ALL PALLINDROMIC SUBSEQUENCE (DUPLICATE PALLINDROME ALSO COUNTED, THERE IS ANOTHER PROBLEM ON UNIQUE SUBSEQUENCE, SEE BELOW)

public class CountAllPallindromicSubsequence {

	public static void main(String args[]) 
    { 
        String str = "abcb"; 
        System.out.println("Total palindromic "+ 
                            "subsequence are : "
                              + countPS(str)); 
    } 
	
	public static int countPS(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < n; j++) {
				if (s.charAt(i) == s.charAt(j)) {
					
//					the all 4, 00, 01, 10, 11 cases.
//					dp[i][j] = 
//							1 + dp[i + 1][j - 1] //including both, unique 
//							+ dp[i + 1][j - 1] // not including both, unique
//							+ dp[i + 1][j] // including only last, in this one dp[i + 1][j - 1] is there.
//							+ dp[i][j - 1] // including only first., in this also one dp[i + 1][j - 1].
//							- 2 * dp[i + 1][j - 1]; // removing both double counted.									
					dp[i][j] = 1 + dp[i + 1][j] + dp[i][j - 1]; 
				} else {
					// in this case, the above 11 case will not be there,
					// as they are not pallindrome.
					dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
				}
			}
		}
		return dp[0][n - 1];
	}
}

// COUNT ALL PALLINDROMIC SUBSTRING (DUPLICATES ALSO COUNTED)
// https://leetcode.com/problems/palindromic-substrings/

class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] P = new boolean[n][n];
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            P[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1 || P[i + 1][j - 1]) {
                    // here 11 case: 1
                    // 00 case: dp[i + 1][j - 1]
                    // 01 and 10 case: dp[i][j - 1] + dp[i + 1][j]
                    // in the above case dp[i + 1][j - 1] counted twice.
                        P[i][j] = true;
                        dp[i][j] = 1 - dp[i + 1][j - 1] + dp[i + 1][j] + dp[i][j - 1];
                    }
                    else {
                       dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                    }
                }
                else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
            }
        }
        
        return dp[0][n - 1];
    }
}

// COUNT ALL DISTINCT PALLINDROMIC SUBSTRING.
// https://leetcode.com/problems/count-different-palindromic-subsequences/

class Solution {
    public int countPalindromicSubsequences(String S) {
        int MOD = 1_000_000_007; // underscores allowed post java 7 for readability.
        int n = S.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (S.charAt(i) == S.charAt(j)) {
                    int low = i + 1;
                    int high = j - 1;
                    while (low <= high && S.charAt(i) != S.charAt(low)) low++;
                    while (high >= low && S.charAt(i) != S.charAt(high)) high--;
                    
                    // multplying 2 will always produce distince subsequences if there is not a situation like: a..a..a..a
                    // the X 2 logic says, take all the pallindrome of my substring and append my own two first and last character.
                    // that will always guarantee unique pallindromic substring. 
                    if (low > high) dp[i][j] = dp[i + 1][j - 1] * 2 + 2; 
                    
                    // in this case, situation is like: a..a..a. There will be 1 repetition if we add +2 because an a was already considered.
                    else if (low == high) dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    
                    // this is the case a..a..a..a. X2 will give us all unique except the inside one dp[low + 1][high - 1] will be double counted.
                    // if we consider not choosing all character before and after the inside a, but place the first and last a, those are the duplicate ones.
                    else dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1]; // also note that space cannot be optimized because of low and high.
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1]; // only dp[i + 1][j - 1] was double counted.
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + MOD : dp[i][j] % MOD;
            }            
        }
        return dp[0][n - 1];
    }
}

// COUNT ALL DISTINCE PALLINDROMIC SUBSTRING... 
// could not find this problem anywhere, but substring problem we cannot improve the complexities, as generating all substring is also 
// O(n^2), we can generate all of them and check.

// COUNT ALL DISTINCT SUBSEQUENCES...
// https://leetcode.com/problems/distinct-subsequences/
/*
idea is repetition will only be generated if a character has already appeared before like, bc..a...a
multiplying 2 will give us all solutions (including the current elem + not-including the current elem)
but if a character has appeared before, then in the case when (all elem including the first a + after that and before last a)
are not included and the last a is included, will be the duplicates.
so if we can store all the subsequences (not including the first a) of first a , those are the count of duplicates.
*/
class Solution {
    public int distinctSubseqII(String S) {
        int[] dict = new int[26];
        int total = 1;
        int MOD = 1_000_000_007;
        for (char c : S.toCharArray()) {
            int combo = total * 2 - dict[c - 'a'];
            dict[c - 'a'] = total;
            total = combo < 0 ? combo + MOD : combo % MOD;
        }
        return total - 1;
    }
}
