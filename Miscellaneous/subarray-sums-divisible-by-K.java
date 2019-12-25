
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int[] mod = new int[K];
        int sum = 0;
        for(int i = 0; i < A.length; i++){
            sum = sum + A[i];
            // in java mod of a negative no will be negative
            // to make it positive always...
            mod[(sum % K + K) % K]++;
        }
        int res = 0;
        for(int i = 0; i < mod.length; i++){
            if(mod[i] > 1)
                // If a mod value is say 5, then there can be 
                // 4 X 3 X 2 X 1 possible subarrays.
                // this product is equal to [n * (n - 1)] / 2. 
                res = res + (mod[i] * (mod[i] - 1)) / 2;
        }
        res = res + mod[0];
        return res;
    }
}
