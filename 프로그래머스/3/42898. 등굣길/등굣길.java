import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[m][n];
        boolean[][] waters = new boolean[m][n];
        boolean topCheck = false;
        boolean leftCheck = false;
        
        for (int[] pos : puddles) {
            waters[pos[0] - 1][pos[1] - 1] = true;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (waters[i][j]) {
                    if (i == 0) {
                        topCheck = true;
                    }
                    if (j == 0) {
                        leftCheck = true;
                    }
                    continue;
                }
                
                if (i == 0) {
                    if (!topCheck) {
                        dp[i][j] = 1;
                    }
                    continue;
                }
                if (j == 0) {
                    if (!leftCheck) {
                        dp[i][j] = 1;
                    }
                    continue;
                }
                
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1_000_000_007;
            }
        }
        
        answer = dp[m - 1][n - 1];
        
        return answer;
    }
}