class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if (n <= 2) {
            return n;
        } 
        
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = (dp[i - 1] % 1000000007 + dp[i - 2] % 1000000007) % 1000000007;
        }
        return dp[n - 1];
    }
}