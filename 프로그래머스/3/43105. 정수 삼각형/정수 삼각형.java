import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;
        
        int[][] dp = new int[height][500];
        dp[0][0] = triangle[0][0];
        for (int i = 1; i < height; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][0] + triangle[i][j];
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][i - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }
        
        for (int i = 0; i < height; i++) {
            if (answer < dp[height - 1][i]) {
                answer = dp[height - 1][i];
            }
        }
        
        return answer;
    }
}