// https://community.topcoder.com/stat?c=problem_statement&pm=2402&rd=5009

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
